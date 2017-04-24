package com.jahnold.mvpswitchnetwork.data.network.volley;

import com.android.volley.RequestQueue;
import com.jahnold.mvpswitchnetwork.data.entities.Cat;
import com.jahnold.mvpswitchnetwork.data.network.CatsHttp;
import com.jahnold.mvpswitchnetwork.data.network.Data;

import java.util.List;

import rx.Observable;

/**
 * Created by matthewarnold on 16/10/2016.
 */

public class VolleyCatsHttp implements CatsHttp {

    private RequestQueue requestQueue;

    public VolleyCatsHttp(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public Observable<Data<List<Cat>>> getCats() {
        return null;
    }
}
