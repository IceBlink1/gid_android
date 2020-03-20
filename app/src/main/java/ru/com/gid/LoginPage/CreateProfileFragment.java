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
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import ru.com.gid.R;

public class CreateProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Resources resources = getResources();

        ArrayList<TextView> elements = new ArrayList<TextView>(Arrays.<TextView>asList(
                (TextView) (view.findViewById(R.id.titleText)),
                (TextView) (view.findViewById(R.id.adText)),
                (TextView) (view.findViewById(R.id.loginTextButton)),
                (Button) (view.findViewById(R.id.createProfileButton))));

        String[] strings = resources.getStringArray(R.array.login_or_signup_page);

        for (int i = 0; i < elements.size(); i++)
            elements.get(i).setText(strings[i]);

        view.findViewById(R.id.loginTextButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.login_page_layout, new LoginFragment()).addToBackStack(null);
                ft.commit();
            }
        });

        view.findViewById(R.id.createProfileButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.login_page_layout, new ProfileCreationPageFragment()).addToBackStack(null);
                ft.commit();
            }
        });
    }
}
