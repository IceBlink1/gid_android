package ru.com.gid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,
            SettingsFragment()).commit()
        setContentView(R.layout.template_main)

    }
}
