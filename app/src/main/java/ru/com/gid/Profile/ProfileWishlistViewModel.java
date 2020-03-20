package ru.com.gid.Profile;

import androidx.lifecycle.ViewModel;

import com.xwray.groupie.GroupAdapter;

public class ProfileWishlistViewModel extends ViewModel {
    private GroupAdapter adapter = null;

    public GroupAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(GroupAdapter adapter) {
        this.adapter = adapter;
    }
}
