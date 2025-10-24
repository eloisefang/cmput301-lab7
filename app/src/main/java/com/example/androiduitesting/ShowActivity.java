package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView cityTextView = findViewById(R.id.cityTextView);
        Button backButton = findViewById(R.id.backButton);

        String cityName = getIntent().getStringExtra("city_name");
        cityTextView.setText(cityName);
        backButton.setOnClickListener(v -> finish());
    }
}
