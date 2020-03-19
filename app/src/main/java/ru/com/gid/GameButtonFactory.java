package ru.com.gid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ru.com.gid.API.GameModel;

public class GameButtonFactory {

    private static HashMap<Integer, Bitmap> bitmapHashMap = new HashMap<Integer, Bitmap>();
    private static ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();

    private final static double MARGIN_ICON_LEFT = 0.0588;
    private final static double MARGIN_ICON_BOTTOM = 0.0932;
    private final static double ICON_HEIGHT_COEF = 0.0552;
    private final static double ICON_WIDTH_COEF = 0.0736;

    public static Future<ImageButton> getGameButton(Context context, int id, int width, int height) throws IOException {
        return threadPoolExecutor.submit(getGameButtonAsync(context, id, width, height));
    }

    private static Callable<ImageButton> getGameButtonAsync(final Context context, final int id, final int width, final int height) {

        if (bitmapHashMap.containsKey(id))
            return new Callable<ImageButton>() {
                @Override
                public ImageButton call() throws Exception {
                    ImageButton ib = new ImageButton(context);
                    ib.setBackground(new BitmapDrawable(context.getResources(), bitmapHashMap.get(id)));
                    return ib;
                }
            };

        return new Callable<ImageButton>() {
            @Override
            public ImageButton call() throws Exception {
                GameModel model = App.getGameApi().getGameById("Token " + App.getToken(), id).execute().body();
                Bitmap background = getBitmapFromURL(model.getHeaderImage());
                ImageButton ib = new ImageButton(context);
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

                ib.setBackground(new BitmapDrawable(context.getResources(), background));
                bitmapHashMap.put(id, background);
                return ib;
            }
        };

    }

    private static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
