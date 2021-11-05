package com.example.coffee_challenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coffee_challenge.model.Coffee;

import java.util.List;

public class CoffeeListAdapter extends ArrayAdapter<Coffee> {
    public static final String TAG = "CoffeeListAdapter";

    private Context context;

    private int resource;

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

        return convertView;
    }

    private void configTextView(View view, Coffee coffee) {
        TextView textViewCoffeName = view.findViewById(R.id.textView_coffee_name);
        TextView textViewCoffeRoast = view.findViewById(R.id.textView_coffee_roasting);

        textViewCoffeName.setText(coffee.name);
        StringBuilder roastingRate = new StringBuilder();
        final int COFFEE_UNICODE = 0X1F375;
        for (int note = 1; (note <= 5 && note <= coffee.roastingLevel); note++) {
            roastingRate.append(new String(Character.toChars(COFFEE_UNICODE)));
        }
        textViewCoffeRoast.setText(roastingRate);
    }

    private void configureEditCoffeeButton(){

    }

    private void configureRemoveCoffeeButton(){

    }
}
