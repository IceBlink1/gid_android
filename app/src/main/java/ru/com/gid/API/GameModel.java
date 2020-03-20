package ru.com.gid.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameModel {

    @SerializedName("steam_id")
    @Expose
    private Integer steamId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("genre")
    @Expose
    private List<String> genre = null;
    @SerializedName("shop_set")
    @Expose
    private List<String> shopSet = null;
    @SerializedName("price_set")
    @Expose
    private List<Double> priceSet = null;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("platforms")
    @Expose
    private List<String> platforms = null;
    @SerializedName("popularity")
    @Expose
    private Integer popularity;
    @SerializedName("header_image")
    @Expose
    private String headerImage;
    @SerializedName("is_free")
    @Expose
    private Boolean isFree;
    @SerializedName("screenshots")
    @Expose
    private List<String> screenshots = null;
    @SerializedName("required_age")
    @Expose
    private Integer requiredAge;
    @SerializedName("trailer_480")
    @Expose
    private String trailer480;
    @SerializedName("trailer_image")
    @Expose
    private String trailerImage;
    @SerializedName("developers")
    @Expose
    private List<String> developers = null;
    @SerializedName("min_requirements")
    @Expose
    private String minRequirements;
    @SerializedName("rec_requirements")
    @Expose
    private String recRequirements;
    @SerializedName("detailed_description")
    @Expose
    private String detailedDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public List<String> getShopSet() {
        return shopSet;
    }

    public void setShopSet(List<String> shopSet) {
        this.shopSet = shopSet;
    }

    public List<Double> getPriceSet() {
        return priceSet;
    }

    public void setPriceSet(List<Double> priceSet) {
        this.priceSet = priceSet;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }

    public Integer getRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(Integer requiredAge) {
        this.requiredAge = requiredAge;
    }

    public String getTrailer480() {
        return trailer480;
    }

    public void setTrailer480(String trailer480) {
        this.trailer480 = trailer480;
    }

    public String getTrailerImage() {
        return trailerImage;
    }

    public void setTrailerImage(String trailerImage) {
        this.trailerImage = trailerImage;
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    public String getMinRequirements() {
        return minRequirements;
    }

    public void setMinRequirements(String minRequirements) {
        this.minRequirements = minRequirements;
    }

    public String getRecRequirements() {
        return recRequirements;
    }

    public void setRecRequirements(String recRequirements) {
        this.recRequirements = recRequirements;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public Integer getSteamId() {
        return steamId;
    }

    public void setSteamId(Integer steamId) {
        this.steamId = steamId;
    }
}
