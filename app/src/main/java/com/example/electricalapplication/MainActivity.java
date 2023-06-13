package com.example.electricalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import com.example.electricalapplication.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUnits;
    private EditText editTextRebate;
    private Button buttonCalculate;
    private TextView textViewResult;
    private Button buttonAbout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        editTextUnits = findViewById(R.id.editTextUnits);
        editTextRebate = findViewById(R.id.editTextRebate);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        buttonAbout = findViewById(R.id.buttonAbout);

       
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                int units = Integer.parseInt(editTextUnits.getText().toString());
                double rebate = Double.parseDouble(editTextRebate.getText().toString()) / 100;

                
                double totalCharges = calculateTotalCharges(units);

               
                double rebateAmount = totalCharges * rebate;
                double finalCost = totalCharges - rebateAmount;

                
                textViewResult.setText("Final Cost: RM " + String.format("%.2f", finalCost));
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    
    public void performActionFromAbout() {
        Toast.makeText(this, "Action from AboutActivity performed", Toast.LENGTH_SHORT).show();
        
        
    }

    
    private double calculateTotalCharges(int units) {
        double totalCharges = 0.0;

        if (units <= 200) {
            totalCharges = units * 0.218;
        } else if (units <= 300) {
            totalCharges = 200 * 0.218 + (units - 200) * 0.334;
        } else if (units <= 600) {
            totalCharges = 200 * 0.218 + 100 * 0.334 + (units - 300) * 0.516;
        } else if (units <= 900) {
            totalCharges = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (units - 600) * 0.546;
        }

        return totalCharges;
    }
}
