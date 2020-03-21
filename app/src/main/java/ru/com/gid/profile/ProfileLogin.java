package ru.com.gid.profile;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.com.gid.api.SteamLoginModel;
import ru.com.gid.App;
import ru.com.gid.R;

public class ProfileLogin extends Fragment {

    private ProfileLoginViewModel mViewModel;

    public static ProfileLogin newInstance() {
        return new ProfileLogin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileLoginViewModel.class);
        // TODO: Use the ViewModel

        Button steamLogin = getActivity().findViewById(R.id.steam_login_button);
        steamLogin.setOnClickListener(this::onSteamLoginClickListener);
    }

    public void onSteamLoginClickListener(View view) {

        EditText login = getActivity().findViewById(R.id.steam_login_edittext);
        EditText password = getActivity().findViewById(R.id.steam_password_edittext);
        App.getSteamApi().
                loginToSteam(App.getToken(), new SteamLoginModel(login.getText().toString(),
                        password.getText().toString(),
                        null, null)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                TextView textView = getActivity().findViewById(R.id.steam_login_textview);
                try {
                    textView.setText(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                TextView textView = getActivity().findViewById(R.id.steam_login_textview);
                textView.setText("Login unsuccessful");
                t.printStackTrace();
            }
        });
    }

}
