package com.example.coffeeexperiment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeexperiment.dao.CoffeeDAO;
import com.example.coffeeexperiment.model.Coffee;

public class CoffeeListActivity extends AppCompatActivity {
    final private CoffeeDAO coffeeDAO = new CoffeeDAO();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_coffee_list);

        ArrayAdapter<Coffee> adapterView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coffeeDAO.getAll());

        ListView listView = findViewById(R.id.listView_coffee);
        listView.setAdapter(adapterView);

    }
}
