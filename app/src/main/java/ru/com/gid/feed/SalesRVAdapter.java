package ru.com.gid.feed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.com.gid.api.DiscountModel;
import ru.com.gid.api.GameModel;
import ru.com.gid.game_activity.GameActivity;
import ru.com.gid.R;

public class SalesRVAdapter extends RecyclerView.Adapter<SalesRVAdapter.SalesViewHolder> {
    private List<GameModel> games;
    private List<DiscountModel> discounts;
    private Context context;
    private List<Bitmap> images;

    public SalesRVAdapter(List<GameModel> games, List<DiscountModel> discounts, List<Bitmap> images, Context context) {
        this.games = games;
        this.context = context;
        this.images = images;
        this.discounts = discounts;
    }

    public SalesRVAdapter(FeedData feed, Context context) {
        this.games = feed.getGamesOnSale();
        this.context = context;
        this.images = feed.getImages();
        this.discounts = feed.getGamesOnSaleD();
    }

    @NonNull
    @Override
    public SalesRVAdapter.SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.recycler_view_sale_element, parent, false);
        return new SalesRVAdapter.SalesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesRVAdapter.SalesViewHolder holder, int position) {

        final GameModel game = games.get(position);
        final Bitmap image = images.get(position);
        final DiscountModel discount = discounts.get(position);
        holder.setGame(game, discount, image);

    }

    @Override
    public int getItemCount() {
        return games == null ? 0 : games.size();
    }

    public class SalesViewHolder extends RecyclerView.ViewHolder {

        private TextView gameName;
        private ImageView gameImg;
        private TextView oldPrice, newPrice;

        private ImageView platfLinux, platfMac, platfWindows;
        private ImageView platfSale;

        public SalesViewHolder(@NonNull View itemView) {
            super(itemView);

            gameName = itemView.findViewById(R.id.gameNameSale);
            gameImg = itemView.findViewById(R.id.imgSale);
            oldPrice = itemView.findViewById(R.id.oldPrice);
            newPrice = itemView.findViewById(R.id.newPrice);
            platfLinux = itemView.findViewById(R.id.platformsLinux);
            platfMac = itemView.findViewById(R.id.platformsMac);
            platfWindows = itemView.findViewById(R.id.platformsWindows);

            platfSale = itemView.findViewById(R.id.platfSale);

            oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

        public void setGame(GameModel game, DiscountModel discount, Bitmap image) {
            if (game == null) {
                RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
                itemView.setVisibility(View.GONE);
                param.height = 0;
                param.width = 0;
                return;
            }
            gameName.setText(game.getName());

            oldPrice.setText(String.valueOf(discount.getOldPrice()));
            newPrice.setText(String.valueOf(discount.getNewPrice()));

            gameImg.setImageBitmap(image);
            gameImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), GameActivity.class);
                    intent.putExtra("model", game);
                    v.getContext().startActivity(intent);
                }
            });
            setPlatforms(game);
        }

        public void setPlatforms(GameModel game) {
            List<String> platforms = game.getPlatforms();

            for (String platform : platforms) {
                if (platform.equals("Linux"))
                    platfLinux.setVisibility(View.VISIBLE);
                if (platform.equals("Mac"))
                    platfMac.setVisibility(View.VISIBLE);
                if (platform.equals("Windows"))
                    platfWindows.setVisibility(View.VISIBLE);
            }
        }
    }


}