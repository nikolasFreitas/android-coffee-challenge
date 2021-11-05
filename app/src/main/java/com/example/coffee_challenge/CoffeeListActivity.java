package com.example.coffee_challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coffee_challenge.dao.CoffeeDAO;
import com.example.coffee_challenge.model.Coffee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CoffeeListActivity extends AppCompatActivity {
    final private CoffeeDAO coffeeDAO = new CoffeeDAO();
    private ListView listView;
    private TextView emptyListTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_list);
        setTitle("Lista de cafés");

        listView = findViewById(R.id.listView_coffee);
        emptyListTextView = findViewById(R.id.textView_list_coffee_empty_message);

        viewContentToggle();
        configureListView();
        configureListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewContentToggle();
        configureListView();
    }

    public void viewContentToggle() {
        if (coffeeDAO.isEmpty()) {
            emptyListTextView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            if (listView.getVisibility() == View.GONE) {
                emptyListTextView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void configureListView() {
        CoffeeListAdapter coffeListAdapter = new CoffeeListAdapter(this, R.layout.adapter_view_coffee_list, coffeeDAO.getAll());
        listView.setAdapter(coffeListAdapter);
//        ArrayAdapter<Coffee> adapterView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coffeeDAO.getAll());
//        listView.setAdapter(adapterView);
    }

    public void configureListeners() {
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton_add_coffee);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddCoffeeActivity.class);
            startActivity(intent);
        });
    }
}
