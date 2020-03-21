package ru.com.gid.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

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
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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


    //TODO: extract strings to list
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
