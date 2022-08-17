package com.mobdevfc.cryptocurrencies.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * This singleton handle the http requests.
 */
public class VolleyManager {
    private static VolleyManager instance;
    private RequestQueue requestQueue;
    private final ImageLoader imageLoader;
    private final Context ctx;

    private VolleyManager(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    /**
     * Used to get singleton instance.
     * @param context represents the activity context.
     * @return returns the singleton instance.
     */
    public static synchronized VolleyManager getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyManager(context);
        }
        return instance;
    }

    /**
     * Used to obtain request queue.
     * @return returns request queue.
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * Add request to queue.
     * @param req represents the request to add.
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /**
     * Getter.
     * @return returns image loader.
     */
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
