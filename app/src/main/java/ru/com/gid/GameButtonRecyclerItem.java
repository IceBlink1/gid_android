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
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
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
        ImageView button = viewHolder.itemView.findViewById(R.id.game_image);
        CardView cw = viewHolder.itemView.findViewById(R.id.game_button_card_view);
        cw.setPreventCornerOverlap(false);
        Glide.with(viewHolder.itemView.getContext()).load(model.getHeaderImage()).into(button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), GameActivity.class);
            intent.putExtra("model", model);
            v.getContext().startActivity(intent);
        });
        LinearLayout iconLayout = viewHolder.itemView.findViewById(R.id.icon_linear_layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 10, 10, 10);

        iconLayout.removeAllViews();

        if (model.getPlatforms().contains("windows")) {
            ImageView icon = new ImageView(viewHolder.itemView.getContext());
            icon.setBackgroundResource(R.drawable.ic_windows);
            iconLayout.addView(icon, layoutParams);
        }

        if (model.getPlatforms().contains("mac")) {
            ImageView icon = new ImageView(viewHolder.itemView.getContext());
            icon.setBackgroundResource(R.drawable.ic_mac);
            iconLayout.addView(icon, layoutParams);
        }


        if (model.getPlatforms().contains("linux")) {
            ImageView icon = new ImageView(viewHolder.itemView.getContext());
            icon.setBackgroundResource(R.drawable.ic_linux);
            iconLayout.addView(icon, layoutParams);
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