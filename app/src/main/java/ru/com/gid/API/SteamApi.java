package ru.com.gid.API;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SteamApi {

    @GET("api/users/steam/owned/")
    Call<List<GameModel>> getOwnedGames(@Header("Authorization") String token);

    @POST("api/users/steam/add/")
    Call<ResponseBody> loginToSteam(@Header("Authorization") String token, @Body SteamLoginModel model);

}
