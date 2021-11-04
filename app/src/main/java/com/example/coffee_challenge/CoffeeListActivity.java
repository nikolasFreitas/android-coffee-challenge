package com.example.coffee_challenge;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coffee_challenge.dao.CoffeeDAO;
import com.example.coffee_challenge.model.Coffee;

public class CoffeeListActivity extends AppCompatActivity {
    final private CoffeeDAO coffeeDAO = new CoffeeDAO();
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_list);

        listView = findViewById(R.id.listView_coffee);
        viewContentToggle();
        configureListView();
    }

    public void viewContentToggle() {
        if (coffeeDAO.isEmpty()) {
            listView.setVisibility(View.GONE);
        } else {
            if (listView.getVisibility() == View.GONE) {
                listView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void configureListView() {
        ArrayAdapter<Coffee> adapterView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coffeeDAO.getAll());
        listView.setAdapter(adapterView);
    }
}
