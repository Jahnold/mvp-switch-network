package com.jahnold.mvpswitchnetwork.data.network;

/**
 * Created by matthewarnold on 23/04/2017.
 */

public class DataError {

    private int errorCode;
    private String errorMessage;
    private Throwable throwable;

    public DataError(Throwable throwable) {
        this.throwable = throwable;
    }

    public DataError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
