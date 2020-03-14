package ru.com.gid.Feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.com.gid.GameModel;
import ru.com.gid.R;

public class SalesRVAdapter extends RecyclerView.Adapter<SalesRVAdapter.SalesViewHolder> {
    private ArrayList<GameModel> games;
    private Context context;

    public SalesRVAdapter(ArrayList<GameModel> games, Context context) {
        this.games = games;
        this.context = context;
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

        holder.setGame(game);
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
        }

        public void setGame(GameModel game) {
            gameName.setText(game.getName());
            // TODO take info about sale prices and platform and add it to row
            // TODO take image and add it to row
            setPlatforms(game);
        }

        public void setPlatforms(GameModel game) {
            List<String> platforms = game.getPlatforms();

            for (String platform: platforms) {
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