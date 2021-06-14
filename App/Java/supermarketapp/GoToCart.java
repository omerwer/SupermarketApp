package com.example.supermarketapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoToCart extends AppCompatActivity {
    TextView final_result;
    Button positiveButton;
    Button negativeButton;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_cart);

        final_result = (TextView) findViewById(R.id.final_result);
        final_result.setText(getFinalCartVal());

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Are You Sure?")
                .setMessage("press OK only if you are done shopping")
                .setPositiveButton("Continue Shopping", null)
                .setNegativeButton("OK", null)
                .show();

        positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Go Back To Continue Shopping", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Press The CHECKOUT Button", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                String weight = extras.get("weight").toString();

                OkHttpClient client = new OkHttpClient();
                String url = "http://192.168.1.235:8080/getTurn/" + weight;    // IMPORTANT: update ip when using new computer

                Request request = new Request.Builder().url(url).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            Gson g = new Gson();
                            String myResponse = response.body().string();
                            Response_Data ret = g.fromJson(myResponse, Response_Data.class);
                            ret.estimate_start = ret.estimate_start.substring(0, ret.estimate_start.indexOf("GMT") - 1);
                            ret.estimate_end = ret.estimate_end.substring(0, ret.estimate_end.indexOf("GMT") - 1);

                            Intent intent = new Intent (getApplicationContext(), ChosenCounter.class);
                            intent.putExtra("registry_id", ret.registry_id);
                            intent.putExtra("estimate_start", ret.estimate_start);
                            intent.putExtra("estimate_end", ret.estimate_end);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    public String getFinalCartVal(){
        return String.valueOf(UpdateCart.getFinal_res());
    }

    public class Response_Data {
        public int registry_id;
        public String estimate_start;
        public String estimate_end;
    }
}