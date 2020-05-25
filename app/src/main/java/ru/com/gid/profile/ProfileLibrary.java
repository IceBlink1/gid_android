package ru.com.gid.profile;

import androidx.lifecycle.Observer;
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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import ru.com.gid.api.GameModel;
import ru.com.gid.GameButtonFactory;
import ru.com.gid.GameButtonRecyclerItem;
import ru.com.gid.R;

public class ProfileLibrary extends Fragment {

    private ProfileViewModel mViewModel;
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

        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
        GameButtonFactory.getLibraryGames(mViewModel);
        mViewModel.libraryGames.observe(getViewLifecycleOwner(), gameModels -> {
            if (!gameModels.isEmpty()) {
                recyclerView = getActivity().findViewById(R.id.library_recyclerview);

                GroupAdapter adapter = new GroupAdapter();

                for (GameModel game : Objects.requireNonNull(mViewModel.libraryGames.getValue())) {
                    adapter.add(new GameButtonRecyclerItem(game));
                }
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            }
        });
    }

}