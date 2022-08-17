package com.mobdevfc.cryptocurrencies.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobdevfc.cryptocurrencies.R;
import com.mobdevfc.cryptocurrencies.classes.Cryptocurrencies;
import com.mobdevfc.cryptocurrencies.classes.Url;
import com.mobdevfc.cryptocurrencies.manager.VolleyManager;
import com.mobdevfc.cryptocurrencies.model.Crypto;

import org.json.JSONException;

import java.util.List;

public class CryptoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    // CONSTANTS
    private static final String TAG = "CryptoAdapter";
    // VARIABLES
    private final List<Crypto> cryptoList;
    private final Context context;

    // ViewHolder for tag
    public class CryptoHolder extends RecyclerView.ViewHolder {
        private final View view;

        public CryptoHolder(View view) {
            super(view);
            this.view = view;
        }

        public void setViewHolder(int position){
            Crypto crypto = cryptoList.get(position);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET
                            , Url.getCryptocurrencies("CRYPTO_INTRADAY", crypto.getSymbol()
                            , "USD", "5min")
                            , null
                            , response -> {
                        ((TextView) view.findViewById(R.id.name)).setText(crypto.getName());
                        ((TextView) view.findViewById(R.id.symbol)).setText(crypto.getSymbol());
                        try {
                            // get close value of last refreshed data
                            String value = response.getJSONObject("Time Series Crypto (5min)")
                                    .getJSONObject(response.getJSONObject("Meta Data")
                                            .getString("6. Last Refreshed"))
                                    .getString("4. close");
                            // set value
                            ((TextView) view.findViewById(R.id.value))
                                    .setText(context.getResources().getString(R.string.value
                                            , value.endsWith("000")
                                                    ? value.substring(0, value.length() - 3)
                                                    : value));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                            , error -> {
                        Log.e(TAG, error.toString());
                    });

            // Access the RequestQueue through your singleton class.
            VolleyManager.getInstance(context).addToRequestQueue(jsonObjectRequest);

            view.setOnClickListener(view -> {

            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CryptoAdapter(Context context) {
        cryptoList = Cryptocurrencies.get();
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crypto
                , parent, false);
        return new CryptoHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        CryptoHolder vh = (CryptoHolder) holder;
        vh.setViewHolder(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    /* this two elements avoid duplication inside recyclerview */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    /**
     * Notification to the adapter to update the data.
     */
    public void refresh(){
        notifyDataSetChanged();
    }
}
