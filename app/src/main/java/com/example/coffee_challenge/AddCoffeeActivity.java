package com.example.coffee_challenge;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coffee_challenge.dao.CoffeeDAO;
import com.example.coffee_challenge.model.Coffee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.security.PublicKey;
import java.util.Objects;


public class AddCoffeeActivity extends AppCompatActivity {
    private CoffeeDAO coffeeDAO = new CoffeeDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coffee);
        setTitle("Adicionar café");

        configSaveButton();
    }

    public void configSaveButton() {
        Button saveButton = findViewById(R.id.button_save_coffee);
        saveButton.setOnClickListener(saveCoffee);
    }

    private final View.OnClickListener saveCoffee = (View e) -> {
        EditText name = ((TextInputLayout) findViewById(R.id.add_coffee_textInputLayout_name)).getEditText();
        EditText roastLevel = ((TextInputLayout) findViewById(R.id.add_coffee_textInputLayout_roastingLevel)).getEditText();

        if(name != null && name.getText().toString().isEmpty()) {
            name.setError("O campo café não pode ser nulo");
        } else if(roastLevel != null && roastLevel.getText().toString().isEmpty()) {
            roastLevel.setError("O nível de café não pode ser nulo");
        } else {
            String nameInputValue = name.getText().toString();
            Integer roastLevelInputValue = Integer.parseInt(roastLevel.getText().toString());
            Coffee coffee = new Coffee(nameInputValue, roastLevelInputValue);
            coffeeDAO.add(coffee);

            String toastMessage = "Café " +
                    coffee.name +
                    " adicionado com sucesso";

            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
            finish();
        }
    };
}
