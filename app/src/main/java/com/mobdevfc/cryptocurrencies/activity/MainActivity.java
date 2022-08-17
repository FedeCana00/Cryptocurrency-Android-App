package com.mobdevfc.cryptocurrencies.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import com.mobdevfc.cryptocurrencies.R;
import com.mobdevfc.cryptocurrencies.adapter.CryptoAdapter;

public class MainActivity extends AppCompatActivity {
    // VARIABLES
    private CryptoAdapter cryptoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            init();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init(){
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            cryptoAdapter.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });

        // use a linear layout manager
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // specify an adapter
        cryptoAdapter = new CryptoAdapter(this);
        recyclerView.setAdapter(cryptoAdapter);
    }
}