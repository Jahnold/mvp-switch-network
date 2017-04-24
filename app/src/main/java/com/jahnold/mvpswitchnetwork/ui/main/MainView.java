package com.jahnold.mvpswitchnetwork.ui.main;

import com.jahnold.mvpswitchnetwork.data.entities.Cat;

import java.util.List;

/**
 * Created by matthewarnold on 16/10/2016.
 */

interface MainView {

    void setContentView(List<Cat> cats);
    void setLoadingView();
    void setErrorView();
}
