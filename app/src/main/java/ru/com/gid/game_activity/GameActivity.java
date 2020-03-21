package ru.com.gid.game_activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwray.groupie.GroupAdapter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.com.gid.api.AddGamePostModel;
import ru.com.gid.api.CommentModel;
import ru.com.gid.api.GameModel;
import ru.com.gid.App;
import ru.com.gid.GameButtonFactory;
import ru.com.gid.R;

public class GameActivity extends AppCompatActivity {

    RecyclerView videoRecyclerView;
    GameModel model;
    RecyclerView commentRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        model = (GameModel) getIntent().getSerializableExtra("model");
        videoRecyclerView = findViewById(R.id.game_page_videos_recyclerview);
        GroupAdapter videoAdapter = new GroupAdapter();
        videoRecyclerView.setAdapter(videoAdapter);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false));

        TextView title = findViewById(R.id.game_title);
        title.setText(model.getName());
        videoAdapter.add(new VideoRecyclerItem(model.getTrailerImage()));
        TextView about = findViewById(R.id.game_about_content);
        about.setText(model.getAbout());
        ImageView imageView = findViewById(R.id.game_header_imageview);
        try {
            imageView.setBackground(new BitmapDrawable(getResources(),
                    Executors.newSingleThreadExecutor().submit(() ->
                            GameButtonFactory.getBitmapFromURL(model.getHeaderImage())).get()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TextView price = findViewById(R.id.game_price_textview);
        if (model.getPriceSet().size() > 0)
            price.setText("Current price is " + model.getPriceSet().get(0).intValue() + " RUB");
        else if (model.getIsFree())
            price.setText("This game is free!");
        else
            price.setText("This game is not yet released and pre-purchase is not yet available");

        commentRecyclerView = findViewById(R.id.game_comment_recyclerview);
        GroupAdapter commentAdapter = new GroupAdapter();
        commentRecyclerView.setAdapter(commentAdapter);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        App.getGameApi().getGameComments(App.getToken(), model.getId()).enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                if (response.body() != null)
                    for (CommentModel model :
                            response.body()) {
                        commentAdapter.add(new CommentRecyclerItem(model.getContent()));
                    }
                else {
                    try {
                        Log.e("response", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Button button = findViewById(R.id.add_wishlist_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        App.getUserApi().addGame(App.getToken(),
                                new AddGamePostModel(model.getId())).
                                enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                    }
                }
        );
    }


}
