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

import com.example.coffee_challenge.dao.EmployeeDAO;
import com.example.coffee_challenge.model.Employee;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


public class PutEmployeeActivity extends AppCompatActivity {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private int employeeId;
    private EditText textEditName;
    private EditText textEditBirthdate;
    private EditText textEditAdmissionDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_employee);
        Intent originIntent = getIntent();
        this.employeeId = originIntent.getIntExtra("employee_id", -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textEditName = ((TextInputLayout) findViewById(R.id.add_employee_textInputLayout_name)).getEditText();
        textEditBirthdate = ((TextInputLayout) findViewById(R.id.add_employee_textInputLayout_birthdate)).getEditText();
        textEditAdmissionDate = ((TextInputLayout) findViewById(R.id.add_employee_textInputLayout_admission)).getEditText();

        configTitle();
        configSaveButton();
        setTextFieldData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configTitle() {
        String titleName;
        if (employeeId >= 0) {
            titleName = "Editar funcionário";
        } else {
            titleName = "Adicionar funcionário";
        }

        setTitle(titleName);
    }

    private void setTextFieldData() {
        if (employeeId < 0) {
            return;
        }
        Optional<Employee> employeeOpt = employeeDAO.findById(employeeId);
        if (employeeOpt.isPresent()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Employee employee = employeeOpt.get();
            textEditName.setText(employee.name);
            textEditBirthdate.setText(dateFormat.format(employee.birthdate));
            textEditAdmissionDate.setText(dateFormat.format(employee.admissionDate));
        }
    }

    public void configSaveButton() {
        Button saveButton = findViewById(R.id.button_save_employee);
        if (employeeId >= 0) {
            saveButton.setOnClickListener(updateCoffee);
        } else {
            saveButton.setOnClickListener(saveCoffee);
        }
    }

    private final View.OnClickListener saveCoffee = (View e) -> {
        if (isFormValid()) {
            String nameInputValue = textEditName.getText().toString();
            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            Date birthdateInputValue = null;
            Date admissionInputValue = null;
            try {
                birthdateInputValue = dateFormatter.parse(textEditBirthdate.getText().toString());
                admissionInputValue = dateFormatter.parse(textEditAdmissionDate.getText().toString());
            } catch (ParseException parseException) {
                parseException.printStackTrace();
                Toast.makeText(this, "A data deve estar no formato dd/mm/yyyy, confira novamente", Toast.LENGTH_LONG).show();
                return;
            }
            Employee employee = new Employee(nameInputValue, birthdateInputValue, admissionInputValue);

            employeeDAO.add(employee);
            String toastMessage = "Funcionário " +
                    employee.name +
                    " ADICIONADO com sucesso";

            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private final View.OnClickListener updateCoffee = (View e) -> {
        if (isFormValid()) {
            String nameInputValue = textEditName.getText().toString();
            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            Date birthdateInputValue = null;
            Date admissionInputValue = null;
            try {
                birthdateInputValue = dateFormatter.parse(textEditBirthdate.getText().toString());
                admissionInputValue = dateFormatter.parse(textEditAdmissionDate.getText().toString());
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            Employee employee = new Employee(employeeId, nameInputValue, birthdateInputValue, admissionInputValue);

            employeeDAO.update(employee);

            String toastMessage = "Funcionário " +
                    employee.name +
                    " EDITADO com sucesso";

            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private boolean isFormValid() {
        if (textEditName != null && textEditName.getText().toString().isEmpty()) {
            textEditName.setError("O campo nome não pode ser nulo");
            return false;
        }

        if (textEditBirthdate != null && textEditBirthdate.getText().toString().isEmpty()) {
            textEditBirthdate.setError("A data de nascimento não pode ser nula");
            return false;
        }

        if (textEditAdmissionDate != null && textEditAdmissionDate.getText().toString().isEmpty()) {
            textEditAdmissionDate.setError("A data de admissão não pode ser nula");
            return false;
        }

        return true;
    }
}
