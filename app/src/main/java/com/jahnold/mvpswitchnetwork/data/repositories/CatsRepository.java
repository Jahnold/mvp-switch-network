package com.jahnold.mvpswitchnetwork.data.repositories;

import com.jahnold.mvpswitchnetwork.data.entities.Cat;
import com.jahnold.mvpswitchnetwork.data.network.CatsHttp;
import com.jahnold.mvpswitchnetwork.data.network.Data;

import java.util.List;

import rx.Observable;

/**
 * Created by matthewarnold on 16/10/2016.
 */

public class CatsRepository {

    private CatsHttp http;

    public CatsRepository(CatsHttp http) {
        this.http = http;
    }

    public Observable<Data<List<Cat>>> getCats() {

        return http
                .getCats()
                .startWith(new Data<>(Data.LOADING, null, null));
    }
}
