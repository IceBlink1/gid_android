package ru.com.gid.Feed;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import ru.com.gid.GameButtonFactory;
import ru.com.gid.API.GameModel;
import ru.com.gid.R;

public class Feed extends Fragment {

    private FeedViewModel mViewModel;
    private RecyclerView recyclerViewSales;
    private SalesRVAdapter recyclerViewAdapter;
    ArrayList<GameModel> gamesOnSale;

    private GridView gamesForUser;

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
        recyclerViewSales.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewAdapter = new SalesRVAdapter(gamesOnSale, this.getContext());
        recyclerViewSales.setAdapter(recyclerViewAdapter);

        gamesForUser = view.findViewById(R.id.gamesForUserGridView);

//        try {
//           // for (int i = 0; i < 4; i++)
//                //gamesForUser.addView(GameButtonFactory.getGameButton(getActivity(), 800, 500, 500).get().getButton());
//        }
//        catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        // TODO: Use the ViewModel
    }

}
