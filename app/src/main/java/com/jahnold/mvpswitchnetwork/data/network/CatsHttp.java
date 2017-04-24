package com.jahnold.mvpswitchnetwork.data.network;

import com.jahnold.mvpswitchnetwork.data.entities.Cat;

import java.util.List;

import rx.Observable;

/**
 * Created by matthewarnold on 23/04/2017.
 */

public interface CatsHttp {

    Observable<Data<List<Cat>>> getCats();
}
