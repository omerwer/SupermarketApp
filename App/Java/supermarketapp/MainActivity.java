package com.example.supermarketapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Spinner city_spinner;
    Spinner store_spinner;
    Button choose_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choose_store = (Button) findViewById(R.id.choose_store);
        city_spinner = findViewById(R.id.city_spinner);
        store_spinner = findViewById(R.id.store_spinner);

        List<String> cityList = new ArrayList<>();
        String city1 = new String("Be'er Sheva");
        cityList.add(city1);
        String city2 = new String("Jerusalem");
        cityList.add(city2);
        String city3 = new String("Tel Aviv");
        cityList.add(city3);

        List<String> storeList = new ArrayList<>();
        String store1 = new String("Shufersal");
        storeList.add(store1);
        String store2 = new String("Mega");
        storeList.add(store2);
        String store3 = new String("Victory");
        storeList.add(store3);
        String store4 = new String("Rami Levy");
        storeList.add(store4);
        String store5 = new String("Tiv Ta'am");
        storeList.add(store5);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, storeList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        store_spinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cityList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_spinner.setAdapter(adapter2);

        store_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String store = "Selected Store: " + (String) parent.getSelectedItem();
                Toast.makeText(getApplicationContext(), store, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = "Selected City: " + (String) parent.getSelectedItem();
                Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void openUpdateCart(View view) {
        Intent intent = new Intent (this, UpdateCart.class);
        startActivity(intent);
    }
}
