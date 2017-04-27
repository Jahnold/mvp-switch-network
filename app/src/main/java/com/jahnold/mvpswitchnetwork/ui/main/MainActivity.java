package com.jahnold.mvpswitchnetwork.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.jahnold.mvpswitchnetwork.App;
import com.jahnold.mvpswitchnetwork.R;
import com.jahnold.mvpswitchnetwork.data.entities.Cat;
import com.jahnold.mvpswitchnetwork.data.network.CatsHttp;
import com.jahnold.mvpswitchnetwork.data.network.volley.VolleyCatsHttp;
import com.jahnold.mvpswitchnetwork.data.repositories.CatsRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.cats_recycler) RecyclerView recycler;
    @BindView(R.id.cats_error) LinearLayout errorView;
    @BindView(R.id.cats_loading) LinearLayout loadingView;

    private MainPresenter presenter;
    private CatsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRecycler();
        initPresenter();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        presenter.init();
    }

    @Override
    protected void onDestroy() {

        presenter.unbindView(this);
        super.onDestroy();
    }

    private void initRecycler() {

        adapter = new CatsAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

    private void initPresenter() {

        // In a real app you would probably want to use a
        // dependency injection framework to do all this for you

//        Retrofit retrofit = App.getInstance().getRetrofit();
//        CatsHttp catsHttp = new RetrofitCatsHttp(retrofit);

        RequestQueue requestQueue = App.getInstance().getRequestQueue();
        CatsHttp catsHttp = new VolleyCatsHttp(requestQueue);

        CatsRepository catsRepository = new CatsRepository(catsHttp);

        presenter = new MainPresenter(catsRepository);
        presenter.bindView(this);
    }

    @OnClick(R.id.cats_retry_button)
    void onRetryClick() {

        presenter.tryAgain();
    }

    @Override
    public void setContentView(List<Cat> cats) {

        recycler.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);

        adapter.setCats(cats);
    }

    @Override
    public void setLoadingView() {

        recycler.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setErrorView() {

        recycler.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }
}
