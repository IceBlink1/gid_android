package ru.com.gid.profile;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import ru.com.gid.R;

public class Profile extends Fragment {

    private ProfileViewModel mViewModel;

    private ProfileScrollPagerAdapter profileScrollPagerAdapter;
    private ViewPager viewPager;

    public static Profile newInstance() {
        return new Profile();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        profileScrollPagerAdapter = new ProfileScrollPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.profile_pager);

        viewPager.setAdapter(profileScrollPagerAdapter);
        TabLayout tl = view.findViewById(R.id.profile_tablayout);
        tl.setupWithViewPager(viewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }


}
