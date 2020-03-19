package ru.com.gid.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscountModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("old_price")
    @Expose
    private Integer oldPrice;
    @SerializedName("new_price")
    @Expose
    private Integer newPrice;
    @SerializedName("price")
    @Expose
    private PriceModel price;
    @SerializedName("percent")
    @Expose
    private Integer percent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Integer newPrice) {
        this.newPrice = newPrice;
    }

    public PriceModel getPrice() {
        return price;
    }

    public void setPrice(PriceModel price) {
        this.price = price;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

}
