package ru.com.gid.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    @GET("/api/users/{id}")
    Call<UserModel> getUserById(@Header("Authorization") String token, @Path("id") int userId);

    @GET("/api/users")
    Call<List<UserModel>> getUsers(@Header("Authorization") String token, @Query("limit") Integer limit, @Query("offset") Integer offset);

    @GET("/api/users/wished")
    Call<List<GameModel>> getUserWishedGames(@Header("Authorization") String token);

    @GET("/api/games/comments/personal")
    Call<List<CommentModel>> getUserComments(@Header("Authorization") String token);

    @POST("/api/users/register/")
    Call<ResponseBody> register(@Body LoginModel loginModel);

    @POST("/api/users/login/")
    Call<TokenResponse> login(@Body LoginModel loginModel);

    @POST("/api/users/platforms/")
    Call<ResponseBody> setPlatforms(@Header("Authorization") String token, @Body List<String> platforms);

    @POST("/api/users/wished/add")
    Call<ResponseBody> addGame(@Header("Authorization") String token, @Body Integer gameId);

    @GET("/api/users/wished/")
    Call<List<GameModel>> getWishedGames(@Header("Authorization") String token);

    @POST("/api/users/pass")
    Call<ResponseBody> changePassword(@Header("Authorization") String token, @Body ChangePasswordModel changePasswordModel);

    @POST("/api/games/all/comments/delete")
    Call<ResponseBody> deleteComment(@Header("Authorization") String token, @Body int commentId);

    @GET("/api/games/discounts")
    Call<List<DiscountModel>> getDiscounts(@Header("Authorization") String token, @Query("limit") Integer limit, @Query("Offset") Integer offset);

    @POST("/api/users/pass/recover")
    Call<ResponseBody> recoverPassword(@Body ChangePasswordModel email);

    @POST("/api/users/pass/verify_code")
    Call<ResponseBody> recoverVerifyCode(@Body ChangePasswordModel model);

    @POST("/api/users/pass/reset")
    Call<ResponseBody> resetPassword(@Body ChangePasswordModel model);

}
