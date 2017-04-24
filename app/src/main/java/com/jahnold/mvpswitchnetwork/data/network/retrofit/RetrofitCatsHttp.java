package com.jahnold.mvpswitchnetwork.data.network.retrofit;

import com.jahnold.mvpswitchnetwork.data.entities.Cat;
import com.jahnold.mvpswitchnetwork.data.network.CatsHttp;
import com.jahnold.mvpswitchnetwork.data.network.Data;
import com.jahnold.mvpswitchnetwork.data.network.DataError;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by matthewarnold on 16/10/2016.
 */

public class RetrofitCatsHttp implements CatsHttp {

    private RetrofitCatsHttpInterface http;

    public RetrofitCatsHttp(Retrofit retrofit) {

        http = retrofit.create(RetrofitCatsHttpInterface.class);
    }

    @Override
    public Observable<Data<List<Cat>>> getCats() {

        return http
                .getCats()
                .map(response -> response.isSuccessful()
                        ? new Data<>(true, response.body(), null)
                        : new Data<List<Cat>>(false, null, new DataError())
                );
    }
}
