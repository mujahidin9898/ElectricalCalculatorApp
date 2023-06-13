package com.example.electricalapplication;

import static android.net.Uri.*;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    private Button buttonWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        buttonWebsite = findViewById(R.id.buttonWebsite);
        buttonWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite();
            }
        });
    }

    private void openWebsite() {
       
        String websiteUrl = "https://github.com/mujahidin9898/ElectricalCalculatorApp.git";

        Intent intent = new Intent(Intent.ACTION_VIEW, parse(websiteUrl));
        startActivity(intent);
    }
}
