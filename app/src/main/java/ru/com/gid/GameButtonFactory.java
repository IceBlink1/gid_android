package ru.com.gid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;

public class GameButtonFactory {

    public static ImageButton getGameButton(Context context) {
        ImageButton ib = new ImageButton(context);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        Bitmap background = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_mac).copy(Bitmap.Config.ARGB_8888, true);

        Bitmap windowsIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_windows).copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(background);

        canvas.drawBitmap(windowsIcon, 0, 0, paint);
        ib.setBackground(new BitmapDrawable(context.getResources(), background));
        return ib;
    }

}
