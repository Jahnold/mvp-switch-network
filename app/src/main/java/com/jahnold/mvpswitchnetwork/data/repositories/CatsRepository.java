package com.jahnold.mvpswitchnetwork.data.repositories;

import com.jahnold.mvpswitchnetwork.data.entities.Cat;
import com.jahnold.mvpswitchnetwork.data.network.CatsHttp;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by matthewarnold on 16/10/2016.
 */

public class CatsRepository {

    private CatsHttp http;

    public CatsRepository(CatsHttp http) {
        this.http = http;
    }

    public Observable<Response<List<Cat>>> getCats() {
        return http.getCats();
    }
}
