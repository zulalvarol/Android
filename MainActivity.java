package com.zulalvarol.bilgioyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final Button baslaBtn = findViewById(R.id.baslaBtn);
    private String basla = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baslaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basla = "başla";
                Intent intent = new Intent(MainActivity.this, SoruActivity.class);
                intent.putExtra("başla...", basla);
                startActivity(intent);
            }
        });
    }
}