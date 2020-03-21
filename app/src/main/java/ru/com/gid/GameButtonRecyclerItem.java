package ru.com.gid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

import ru.com.gid.api.GameModel;
import ru.com.gid.game_activity.GameActivity;

public class GameButtonRecyclerItem extends Item {

    Bitmap bitmap;
    GameModel model;

    public GameButtonRecyclerItem(Bitmap bitmap, GameModel model) {

        this.bitmap = bitmap;
        this.model = model;
    }

    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
        ImageButton button = viewHolder.itemView.findViewById(R.id.game_button);
        button.setBackground(new BitmapDrawable(viewHolder.itemView.getContext().getResources(), bitmap));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                intent.putExtra("model", model);
                v.getContext().startActivity(intent);
            }
        });
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
