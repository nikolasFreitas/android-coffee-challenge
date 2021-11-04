package com.example.coffee_challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachMenuListeners();
    }

    View.OnClickListener onItemClick = (e) -> {
        int itemId = e.getId();
        Intent intent = null;
        if(itemId == R.id.textView_coffee_option) {
            intent = new Intent(this, CoffeeListActivity.class);

        } else if (itemId == R.id.textView_employees_option) {
            Toast.makeText(this, "Clickou no funcionário, não implementado", Toast.LENGTH_SHORT).show();
        }

        if (intent != null) {
            startActivity(intent);
        }
    };

    public void attachMenuListeners() {
        findViewById(R.id.textView_coffee_option).setOnClickListener(onItemClick);
        findViewById(R.id.textView_employees_option).setOnClickListener(onItemClick);
    }
}