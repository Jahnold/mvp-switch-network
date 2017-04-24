package com.jahnold.mvpswitchnetwork.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jahnold.mvpswitchnetwork.R;
import com.jahnold.mvpswitchnetwork.data.entities.Cat;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matthewarnold on 23/04/2017.
 */

class CatsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cat_image) ImageView image;
    @BindView(R.id.cat_name) TextView name;
    @BindView(R.id.cat_description) TextView description;

    CatsViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(Cat cat) {

        Picasso.with(image.getContext())
                .load(cat.getImageUrl())
                .into(image);

        name.setText(cat.getName());
        description.setText(cat.getDescription());
    }
}
