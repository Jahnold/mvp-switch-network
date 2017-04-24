package com.jahnold.mvpswitchnetwork.data.network;

/**
 * Created by matthewarnold on 23/04/2017.
 */

public class Data<T> {

    public final boolean isSuccessful;
    public final T result;
    public final DataError error;

    public Data(boolean isSuccessful, T result, DataError error) {
        this.isSuccessful = isSuccessful;
        this.result = result;
        this.error = error;
    }
}
