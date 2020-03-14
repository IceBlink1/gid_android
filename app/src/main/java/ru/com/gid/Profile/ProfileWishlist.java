package ru.com.gid.Profile;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ru.com.gid.GameButtonFactory;
import ru.com.gid.R;

public class ProfileWishlist extends Fragment {

    private ProfileWishlistViewModel mViewModel;
    private GridLayout gridLayout;

    public static ProfileWishlist newInstance() {
        return new ProfileWishlist();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_wishlist, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridLayout = getActivity().findViewById(R.id.wishlist_gridlayout);
        gridLayout.setColumnCount(2);
        try {
            gridLayout.addView(GameButtonFactory.getGameButton(getActivity(), 800, 500, 500).get().getButton());
            gridLayout.addView(GameButtonFactory.getGameButton(getActivity(), 800, 500, 500).get().getButton());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mViewModel = ViewModelProviders.of(this).get(ProfileWishlistViewModel.class);
        // TODO: Use the ViewModel
    }

}
