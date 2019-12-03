package ru.isoteric.hms_transliteration;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutAppActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeNoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        setActionBar();
        genVersion();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setActionBar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @SuppressLint("SetTextI18n")
    private void genVersion() {

        ((TextView) findViewById(R.id.aa_version)).setText(
              "Версия: " + BuildConfig.VERSION_NAME + "." + (BuildConfig.VERSION_CODE * 100 + 19) + "." + BuildConfig.BUILD_TYPE
        );


    }

}
