package com.jahnold.mvpswitchnetwork.ui.common;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by matthewarnold on 16/10/2016.
 */
public class Presenter<V> {

    @NonNull
    private final CompositeSubscription subscriptions = new CompositeSubscription();

    private boolean isNoOpViewBound = false;
    private volatile V view;

    @NonNull
    protected V view() {

        if (view == null) {
            throw new IllegalStateException("You've not bound a view...numpty");
        }

        return view;
    }

    @CallSuper
    public void bindView(@NonNull V view) {

        final V previousView = this.view;

        if (previousView != null) {
            if (isNoOpViewBound) {
                this.view = null;
                isNoOpViewBound = false;
            }
            else {
                throw new IllegalStateException("Previous view is not unbounded! previousView = " + previousView);
            }
        }

        this.view = view;
    }

    @SuppressWarnings("unchecked")
    @CallSuper
    public void unbindView(@NonNull V view) {

        final V previousView = this.view;

        if (previousView == view) {

            // Bit of magic here
            // When the view is detached, reflection is used to determine the view interface
            // Then a "No Operation" null object is created which simply does nothing
            // This way null checks on the view are not required in the presenters
            Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
            Class<V> viewClass = (Class<V>) types[0];
            this.view = NoOp.of(viewClass);
            isNoOpViewBound = true;
        }
        else {
            throw new IllegalStateException("Unexpected view! previousView = " + previousView + ", view to unbind = " + view);
        }

        subscriptions.clear();
    }

    protected final void unsubscribeOnUnbindView(@NonNull Subscription subscription, @NonNull Subscription... subscriptions) {
        this.subscriptions.add(subscription);

        for (Subscription s : subscriptions) {
            this.subscriptions.add(s);
        }
    }

    public final void clearSubscriptions() {
        subscriptions.clear();
    }

}
