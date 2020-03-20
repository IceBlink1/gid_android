package ru.com.gid;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.com.gid.API.GameApi;
import ru.com.gid.API.SteamApi;
import ru.com.gid.API.UserApi;

public class App extends Application {

    private static String token;
    private static GameApi gameApi;
    private static UserApi userApi;
    private static SteamApi steamApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://80.87.200.190:8000/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        gameApi = retrofit.create(GameApi.class); //Создаем объект, при помощи которого будем выполнять запросы
        userApi = retrofit.create(UserApi.class);
        steamApi = retrofit.create(SteamApi.class);
        token = "Token 1783f0bf36fea2ee45fb134bfed644d309c052c2";
    }

    public static SteamApi getSteamApi() {
        return steamApi;
    }

    public static GameApi getGameApi() {
        return gameApi;
    }

    public static UserApi getUserApi() {
        return userApi;
    }


    public static String getToken() {
        return token;
    }
}
