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

import com.example.coffee_challenge.dao.CoffeeDAO;
import com.example.coffee_challenge.model.Coffee;

import java.util.List;

public class CoffeeListAdapter extends ArrayAdapter<Coffee> {
    public static final String TAG = "CoffeeListAdapter";
    private CoffeeDAO coffeeDAO = new CoffeeDAO();

    private Context context;
    private int resource;
    private SetOnListUpdate setOnListUpdateCallBack;

    public CoffeeListAdapter(Context context, int resource, List<Coffee> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).id;
        String name = getItem(position).name;
        int roastingLevel = getItem(position).roastingLevel;

        Coffee coffee = new Coffee(name, roastingLevel, id);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);
        configTextView(convertView, coffee);
        configureEditCoffeeButton(convertView, coffee);


        return convertView;
    }

    private void configTextView(View view, Coffee coffee) {
        TextView textViewCoffeName = view.findViewById(R.id.textView_coffee_name);
        TextView textViewCoffeRoast = view.findViewById(R.id.textView_coffee_roasting);

        textViewCoffeName.setText(coffee.name);
        StringBuilder roastingRate = new StringBuilder();
        final int COFFEE_UNICODE = 0X1F375;
        final int MAX_COFFEE_ICON = 5;
        for (int note = 1; (note <= MAX_COFFEE_ICON && note <= coffee.roastingLevel); note++) {
            roastingRate.append(new String(Character.toChars(COFFEE_UNICODE)));
        }
        if (coffee.roastingLevel > MAX_COFFEE_ICON) {
            roastingRate.append(" ...");
        }
        textViewCoffeRoast.setText(roastingRate);
    }

    private void configureEditCoffeeButton(View view, Coffee coffee) {
        ImageView imageViewRemoveCoffee = view.findViewById(R.id.imageView_delete_coffee);
        imageViewRemoveCoffee.setOnClickListener(v -> {
            openDialog(coffee);
//            Toast.makeText(context, "Edit ainda não implementado", Toast.LENGTH_SHORT).show();
        });
    }

    private void openDialog(Coffee coffee) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle("Remover café")
                .setMessage("Deseja deletar o café " + coffee.name + "?")
                .setPositiveButton("deletar", (DialogInterface dialog, int id) -> {
                    if (coffeeDAO.remove(coffee.id)) {
                        notifyDataSetChanged();
                        setOnListUpdateCallBack.onUpdate();
                        return;
                    }

                    Toast.makeText(context, "O café não pode ser deletado", Toast.LENGTH_SHORT).show();

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
