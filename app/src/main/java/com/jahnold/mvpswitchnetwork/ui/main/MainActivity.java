package com.jahnold.mvpswitchnetwork.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jahnold.mvpswitchnetwork.App;
import com.jahnold.mvpswitchnetwork.R;
import com.jahnold.mvpswitchnetwork.data.entities.Cat;
import com.jahnold.mvpswitchnetwork.data.network.CatsHttp;
import com.jahnold.mvpswitchnetwork.data.network.retrofit.RetrofitCatsHttp;
import com.jahnold.mvpswitchnetwork.data.repositories.CatsRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.cats_recycler) RecyclerView recycler;

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

        Retrofit retrofit = App.getInstance().getRetrofit();
        CatsHttp catsHttp = new RetrofitCatsHttp(retrofit);
        CatsRepository catsRepository = new CatsRepository(catsHttp);

        presenter = new MainPresenter(catsRepository);
        presenter.bindView(this);
    }

    @Override
    public void setContentView(List<Cat> cats) {

        adapter.setCats(cats);
    }

    @Override
    public void setLoadingView() {

    }

    @Override
    public void setErrorView() {

    }
}
