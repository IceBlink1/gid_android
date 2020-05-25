package ru.com.gid.feed;

import android.graphics.Bitmap;

import java.util.List;

import ru.com.gid.api.DiscountModel;
import ru.com.gid.api.GameModel;

public class FeedData {
    private List<GameModel> gamesOnSale;
    private List<DiscountModel> gamesOnSaleD;

    public FeedData(List<GameModel> gm, List<DiscountModel> dm) {
        this.gamesOnSale = gm;
        this.gamesOnSaleD = dm;
    }

    public List<DiscountModel> getGamesOnSaleD() {
        return gamesOnSaleD;
    }

    public List<GameModel> getGamesOnSale() {
        return gamesOnSale;
    }
}