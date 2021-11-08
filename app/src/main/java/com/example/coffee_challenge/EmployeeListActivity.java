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

import com.example.coffee_challenge.dao.EmployeeDAO;
import com.example.coffee_challenge.model.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Optional;

public class EmployeeListActivity extends AppCompatActivity {
    final private EmployeeDAO employeeDAO = new EmployeeDAO();
    private ListView listView;
    private TextView emptyListTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        setTitle("Lista de funcionários");

        Optional.ofNullable(getSupportActionBar()).ifPresent(t -> t.setDisplayHomeAsUpEnabled(true));

        listView = findViewById(R.id.listView_employee);
        emptyListTextView = findViewById(R.id.textView_list_employee_empty_message);

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
        if (employeeDAO.isEmpty()) {
            emptyListTextView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            emptyListTextView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    public void configureListView() {
        EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(this, R.layout.adapter_view_employee_list, employeeDAO.getAll());
        employeeListAdapter.onAdapterUpdate(this::viewContentToggle);
        listView.setAdapter(employeeListAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Employee employee = (Employee) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, PutEmployeeActivity.class);
            intent.putExtra("employee_id", employee.id);

            startActivity(intent);
        });
    }

    public void configureAddCoffee() {
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton_add_employee);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, PutEmployeeActivity.class);
            startActivity(intent);
        });
    }
}
