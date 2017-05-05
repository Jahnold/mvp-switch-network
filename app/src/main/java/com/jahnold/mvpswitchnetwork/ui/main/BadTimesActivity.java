package com.jahnold.mvpswitchnetwork.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.jahnold.mvpswitchnetwork.App;
import com.jahnold.mvpswitchnetwork.R;
import com.jahnold.mvpswitchnetwork.data.network.retrofit.RetrofitCatsHttpInterface;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Retrofit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BadTimesActivity extends AppCompatActivity {

    @BindView(R.id.cats_recycler) RecyclerView recycler;
    @BindView(R.id.cats_error) LinearLayout errorView;
    @BindView(R.id.cats_loading) LinearLayout loadingView;

    private CatsAdapter adapter;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_times);

        initRecycler();
        getCatsData();
    }

    @Override
    protected void onDestroy() {

        subscriptions.clear();
        super.onDestroy();
    }

    private void initRecycler() {

        adapter = new CatsAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

    @OnClick(R.id.cats_retry_button)
    void onRetryClick() {

        getCatsData();
    }

    private void getCatsData() {

        Retrofit retrofit = App.getInstance().getRetrofit();
        RetrofitCatsHttpInterface http = retrofit.create(RetrofitCatsHttpInterface.class);

        recycler.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);

        Subscription s = http
                .getCats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            if (result.isSuccessful()) {

                                recycler.setVisibility(View.VISIBLE);
                                errorView.setVisibility(View.GONE);
                                loadingView.setVisibility(View.GONE);

                                adapter.setCats(result.body());
                            }
                            else {

                                recycler.setVisibility(View.GONE);
                                errorView.setVisibility(View.VISIBLE);
                                loadingView.setVisibility(View.GONE);
                            }
                        },
                        throwable -> {
                            throwable.printStackTrace();

                            recycler.setVisibility(View.GONE);
                            errorView.setVisibility(View.VISIBLE);
                            loadingView.setVisibility(View.GONE);
                        }
                );
        subscriptions.add(s);
    }
}
