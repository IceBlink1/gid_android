package ru.com.gid.LoginPage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.com.gid.R;

public class LoginPageActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        WelcomePageFragment welcomePageFragment = new WelcomePageFragment();
        fragmentTransaction.add(R.id.login_page_layout, welcomePageFragment);
        fragmentTransaction.commit();
    }
}
