package com.example.coffee_challenge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coffee_challenge.dao.EmployeeDAO;
import com.example.coffee_challenge.model.Employee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmployeeListAdapter extends ArrayAdapter<Employee> {
    public static final String TAG = "EmployeeListAdapter";
    private EmployeeDAO employee = new EmployeeDAO();

    private Context context;
    private int resource;
    private SetOnListUpdate setOnListUpdateCallBack;

    public EmployeeListAdapter(Context context, int resource, List<Employee> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).id;
        String name = getItem(position).name;
        Date admissionDate = getItem(position).admissionDate;
        Date birthdate = getItem(position).birthdate;

        Employee employee = new Employee(id, name, admissionDate, birthdate);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);
        configTextView(convertView, employee);
        configureEditEmployeeButton(convertView, employee);


        return convertView;
    }

    private void configTextView(View view, Employee employee) {
        TextView textViewEmployeeName = view.findViewById(R.id.textView_employee_name);
        TextView textViewEmployeeBirthdate = view.findViewById(R.id.textView_employee_birthdate);
        TextView textViewEmployeeAdmission = view.findViewById(R.id.textView_employee_admission);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        textViewEmployeeName.setText(employee.name);
        textViewEmployeeBirthdate.setText(dateFormat.format(employee.birthdate));
        textViewEmployeeAdmission.setText(dateFormat.format(employee.admissionDate));
    }

    private void configureEditEmployeeButton(View view, Employee employee) {
        ImageView imageViewRemoveEmployee = view.findViewById(R.id.imageView_delete_employee);
        imageViewRemoveEmployee.setOnClickListener(v -> {
            openDialog(employee);
        });
    }

    private void openDialog(Employee employee) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle("Remover funcionário")
                .setMessage("Deseja deletar o funcionário " + employee.name + "?")
                .setPositiveButton("deletar", (DialogInterface dialog, int id) -> {
                    if (this.employee.remove(employee.id)) {
                        notifyDataSetChanged();
                        setOnListUpdateCallBack.onUpdate();
                        Toast.makeText(context, "Funcionário "+ employee.name + " removido com sucesso", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(context, "O funcionário não pode ser removido", Toast.LENGTH_SHORT).show();

                })
                .setNegativeButton("Cancelar", (DialogInterface dialog, int id) -> {});

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onAdapterUpdate(SetOnListUpdate callback) {
        setOnListUpdateCallBack = callback;
    }


    public interface SetOnListUpdate {
        void onUpdate();
    }
}
