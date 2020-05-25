package ru.com.gid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.com.gid.api.DiscountModel;
import ru.com.gid.api.DiscountModelOffsetLimit;
import ru.com.gid.api.GameModel;
import ru.com.gid.feed.FeedData;
import ru.com.gid.feed.FeedViewModel;
import ru.com.gid.profile.ProfileViewModel;

public class GameButtonFactory {

    private final static double MARGIN_ICON_LEFT = 0.0588;
    private final static double MARGIN_ICON_BOTTOM = 0.0932;
    private final static double ICON_HEIGHT_COEF = 0.0552;
    private final static double ICON_WIDTH_COEF = 0.0736;
    private static HashMap<Integer, Bitmap> bitmapHashMap = new HashMap<>();
    private static ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();

    public static void getUnreleasedGames(ProfileViewModel vm) {

        App.getUserApi().getWishedUnreleasedGames(App.getToken()).enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<GameModel>> call, @NotNull Response<List<GameModel>> response) {
                if (response.isSuccessful()) {
                    vm.postUnreleasedGames(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<GameModel>> call, @NotNull Throwable t) {

            }
        });
    }

    public static void getGamesOnSale(FeedViewModel vm) {
        List<GameModel> games = new ArrayList<>();
        App.getGameApi().getDiscounts(App.getToken(), 100, 1).enqueue(new Callback<DiscountModelOffsetLimit>() {
            @Override
            public void onResponse(@NotNull Call<DiscountModelOffsetLimit> call, @NotNull Response<DiscountModelOffsetLimit> response) {
                if (response.isSuccessful()) {
                    List<DiscountModel> discounts = response.body().getResults();
                    for (DiscountModel discount : discounts) {
                        if (discount.getPrice() != null) {
                            GameModel gm = discount.getPrice().getGame();
                            games.add(gm);
                        } else games.add(null);
                    }

                    vm.post(new FeedData(games, discounts));
                }
            }

            @Override
            public void onFailure(@NotNull Call<DiscountModelOffsetLimit> call, @NotNull Throwable t) {
                Log.d("Discounts", "Discounts failed " + t.getMessage());
            }
        });

    }


    public static void getWishedGames(ProfileViewModel vm) {
        App.getUserApi().getUserWishedGames(App.getToken()).enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<GameModel>> call, @NotNull Response<List<GameModel>> response) {
                if (response.isSuccessful()) {
                    vm.postWishedGames(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<GameModel>> call, @NotNull Throwable t) {

            }
        });
    }

    public static void getLibraryGames(ProfileViewModel vm) {
        App.getSteamApi().getOwnedGames(App.getToken()).enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<GameModel>> call, @NotNull Response<List<GameModel>> response) {
                if (response.isSuccessful()) {
                    vm.postLibraryGames(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<GameModel>> call, @NotNull Throwable t) {

            }
        });
    }

    private static Callable<Bitmap> getGameButtonAsync(final GameModel model, final Context context, final int width, final int height) {

        if (bitmapHashMap.containsKey(model.getId()))
            return () -> bitmapHashMap.get(model.getId());

        return () -> {
            Bitmap background = getBitmapFromURL(model.getHeaderImage());
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);

            background = Bitmap.createScaledBitmap(background, width, height, false);
            background = background.copy(background.getConfig(), true);
            Canvas canvas = new Canvas(background);
            int iconCount = 0;
            if (model.getPlatforms().contains("windows")) {
                Bitmap windowsIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_windows)
                        .copy(background.getConfig(), true);
                windowsIcon = Bitmap.createScaledBitmap(windowsIcon, (int) (ICON_HEIGHT_COEF * height),
                        (int) (ICON_WIDTH_COEF * width), false);

                canvas.drawBitmap(windowsIcon, (int) (canvas.getWidth() * MARGIN_ICON_LEFT
                                + width * ICON_WIDTH_COEF * iconCount),
                        (int) (canvas.getHeight() * (1 - MARGIN_ICON_BOTTOM)), paint);
                iconCount++;
            }

            if (model.getPlatforms().contains("mac")) {
                Bitmap macIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_mac)
                        .copy(background.getConfig(), true);
                macIcon = Bitmap.createScaledBitmap(macIcon, (int) (ICON_HEIGHT_COEF * height),
                        (int) (ICON_WIDTH_COEF * width), false);

                canvas.drawBitmap(macIcon, (int) (canvas.getWidth() * MARGIN_ICON_LEFT
                                + width * ICON_WIDTH_COEF * iconCount),
                        (int) (canvas.getHeight() * (1 - MARGIN_ICON_BOTTOM)), paint);
                iconCount++;
            }

            if (model.getPlatforms().contains("linux")) {
                Bitmap linuxIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_linux)
                        .copy(background.getConfig(), true);
                linuxIcon = Bitmap.createScaledBitmap(linuxIcon, (int) (ICON_HEIGHT_COEF * height),
                        (int) (ICON_WIDTH_COEF * width), false);

                canvas.drawBitmap(linuxIcon, (int) (canvas.getWidth() * MARGIN_ICON_LEFT
                                + width * ICON_WIDTH_COEF * iconCount),
                        (int) (canvas.getHeight() * (1 - MARGIN_ICON_BOTTOM)), paint);
                //iconCount++;
            }
            bitmapHashMap.put(model.getId(), background);
            return background;
        };

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}