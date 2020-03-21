package ru.com.gid.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AddGamePostModel {

    @Expose
    @SerializedName("game_id")
    private Integer id;

    public AddGamePostModel(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
