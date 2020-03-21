package ru.com.gid.login_page;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.com.gid.api.LoginModel;
import ru.com.gid.api.TokenResponse;
import ru.com.gid.App;
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
                (EditText) (view.findViewById(R.id.usernameEditText)),
                (TextView) (view.findViewById(R.id.usernameHintTextView)),
                (EditText) (view.findViewById(R.id.passwordEditText)),
                (TextView) (view.findViewById(R.id.passwordHintTextView)),
                (EditText) (view.findViewById(R.id.commonNameEditText)),
                (TextView) (view.findViewById(R.id.commonNameHintTextView))
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
                LoginModel model = new LoginModel(elements.get(1).getText().toString(),
                        elements.get(3).getText().toString(), elements.get(7).getText().toString(),
                        elements.get(5).getText().toString());
                App.getUserApi().register(model).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        App.getUserApi().login(model).enqueue(new Callback<TokenResponse>() {
                            @Override
                            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                                if (response.body() != null) {
                                    App.setToken(response.body().getToken());
                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.login_page_layout, new AdvertFragment()).addToBackStack(null);
                                    ft.commit();
                                }
                            }

                            @Override
                            public void onFailure(Call<TokenResponse> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });
    }
}
