package com.example.coffee_challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listView_coffee);
        emptyListTextView = findViewById(R.id.textView_list_coffee_empty_message);

        viewContentToggle();
        configureListView();
        configureAddCoffee();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewContentToggle();
        configureListView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void viewContentToggle() {
        if (coffeeDAO.isEmpty()) {
            emptyListTextView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            emptyListTextView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    public void configureListView() {
        CoffeeListAdapter coffeListAdapter = new CoffeeListAdapter(this, R.layout.adapter_view_coffee_list, coffeeDAO.getAll());
        coffeListAdapter.onAdapterUpdate(this::viewContentToggle);
        listView.setAdapter(coffeListAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Coffee coffee = (Coffee) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, PutCoffeeActivity.class);
            intent.putExtra("coffee_id", coffee.id);

            startActivity(intent);
        });
    }

    public void configureAddCoffee() {
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton_add_coffee);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, PutCoffeeActivity.class);
            startActivity(intent);
        });
    }
}
