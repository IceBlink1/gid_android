package ru.com.gid.login_page;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.com.gid.api.LoginModel;
import ru.com.gid.api.TokenResponse;
import ru.com.gid.App;
import ru.com.gid.MainActivity;
import ru.com.gid.R;

public class LoginFragment extends Fragment {
    ArrayList<EditText> elements;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Resources resources = getResources();

        elements = new ArrayList<>(Arrays.asList(
                (EditText) (view.findViewById(R.id.editEmail)),
                (EditText) (view.findViewById(R.id.editPassword))
        ));

        String[] strings = resources.getStringArray(R.array.login_page);

        ((TextView) (view.findViewById(R.id.loginTitle))).setText(strings[0]);

        for (int i = 0; i < elements.size(); i++)
            elements.get(i).setHint(strings[i + 1]);

        view.findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getUserApi().login(new LoginModel(null, elements.get(0).getText().toString(),
                        null, elements.get(1).getText().toString())).enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        if (response.body() != null) {
                            App.setToken(response.body().getToken());
                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            v.getContext().startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
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
