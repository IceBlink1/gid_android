package ru.com.gid.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.com.gid.GameModel;

public interface GameApi {
    @GET("/api/games/all/1000")
    Call<List<GameModel>> getAllGames();
}
