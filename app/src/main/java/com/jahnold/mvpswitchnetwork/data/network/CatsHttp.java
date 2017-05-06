package com.jahnold.mvpswitchnetwork.data.network;

import com.jahnold.mvpswitchnetwork.data.entities.Cat;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by matthewarnold on 16/10/2016.
 */

public class CatsHttp {

    private CatsRestApi http;

    public CatsHttp(Retrofit retrofit) {
        http = retrofit.create(CatsRestApi.class);
    }

    public Observable<Response<List<Cat>>> getCats() {
        return http.getCats();
    }
}
