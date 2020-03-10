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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GameButtonFactory {

    private static List<ImageButtonWrapper> buttonWrapperList = new ArrayList<>();
    private static ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();

    private final static double MARGIN_ICON_LEFT = 0.0588;
    private final static double MARGIN_ICON_BOTTOM = 0.0932;
    private final static double ICON_HEIGHT_COEF = 0.0552;
    private final static double ICON_WIDTH_COEF = 0.0736;

    public static Future<ImageButtonWrapper> getGameButton(Context context, int id, int width, int height) throws IOException {
        return threadPoolExecutor.submit(getGameButtonAsync(context, id, width, height));
    }

    private static Callable<ImageButtonWrapper> getGameButtonAsync(final Context context, final int id, final int width, final int height) throws IOException {

        final ImageButtonWrapper contains = findButtonById(id);
        if (contains != null)
            return new Callable<ImageButtonWrapper>() {
                @Override
                public ImageButtonWrapper call() throws Exception {
                    return contains;
                }
            };
        return new Callable<ImageButtonWrapper>() {
            @Override
            public ImageButtonWrapper call() throws Exception {
                GameModel model = App.getApi().getGame().execute().body();
                Bitmap background = getBitmapFromURL(model.getHeaderImage());
                ImageButton ib = new ImageButton(context);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.STROKE);

                background = Bitmap.createScaledBitmap(background, width, height, false);
                background = background.copy(background.getConfig(), true);

                Bitmap windowsIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_windows)
                        .copy(background.getConfig(), true);

                windowsIcon = Bitmap.createScaledBitmap(windowsIcon, (int) (ICON_HEIGHT_COEF * height),
                        (int) (ICON_WIDTH_COEF * width), false);

                Canvas canvas = new Canvas(background);
                canvas.drawBitmap(windowsIcon, (int) (canvas.getWidth() * MARGIN_ICON_LEFT),
                        (int) (canvas.getHeight() * (1 - MARGIN_ICON_BOTTOM)), paint);
                ib.setBackground(new BitmapDrawable(context.getResources(), background));
                buttonWrapperList.add(new ImageButtonWrapper(ib, id));
                return buttonWrapperList.get(buttonWrapperList.size() - 1);
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
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    private static ImageButtonWrapper findButtonById(int id) {
        for (int i = 0; i < buttonWrapperList.size(); i++) {
            if (buttonWrapperList.get(i).getId() == id)
                return buttonWrapperList.get(i);
        }
        return null;
    }

    public static class ImageButtonWrapper {

        ImageButton button;
        int id;

        public ImageButtonWrapper(ImageButton button, int id) {
            this.button = button;
            this.id = id;
        }

        public ImageButton getButton() {
            return button;
        }

        public int getId() {
            return id;
        }
    }
}
