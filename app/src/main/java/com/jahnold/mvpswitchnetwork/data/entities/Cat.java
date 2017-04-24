package com.jahnold.mvpswitchnetwork.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewarnold on 16/10/2016.
 */

public class Cat {

    @SerializedName("name")
    private String name;

    @SerializedName("desc")
    private String description;

    @SerializedName("image")
    private String imageUrl;

    public String getName() {return name;}
    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
