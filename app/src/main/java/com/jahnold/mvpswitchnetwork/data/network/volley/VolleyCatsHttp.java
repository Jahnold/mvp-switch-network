package com.jahnold.mvpswitchnetwork.data.network.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jahnold.mvpswitchnetwork.data.entities.Cat;
import com.jahnold.mvpswitchnetwork.data.network.CatsHttp;
import com.jahnold.mvpswitchnetwork.data.network.Data;
import com.jahnold.mvpswitchnetwork.data.network.DataError;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by matthewarnold on 16/10/2016.
 */

public class VolleyCatsHttp implements CatsHttp {

    private RequestQueue requestQueue;
    private Gson gson;

    public VolleyCatsHttp(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        this.gson = new Gson();
    }

    @Override
    public Observable<Data<List<Cat>>> getCats() {

        PublishSubject<Data<List<Cat>>> subject = PublishSubject.create();

        String url = "http://www.mocky.io/v2/58fcfef20f00004c21513695";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response -> {

                    Data<List<Cat>> result;
                    try {
                        Cat[] catsArray = gson.fromJson(response, Cat[].class);
                        List<Cat> cats = Arrays.asList(catsArray);

                        result = new Data<>(Data.SUCCESS, cats, null);
                    }
                    catch (Exception e) {
                        result = new Data<>(Data.ERROR, null, new DataError(e));
                    }
                    subject.onNext(result);
                },
                error -> {

                    Data<List<Cat>> result = new Data<>(Data.ERROR, null, new DataError(error));
                    subject.onNext(result);
                }
        );

        requestQueue.add(request);

        return subject.asObservable();
    }
}
