package ru.com.gid.API;

import android.provider.Telephony;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RateGameModel {

    @Expose
    @SerializedName("steam_id")
    private Integer steamId;

    @Expose
    @SerializedName("mark")
    private Integer mark;

    public RateGameModel(Integer steamId, Integer mark) {
        this.steamId = steamId;
        this.mark = mark;
    }

    public Integer getSteamId() {
        return steamId;
    }

    public void setSteamId(Integer steamId) {
        this.steamId = steamId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
