package ru.com.gid.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SteamApi {

    @POST("/users/steam/add")
    Call<ResponseBody> loginToSteam(@Body SteamLoginModel model);

}
