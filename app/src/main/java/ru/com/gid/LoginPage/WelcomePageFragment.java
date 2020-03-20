package ru.com.gid.LoginPage;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import ru.com.gid.R;

public class WelcomePageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Resources resources = getResources();
        ArrayList<TextView> elements = new ArrayList<TextView>(Arrays.<TextView>asList(
                (TextView) (getView().findViewById(R.id.welcomeText)),
                (TextView) (getView().findViewById(R.id.adText))));
        String[] strings = resources.getStringArray(R.array.welcome_page);

        for (int i = 0; i < 2; i++)
            elements.get(i).setText(strings[i]);

        view.findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.login_page_layout, new CreateProfileFragment());
                ft.commit();
            }
        });
    }
}
