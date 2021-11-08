package com.example.coffee_challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.coffee_challenge.dao.CoffeeDAO;
import com.example.coffee_challenge.model.Coffee;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Optional;


public class PutCoffeeActivity extends AppCompatActivity {
    private CoffeeDAO coffeeDAO = new CoffeeDAO();
    private int coffeeId;
    private EditText textEditName;
    private EditText textEditRoastLevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_coffee);
        Intent originIntent = getIntent();
        this.coffeeId = originIntent.getIntExtra("coffee_id", -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        

        textEditName = ((TextInputLayout) findViewById(R.id.add_coffee_textInputLayout_name)).getEditText();
        textEditRoastLevel = ((TextInputLayout) findViewById(R.id.add_coffee_textInputLayout_roastingLevel)).getEditText();

        configTitle();
        configSaveButton();
        setTextFieldData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configTitle() {
        String titleName;
        if (coffeeId >= 0) {
            titleName = "Editar café";
        } else {
            titleName = "Adicionar Café";
        }

        setTitle(titleName);
    }

    private void setTextFieldData() {
        if (coffeeId < 0) {
            return;
        }
        Optional<Coffee> coffeOpt = coffeeDAO.findById(coffeeId);
        if(coffeOpt.isPresent()) {
            Coffee coffee = coffeOpt.get();
            textEditName.setText(coffee.name);
            textEditRoastLevel.setText(String.valueOf(coffee.roastingLevel));
        }
    }

    public void configSaveButton() {
        Button saveButton = findViewById(R.id.button_save_coffee);
        if (coffeeId >= 0) {
            saveButton.setOnClickListener(updateCoffee);
        } else {
            saveButton.setOnClickListener(saveCoffee);
        }
    }

    private final View.OnClickListener saveCoffee = (View e) -> {
        if (isFormValid()) {
            String nameInputValue = textEditName.getText().toString();
            Integer roastLevelInputValue = Integer.parseInt(textEditRoastLevel.getText().toString());
            Coffee coffee = new Coffee(nameInputValue, roastLevelInputValue);
            coffeeDAO.add(coffee);
            String toastMessage = "Café " +
                    coffee.name +
                    " ADICIONADO com sucesso";

            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private final View.OnClickListener updateCoffee = (View e) -> {
        if (isFormValid()) {
            String nameInputValue = textEditName.getText().toString();
            Integer roastLevelInputValue = Integer.parseInt(textEditRoastLevel.getText().toString());
            Coffee coffee = new Coffee(nameInputValue, roastLevelInputValue, coffeeId);
            coffeeDAO.update(coffee);

            String toastMessage = "Café " +
                    coffee.name +
                    " EDITADO com sucesso";

            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private boolean isFormValid() {
        if (textEditName != null && textEditName.getText().toString().isEmpty()) {
            textEditName.setError("O campo café não pode ser nulo");
            return false;
        }

        if (textEditRoastLevel != null && textEditRoastLevel.getText().toString().isEmpty()) {
            textEditRoastLevel.setError("O nível de café não pode ser nulo");
            return false;
        }

        return true;
    }
}
