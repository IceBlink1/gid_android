package ru.com.gid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

import ru.com.gid.api.GameModel;
import ru.com.gid.game_activity.GameActivity;

public class GameButtonRecyclerItem extends Item {

    GameModel model;

    public GameButtonRecyclerItem(GameModel model) {
        this.model = model;
    }

    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
        ImageButton button = viewHolder.itemView.findViewById(R.id.game_image);
        Picasso.get().load(model.getHeaderImage()).into(button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), GameActivity.class);
            intent.putExtra("model", model);
            v.getContext().startActivity(intent);
        });
        LinearLayout iconLayout = viewHolder.itemView.findViewById(R.id.icon_linear_layout);
        if (model.getPlatforms().contains("mac")) {
            ImageView icon = new ImageView(viewHolder.itemView.getContext());
            icon.setBackgroundResource(R.drawable.ic_mac);

            iconLayout.addView(icon);
        }
        if (model.getPlatforms().contains("windows")) {
            ImageView icon = new ImageView(viewHolder.itemView.getContext());
            icon.setBackgroundResource(R.drawable.ic_windows);
            iconLayout.addView(icon);
        }

        if (model.getPlatforms().contains("linux")) {
            ImageView icon = new ImageView(viewHolder.itemView.getContext());
            icon.setBackgroundResource(R.drawable.ic_linux);
            iconLayout.addView(icon);
        }
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