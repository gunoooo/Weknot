package com.example.weknot.main_page.activity.SignActivity.find;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.weknot.R;

public class ShowFindIdActivity extends AppCompatActivity {

    private TextView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_find_id);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {

            finish();
        });
    }
}
