package ru.com.gid.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("game")
    @Expose
    private GameModel game;
    @SerializedName("shop")
    @Expose
    private ShopModel shop;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("initial_formatted")
    @Expose
    private String initialFormatted;
    @SerializedName("final_formatted")
    @Expose
    private String finalFormatted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public GameModel getGame() {
        return game;
    }

    public void setGame(GameModel game) {
        this.game = game;
    }

    public ShopModel getShop() {
        return shop;
    }

    public void setShop(ShopModel shop) {
        this.shop = shop;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInitialFormatted() {
        return initialFormatted;
    }

    public void setInitialFormatted(String initialFormatted) {
        this.initialFormatted = initialFormatted;
    }

    public String getFinalFormatted() {
        return finalFormatted;
    }

    public void setFinalFormatted(String finalFormatted) {
        this.finalFormatted = finalFormatted;
    }

}
