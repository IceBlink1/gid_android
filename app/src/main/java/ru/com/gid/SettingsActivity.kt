package ru.com.gid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_container,
            SettingsFragment()).commit()
        setContentView(R.layout.activity_settings)
    }
}
