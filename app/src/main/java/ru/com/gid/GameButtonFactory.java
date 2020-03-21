package ru.com.gid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Picasso;

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

import ru.com.gid.API.DiscountModel;
import ru.com.gid.API.GameModel;
import ru.com.gid.Feed.FeedData;

public class GameButtonFactory {

    private final static double MARGIN_ICON_LEFT = 0.0588;
    private final static double MARGIN_ICON_BOTTOM = 0.0932;
    private final static double ICON_HEIGHT_COEF = 0.0552;
    private final static double ICON_WIDTH_COEF = 0.0736;
    private static HashMap<Integer, Bitmap> bitmapHashMap = new HashMap<>();
    private static ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();

    public static Map<GameModel, Future<Bitmap>> getUnreleasedGames(Context context, int width, int height) throws ExecutionException, InterruptedException {
        List<GameModel> games =
                threadPoolExecutor.submit(() -> {
                    try {
                        return App.getUserApi().getWishedUnreleasedGames(App.getToken()).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).get();
        return fillBitmapList(games, context, width, height);
    }

    public static FeedData getGamesOnSale() throws ExecutionException, InterruptedException {
        return threadPoolExecutor.submit(() -> {
            try {
                List<GameModel> games = new ArrayList<>();
                List<Bitmap> images = new ArrayList<>();
                List<DiscountModel> discounts = App.getGameApi().getDiscounts(App.getToken(), 100, 1).execute().body().getResults();
                for (DiscountModel discount : discounts) {
                    if (discount.getPrice() != null) {
                        GameModel gm = discount.getPrice().getGame();
                        games.add(gm);
                    } else games.add(null);
                }
                for (GameModel game : games) {
                    if (game != null) {
                        images.add(threadPoolExecutor.submit(getGameIcon(game)).get());
                    } else images.add(null);
                }
                return new FeedData(games, discounts, images);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }).get();
    }

    private static Callable<Bitmap> getGameIcon(GameModel model) {
        return () -> {
            Bitmap img = Picasso
                    .get()
                    .load(model.getHeaderImage())
                    .resize(77, 103)
                    .get();
            return img;
        };
    }


    public static Map<GameModel, Future<Bitmap>> getWishedGames(Context context, int width, int height) throws ExecutionException, InterruptedException {
        List<GameModel> games =
                threadPoolExecutor.submit(() -> {
                    try {
                        return App.getUserApi().getUserWishedGames(App.getToken()).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).get();
        return fillBitmapList(games, context, width, height);
    }

    public static Map<GameModel, Future<Bitmap>> getLibraryGames(Context context, int width, int height) throws Exception {
        List<GameModel> games =
                threadPoolExecutor.submit(() -> {
                    try {
                        return App.getSteamApi().getOwnedGames(App.getToken()).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).get();

        return fillBitmapList(games, context, width, height);
    }

    private static Map<GameModel, Future<Bitmap>> fillBitmapList(List<GameModel> games, Context context, int width, int height) {
        Map<GameModel, Future<Bitmap>> bitmaps = new HashMap<>();
        for (GameModel game :
                games) {
            bitmaps.put(game, threadPoolExecutor.submit(getGameButtonAsync(game, context, width, height)));
        }

        return bitmaps;
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
