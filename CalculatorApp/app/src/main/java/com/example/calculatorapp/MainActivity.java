package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input1, input2;
    Button addBtn, subBtn;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        addBtn = findViewById(R.id.addBtn);
        subBtn = findViewById(R.id.subBtn);
        result = findViewById(R.id.result);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());
                int sum = num1 + num2;
                result.setText("Result: " + String.valueOf(sum));
            }
        });

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());
                int diff = num1 - num2;
                result.setText("Result: " + String.valueOf(diff));
            }
        });
    }
}