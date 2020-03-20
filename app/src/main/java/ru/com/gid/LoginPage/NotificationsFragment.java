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
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import ru.com.gid.R;

public class NotificationsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Resources resources = getResources();

        ArrayList<TextView> elements = new ArrayList<TextView>(Arrays.<TextView>asList(
                (TextView) (view.findViewById(R.id.notificationsTitleTextView)),
                (CheckBox) (view.findViewById(R.id.notificationCheckBox1)),
                (CheckBox) (view.findViewById(R.id.notificationCheckBox2)),
                (CheckBox) (view.findViewById(R.id.notificationCheckBox3)),
                (CheckBox) (view.findViewById(R.id.notificationCheckBox4)),
                (CheckBox) (view.findViewById(R.id.notificationCheckBox5))));

        String[] strings = resources.getStringArray(R.array.notification_page);

        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).setText(strings[i]);
        }

        view.findViewById(R.id.platformContinueButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.login_page_layout, new LoginEndingFragment());
                ft.commit();
            }
        });
    }
}
