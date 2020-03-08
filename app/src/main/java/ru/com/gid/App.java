package ru.com.gid;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.com.gid.API.GameApi;

public class App extends Application {

    private static GameApi umoriliApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://80.87.200.190:8000") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        umoriliApi = retrofit.create(GameApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static GameApi getApi() {
        return umoriliApi;
    }

}
