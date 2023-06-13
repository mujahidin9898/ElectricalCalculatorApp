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

        // Initialize the views
        editTextUnits = findViewById(R.id.editTextUnits);
        editTextRebate = findViewById(R.id.editTextRebate);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        buttonAbout = findViewById(R.id.buttonAbout);

        // Set a click listener for the Calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the input values
                int units = Integer.parseInt(editTextUnits.getText().toString());
                double rebate = Double.parseDouble(editTextRebate.getText().toString()) / 100;

                // Calculate the total charges based on the given rate table and units used
                double totalCharges = calculateTotalCharges(units);

                // Deduct the rebate percentage from the total charges
                double rebateAmount = totalCharges * rebate;
                double finalCost = totalCharges - rebateAmount;

                // Display the final cost to the user
                textViewResult.setText("Final Cost: RM " + String.format("%.2f", finalCost));
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the AboutActivity
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    // Function to perform an action from the AboutActivity
    public void performActionFromAbout() {
        Toast.makeText(this, "Action from AboutActivity performed", Toast.LENGTH_SHORT).show();
        // Add your desired functionality here
        // This function can be called from the AboutActivity or any other activity
    }

    // Helper method to calculate the total charges based on the given rate table and units used
    private double calculateTotalCharges(int units) {
        double totalCharges = 0.0;

        // Calculate charges for each block
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
