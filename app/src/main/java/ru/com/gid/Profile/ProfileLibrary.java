package ru.com.gid.Profile;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwray.groupie.GroupAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import ru.com.gid.API.GameModel;
import ru.com.gid.GameButtonFactory;
import ru.com.gid.GameButtonRecyclerItem;
import ru.com.gid.R;

public class ProfileLibrary extends Fragment {

    private ProfileLibraryViewModel mViewModel;
    private RecyclerView recyclerView;
    private GroupAdapter adapter;

    public static ProfileLibrary newInstance() {
        return new ProfileLibrary();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_library, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.library_recyclerview);

        if(savedInstanceState == null)
            adapter = new GroupAdapter();
        try {
            Map<GameModel, Future<Bitmap>> gameBitmaps = GameButtonFactory.getLibraryGames(getActivity(), 500, 500);
            for (Map.Entry<GameModel, Future<Bitmap>> entry : gameBitmaps.entrySet()) {
                adapter.add(new GameButtonRecyclerItem(entry.getValue().get(), entry.getKey()));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mViewModel = ViewModelProviders.of(this).get(ProfileLibraryViewModel.class);
        // TODO: Use the ViewModel
    }

}
