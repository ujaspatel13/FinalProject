package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Language extends AppCompatActivity{
    Button btnHindi, btnEnglish;
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);
        btnHindi = findViewById(R.id.btnHindi);
        btnEnglish = findViewById(R.id.btnEnglish);

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LocalHelper.setLocale(Language.this, "en");
                resources = context.getResources();
                // messageView.setText(resources.getString(R.string.language));
                Intent intent = new Intent(Language.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LocalHelper.setLocale(Language.this, "hi");
                resources = context.getResources();
                // messageView.setText(resources.getString(R.string.language));
                Intent intent = new Intent(Language.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
