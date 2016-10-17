package com.jahnold.mvpswitchnetwork.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jahnold.mvpswitchnetwork.R;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setContentView() {

    }

    @Override
    public void setLoadingView() {

    }

    @Override
    public void setErrorView() {

    }
}
