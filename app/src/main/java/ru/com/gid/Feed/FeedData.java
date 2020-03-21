package ru.com.gid.Feed;

import android.graphics.Bitmap;

import java.util.List;

import ru.com.gid.API.DiscountModel;
import ru.com.gid.API.GameModel;

public class FeedData {
    private List<GameModel> gamesOnSale;
    private List<DiscountModel> gamesOnSaleD;
    private List<Bitmap> images;

    public FeedData(List<GameModel> gm, List<DiscountModel> dm, List<Bitmap> img) {
        this.gamesOnSale = gm;
        this.gamesOnSaleD = dm;
        this.images = img;
    }

    public List<Bitmap> getImages() {
        return images;
    }

    public List<DiscountModel> getGamesOnSaleD() {
        return gamesOnSaleD;
    }

    public List<GameModel> getGamesOnSale() {
        return gamesOnSale;
    }
}
