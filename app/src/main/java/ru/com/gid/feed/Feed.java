package ru.com.gid.feed;

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

import java.util.Objects;

import ru.com.gid.GameButtonFactory;
import ru.com.gid.GameButtonRecyclerItem;
import ru.com.gid.R;
import ru.com.gid.api.GameModel;
import ru.com.gid.profile.ProfileViewModel;

public class Feed extends Fragment {

    private FeedViewModel feedViewModel;
    private ProfileViewModel profileViewModel;
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
        recyclerViewSales = view.findViewById(R.id.reciclerViewSales);

        gamesForUser = view.findViewById(R.id.gamesForUserRV);
        gamesForUser.setNestedScrollingEnabled(false);
        gamesForUser.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
        GameButtonFactory.getWishedGames(profileViewModel);
        profileViewModel.getWishedGames().observe(getViewLifecycleOwner(), gameModels -> {
            if (!gameModels.isEmpty()) {
                recyclerViewSales = getActivity().findViewById(R.id.wishlist_recyclerview);
                GroupAdapter ga = new GroupAdapter();
                for (GameModel game : Objects.requireNonNull(profileViewModel.getWishedGames().getValue())) {
                    ga.add(new GameButtonRecyclerItem(game));
                }
                gamesForUser.setAdapter(ga);
            }
        });

        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        // TODO: Use the ViewModel
        GameButtonFactory.getGamesOnSale(feedViewModel);
        feedViewModel.feedData.observe(getViewLifecycleOwner(), feedData -> {
            FeedData feed = feedData;
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewSales.getContext(),
                    DividerItemDecoration.VERTICAL);
            Drawable dr = getResources().getDrawable(R.drawable.divider);
            dividerItemDecoration.setDrawable(dr);

            recyclerViewSales.addItemDecoration(dividerItemDecoration);
            recyclerViewSales.setLayoutManager(new LinearLayoutManager(Feed.this.getContext()));

            recyclerViewAdapter = new SalesRVAdapter(feed, Feed.this.getContext());
            recyclerViewSales.setAdapter(recyclerViewAdapter);
            recyclerViewSales.setNestedScrollingEnabled(false);
        });
    }

}