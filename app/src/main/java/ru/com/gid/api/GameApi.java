package ru.com.gid.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameApi {
    @GET("api/games/all/{id}")
    Call<GameModel> getGameById(@Header("Authorization") String token, @Path("id") int id);

    @GET("api/games/all")
    Call<List<GameModel>> getGameWithFilters(@Header("Authorization") String token,
                                             @Query("name") String name,
                                             @Query("genres") List<String> genres,
                                             @Query("shop") List<String> shops,
                                             @Query("min_price") Double minPrice,
                                             @Query("max_price") Double maxPrice,
                                             @Query("popularity_order") String popularityOrder, //priceOrder and popularityOrder should only have values "ascending" or "descending"
                                             @Query("price_order") String priceOrder,
                                             @Query("limit") Integer limit,
                                             @Query("offset") Integer offset);

    @GET("api/games/all/{id}/comments")
    Call<List<CommentModel>> getGameComments(@Header("Authorization") String token, @Path("id") int id);

    @GET("api/games/discounts")
    Call<DiscountModelOffsetLimit> getDiscounts(@Header("Authorization") String token, @Query("limit") Integer limit, @Query("offset") Integer offset);

    @GET("api/articles")
    Call<List<ArticleModel>> getArticles(@Header("Authorization") String token, @Query("steam_id") Integer steamId);

    @GET("api/articles/?personal")
    Call<List<ArticleModel>> getUserArticles(@Header("Autorization") String token);

    @POST("api/games/all/rate/")
    Call<ResponseBody> rateGame(@Body RateGameModel model);
}
