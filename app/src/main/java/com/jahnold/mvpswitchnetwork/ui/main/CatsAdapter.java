package com.jahnold.mvpswitchnetwork.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jahnold.mvpswitchnetwork.R;
import com.jahnold.mvpswitchnetwork.data.entities.Cat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by matthewarnold on 23/04/2017.
 */

public class CatsAdapter extends RecyclerView.Adapter<CatsViewHolder> {

    private List<Cat> cats;

    public CatsAdapter() {
        cats = new ArrayList<>();
    }

    @Override
    public CatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_cat, parent, false);

        return new CatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatsViewHolder holder, int position) {

        Cat cat = cats.get(position);
        holder.bind(cat);
    }

    @Override
    public int getItemCount() {

        return cats != null ? cats.size() : 0;
    }

    public void setCats(List<Cat> cats) {

        this.cats = cats != null
                ? cats
                : Collections.emptyList();

        notifyDataSetChanged();
    }
}
