package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9,
            button0;
    private Button buttonAdd, buttonSub, buttonMul, buttonDiv, buttonDot, buttonEqual;
    private String currentInput = "";
    private double operand1 = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.text_View1);
    }

    public void onDigitClick(View view) {
        Button button = (Button) view;
        currentInput += button.getText().toString();
        updateDisplay();
    }

    public void onOperatorClick(View view) {
        if (!currentInput.isEmpty()) {
            operand1 = Double.parseDouble(currentInput);
            operator = ((Button) view).getText().toString();
            currentInput = "";
        }
    }

    public void onEqualsClick(View view) {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double operand2 = Double.parseDouble(currentInput);
            double result = performOperation(operand1, operand2, operator);

            // Format result to avoid unnecessary decimals if it's a whole number
            if (result == (long) result) {
                currentInput = String.valueOf((long) result);
            } else {
                currentInput = String.valueOf(result);
            }

            operator = "";
            updateDisplay();
        }
    }

    public void onClearClick(View view) {
        currentInput = "";
        operand1 = 0;
        operator = "";
        updateDisplay();
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0)
                    return operand1 / operand2;
                else
                    return Double.NaN;
            default:
                return 0;
        }
    }

    public void updateDisplay() {
        textView1.setText(currentInput);
    }
}
