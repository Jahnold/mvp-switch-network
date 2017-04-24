package com.jahnold.mvpswitchnetwork.data.network.retrofit;

import com.jahnold.mvpswitchnetwork.data.entities.Cat;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by matthewarnold on 16/10/2016.
 */

interface RetrofitCatsHttpInterface {

    @GET("58fcfef20f00004c21513695")
    Observable<Response<List<Cat>>> getCats();
}
