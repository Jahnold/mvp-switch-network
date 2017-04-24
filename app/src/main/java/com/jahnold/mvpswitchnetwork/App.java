package com.jahnold.mvpswitchnetwork;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matthewarnold on 16/10/2016.
 */

public class App extends Application {

    private static App instance;

    private Retrofit retrofit;
    private RequestQueue requestQueue;

    public static App getInstance() {return instance;}

    public Retrofit getRetrofit() {return retrofit;}
    public RequestQueue getRequestQueue() {return requestQueue;}


    @Override
    public void onCreate() {

        super.onCreate();
        instance = this;

        initVolley();
        initRetrofit();
    }

    private void initVolley() {

        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    private void initRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://www.mocky.io/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
