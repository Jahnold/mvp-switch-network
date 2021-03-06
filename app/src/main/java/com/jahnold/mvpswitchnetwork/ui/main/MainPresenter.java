package com.jahnold.mvpswitchnetwork.ui.main;

import com.jahnold.mvpswitchnetwork.data.network.Data;
import com.jahnold.mvpswitchnetwork.data.repositories.CatsRepository;
import com.jahnold.mvpswitchnetwork.ui.common.Presenter;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by matthewarnold on 16/10/2016.
 */

class MainPresenter extends Presenter<MainView> {

    private CatsRepository catsRepository;

    MainPresenter(CatsRepository catsRepository) {
        this.catsRepository = catsRepository;
    }

    void init() {

        subscribeToCats();
    }

    private void subscribeToCats() {

        Subscription s = catsRepository
                .getCats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        catData -> {
                            if (catData.status == Data.LOADING) {
                                view().setLoadingView();
                            }
                            if (catData.status == Data.SUCCESS) {
                                view().setContentView(catData.result);
                            }
                            if (catData.status == Data.ERROR) {
                                view().setErrorView();
                            }
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            view().setErrorView();
                        }
                );
        unsubscribeOnUnbindView(s);
    }

    void tryAgain() {

        clearSubscriptions();
        subscribeToCats();
    }
}
