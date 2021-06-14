package com.example.supermarketapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class UpdateCart extends AppCompatActivity {
    private Button calc;
    private Button done;
    private TextView result;
    private TextView section_a;
    private TextView section_b;
    private TextView section_c;
    public static int final_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cart);
//
        result = (TextView) findViewById(R.id.result);
        section_a = (TextView) findViewById(R.id.section_a);
        section_b = (TextView) findViewById(R.id.section_b);
        section_c = (TextView) findViewById(R.id.section_c);
        calc = (Button) findViewById(R.id.calc);

        calc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //calculations
                int s_a = Integer.parseInt(section_a.getText().toString());
                int s_b = Integer.parseInt(section_b.getText().toString());
                int s_c = Integer.parseInt(section_c.getText().toString());
                final_res = (7 * s_a) + (9 * s_b) + (11 * s_c);
                result.setText(String.valueOf(final_res));
            }
        });

        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), GoToCart.class);
                intent.putExtra("weight", final_res);
                startActivity(intent);
            }
        });
    }

    public static int getFinal_res() {
        return final_res;
    }
}