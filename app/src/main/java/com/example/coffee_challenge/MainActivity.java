package com.example.coffee_challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String[] menuOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuOptions = new String[] {
                getString(R.string.main_menu_coffee_option),
                getString(R.string.main_menu_employee_option)
        };

        configListView();
    }

    private void configListView() {
        listView = findViewById(R.id.listView_main_menu);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuOptions);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(onMenuItemClick);
    }

    AdapterView.OnItemClickListener onMenuItemClick = (parent, view, position, id) -> {
        Intent intent = null;
        if(position == 0) {
            intent = new Intent(this, CoffeeListActivity.class);
        } else if (position == 1) {
            Toast.makeText(this, "Clickou no funcionário, não implementado", Toast.LENGTH_SHORT).show();
        }

        if (intent != null) {
            startActivity(intent);
        }
    };

}