package ru.com.gid.GameActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.com.gid.API.GameModel;
import ru.com.gid.GameButtonFactory;
import ru.com.gid.R;

public class VideoRecyclerItem extends Item {

    String bitmapUrl;


    public VideoRecyclerItem(String bitmapUrl) {
        this.bitmapUrl = bitmapUrl;
    }

    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
        CardView cw = viewHolder.itemView.findViewById(R.id.video_cardview);

        try {
            cw.setBackground(new BitmapDrawable(viewHolder.itemView.getResources(),
                    Executors.newSingleThreadExecutor().submit(() -> GameButtonFactory.getBitmapFromURL(bitmapUrl)).get()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getLayout() {
        return R.layout.game_video_recycler_item;
    }
}
