package com.jahnold.mvpswitchnetwork.data.network;

/**
 * Created by matthewarnold on 23/04/2017.
 */

public class Data<T> {

    public static final int LOADING = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;

    public final int status;
    public final T result;
    public final DataError error;

    public Data(int status, T result, DataError error) {
        this.status = status;
        this.result = result;
        this.error = error;
    }
}
