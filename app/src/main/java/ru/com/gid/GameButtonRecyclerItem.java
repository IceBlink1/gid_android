package ru.com.gid;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

public class GameButtonRecyclerItem extends Item {

    Bitmap bitmap;

    public GameButtonRecyclerItem(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
        viewHolder.itemView.findViewById(R.id.game_button).
                setBackground(new BitmapDrawable(viewHolder.itemView.getContext().getResources(), bitmap));

    }


    @Override
    public int getLayout() {
        return R.layout.game_button_layout_recycler_view;
    }

    @Override
    public int getSpanSize(int spanCount, int position) {
        return 2;
    }
}
