package ru.com.gid.Feed;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwray.groupie.GroupAdapter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import ru.com.gid.GameButtonFactory;
import ru.com.gid.GameButtonRecyclerItem;
import ru.com.gid.API.GameModel;
import ru.com.gid.R;

public class Feed extends Fragment {

    private FeedViewModel mViewModel;
    private RecyclerView recyclerViewSales;
    private SalesRVAdapter recyclerViewAdapter;


    private RecyclerView gamesForUser;

    public static Feed newInstance() {
        return new Feed();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feed_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        gamesForUser = view.findViewById(R.id.gamesForUserRV);
        gamesForUser.setNestedScrollingEnabled(false);
        GroupAdapter ga = new GroupAdapter();
        gamesForUser.setLayoutManager(new GridLayoutManager(getContext(), 2));


        try {
            Map<GameModel, Future<Bitmap>> gameBitmaps = GameButtonFactory.getWishedGames(getActivity(), 500, 500);
            for (Map.Entry<GameModel, Future<Bitmap>> entry:
                 gameBitmaps.entrySet()) {
                ga.add(new GameButtonRecyclerItem(entry.getValue().get(), entry.getKey()));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        gamesForUser.setAdapter(ga);

        FeedData feed = null;
        try {
            feed = GameButtonFactory.getGamesOnSale();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        recyclerViewSales = view.findViewById(R.id.reciclerViewSales);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewSales.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable dr = getResources().getDrawable(R.drawable.divider);
        dividerItemDecoration.setDrawable(dr);

        recyclerViewSales.addItemDecoration(dividerItemDecoration);
        recyclerViewSales.setLayoutManager(new LinearLayoutManager(this.getContext()));

        recyclerViewAdapter = new SalesRVAdapter(feed, this.getContext());
        recyclerViewSales.setAdapter(recyclerViewAdapter);
        recyclerViewSales.setNestedScrollingEnabled(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        // TODO: Use the ViewModel
    }

}
