package ru.com.gid.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.com.gid.R;

public class ProfileScrollPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> profileElements = new ArrayList<Fragment>() {
        {
            add(new ProfileLibrary());
            add(new ProfileWishlist());
            add(new ProfileUnreleased());
            add(new ProfileReviews());
            add(new ProfileLogin());
        }
    };

    public ProfileScrollPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return profileElements.get(position);
    }

    @Override
    public int getCount() {
        return profileElements.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Library";
            case 1:
                return "Wishlist";
            case 2:
                return "To be released";
            case 3:
                return "Reviews";
            case 4:
                return "Accounts";
        }
        throw new ArrayIndexOutOfBoundsException("Unexpected argument in profile scroll.");
    }
}
