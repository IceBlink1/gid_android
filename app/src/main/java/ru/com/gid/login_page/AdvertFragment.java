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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import ru.com.gid.R;

public class AdvertFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_advert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Resources resources = getResources();
        ArrayList<TextView> elements = new ArrayList<TextView>(Arrays.<TextView>asList(
                (TextView) (getView().findViewById(R.id.advertTitleTextView)),
                (TextView) (getView().findViewById(R.id.advertTextView))));
        String[] strings = resources.getStringArray(R.array.advert_page);

        for (int i = 0; i < 2; i++)
            elements.get(i).setText(strings[i]);

        view.findViewById(R.id.advertDoneButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.login_page_layout, new ChoosePlatformFragment()).addToBackStack(null);
                ft.commit();
            }
        });
    }
}
