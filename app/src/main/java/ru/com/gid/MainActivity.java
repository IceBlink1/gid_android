package ru.com.gid;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.com.gid.feed.Feed;
import ru.com.gid.profile.Profile;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        this.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_main);

        FragmentContainerView fragmentContainerView = findViewById(R.id.nav_host_fragment);
        BottomNavigationView navView = findViewById(R.id.bottom_navigation_bar);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.nav_host_fragment, new Feed()).commit();
        navView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getTitle().toString()) {
                case "Profile":
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.nav_host_fragment, new Profile()).commit();
                    break;
                case "Feed":
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.nav_host_fragment, new Feed()).commit();
                    break;
            }
            return false;
        });

    }
}
