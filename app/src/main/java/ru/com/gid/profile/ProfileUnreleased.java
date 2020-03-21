package ru.com.gid.profile;

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

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import ru.com.gid.api.GameModel;
import ru.com.gid.GameButtonFactory;
import ru.com.gid.GameButtonRecyclerItem;
import ru.com.gid.R;

public class ProfileUnreleased extends Fragment {

    private ProfileUnreleasedViewModel mViewModel;
    private RecyclerView recyclerView;

    public static ProfileUnreleased newInstance() {
        return new ProfileUnreleased();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_unreleased, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.unreleased_recyclerview);

        GroupAdapter adapter = new GroupAdapter();
        try {
            Map<GameModel, Future<Bitmap>> gameBitmaps = GameButtonFactory.getUnreleasedGames(getActivity(), 500, 500);
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

        mViewModel = ViewModelProviders.of(this).get(ProfileUnreleasedViewModel.class);
        // TODO: Use the ViewModel
    }

}
