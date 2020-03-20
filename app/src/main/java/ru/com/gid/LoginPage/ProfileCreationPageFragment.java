package ru.com.gid.LoginPage;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import ru.com.gid.R;

public class ProfileCreationPageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_creation_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Resources resources = getResources();

        ArrayList<TextView> elements = new ArrayList<>(Arrays.asList(
                (TextView) (view.findViewById(R.id.titleTextView)),
                (EditText) (view.findViewById(R.id.emailEditText)),
                (TextView) (view.findViewById(R.id.emailHintTextView)),
                (EditText) (view.findViewById(R.id.passwordEditText)),
                (TextView) (view.findViewById(R.id.passwordHintTextView))
        ));

        String[] strings = resources.getStringArray(R.array.signup_page);

        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) instanceof EditText)
                (elements.get(i)).setHint(strings[i]);
            else
                (elements.get(i)).setText(strings[i]);
        }

        view.findViewById(R.id.doneButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.login_page_layout, new AdvertFragment()).addToBackStack(null);
                ft.commit();
            }
        });
    }
}
