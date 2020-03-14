package ru.com.gid.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import ru.com.gid.App;
import ru.com.gid.GameModel;

public interface GameApi {
    @Headers({"Authorization: Token 15f0c18bdd0ae4972edcf46edd2ef495db9b68e3"})
    @GET("api/games/all/800")
    Call<GameModel> getGame();


}
