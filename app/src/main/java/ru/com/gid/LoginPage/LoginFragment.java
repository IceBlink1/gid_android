package ru.com.gid.LoginPage;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import ru.com.gid.R;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Resources resources = getResources();

        ArrayList<EditText> elements = new ArrayList<>(Arrays.asList(
                (EditText) (view.findViewById(R.id.editEmail)),
                (EditText) (view.findViewById(R.id.editPassword))
        ));

        String[] strings = resources.getStringArray(R.array.login_page);

        ((TextView) (view.findViewById(R.id.loginTitle))).setText(strings[0]);

        for (int i = 0; i < elements.size(); i++)
            elements.get(i).setHint(strings[i]);

        // TODO: Переход на главную страницу, проверку ввода

//        view.findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.login_page_layout, new AdvertFragment());
//                ft.commit();
//            }
//        });
    }
}
