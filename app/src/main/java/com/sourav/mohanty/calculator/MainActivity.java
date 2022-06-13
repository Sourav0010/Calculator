package com.sourav.mohanty.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaring Final Strings
    private final String SAVED_OPERATION = "pendingOp";
    private final String SAVED_OPERAND = "op1";

    // Declaring Variable
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    // Variables to hold data for operations;
    private Double op1 = null;
    private String pendingOp = "=";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.newNumber);
        newNumber = findViewById(R.id.result);
        displayOperation = findViewById(R.id.operation);

        //Number Buttons
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttondot = findViewById(R.id.buttonDot);

        //Operation Buttons
        Button buttonequals = findViewById(R.id.buttonEquals);
        Button buttondevide = findViewById(R.id.buttonDevide);
        Button buttonmultiply = findViewById(R.id.buttonMultiply);
        Button buttonminus = findViewById(R.id.buttonMinus);
        Button buttonplus = findViewById(R.id.buttonAdd);
        Button percentageButton = findViewById(R.id.percentage);
        Button brackets = findViewById(R.id.buttonBracket);

        //Added OnClock Action For The Number Buttons....
        View.OnClickListener onClickNumber = v -> {
            Button b = (Button) v;
            newNumber.append(b.getText().toString());
        };

        //Assigned the OnClick Action For the All Number Buttons....
        button0.setOnClickListener(onClickNumber);
        button1.setOnClickListener(onClickNumber);
        button2.setOnClickListener(onClickNumber);
        button3.setOnClickListener(onClickNumber);
        button4.setOnClickListener(onClickNumber);
        button5.setOnClickListener(onClickNumber);
        button6.setOnClickListener(onClickNumber);
        button7.setOnClickListener(onClickNumber);
        button8.setOnClickListener(onClickNumber);
        button9.setOnClickListener(onClickNumber);
        buttondot.setOnClickListener(onClickNumber);

        View.OnClickListener opListner = v -> {
            Button b = (Button) v;
            String operation = b.getText().toString();
            String value = newNumber.getText().toString();
            try {
                Double doubleValue = Double.valueOf(value);
                performOperation(doubleValue, operation);
            } catch (NumberFormatException e) {
                newNumber.setText("");
            }
            pendingOp = operation;
            displayOperation.setText(pendingOp);
        };

        buttonequals.setOnClickListener(opListner);
        buttondevide.setOnClickListener(opListner);
        buttonminus.setOnClickListener(opListner);
        buttonplus.setOnClickListener(opListner);
        buttonmultiply.setOnClickListener(opListner);
        percentageButton.setOnClickListener(onClickNumber);


        Button clearText = findViewById(R.id.clearText);
        clearText.setOnClickListener(v -> {
            result.setText("");
            newNumber.setText("");
            displayOperation.setText("");
            op1 = null;
        });

        percentageButton.setOnClickListener(v -> {
            String s = result.getText().toString();
            String m = newNumber.getText().toString();
            if ((s.length() == 0) && (m.length() == 0)) {
                result.setText("0.0");
            } else {
                try {
                    Double value = Double.valueOf(s);
                    Double num = Double.valueOf(m);
                    double cal = (value * num) / 100.0;
                    result.setText(Double.toString(cal));
                    newNumber.setText(result.getText());
                    displayOperation.setText("");
                    op1 = null;
                } catch (Exception e) {
                    result.setText("");
                    newNumber.setText("");
                    displayOperation.setText("");
                    op1 = null;
                }
            }
        });

        Button buttonNeg = findViewById(R.id.negSymobol);
        buttonNeg.setOnClickListener(v -> {
            String s = newNumber.getText().toString();
            if (s.length() == 0) {
                newNumber.setText("-");
            } else {
                try {
                    double doublevalue = Double.parseDouble(s);
                    doublevalue *= -1;
                    op1 = doublevalue;
                    newNumber.setText(Double.toString(doublevalue));
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
            }
        });

        brackets.setOnClickListener(v -> {
            Context context = getApplicationContext();
            CharSequence text = "Feature Is Under Development :)";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean isFirstRun = sharedPreferences.getBoolean("IS_FIRST_RUN", true);

        if (isFirstRun) {
            // AlertDialog Builder class
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(MainActivity.this);
            
            builder.setMessage("→ New Material Ui Introduced. " +
                    "\n→ Minor Bugs Fixed " +
                    "\n→ Added New Functions " +
                    "\n→ With New Refreshing Look " +
                    "\n→ Dev CraazY");

            builder.setTitle("Update Info!");
            builder.setCancelable(false);
            builder
                    .setPositiveButton(
                            "Ok",
                            (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();

            alertDialog.show();

            editor.putBoolean("IS_FIRST_RUN", false);
            editor.apply();
        }

    }

    @SuppressLint("SetTextI18n")
    private void performOperation(Double value, String operation) {
        if (null == op1) {
            op1 = value;
        } else {
            if (pendingOp.equals("=")) {
                pendingOp = operation;
            }
            switch (pendingOp) {
                case "=":
                    op1 = value;
                    break;
                case "÷":
                    if (value == 0) {
                        op1 = 0.0;
                    } else {
                        op1 /= value;
                    }
                    break;
                case "×":
                    op1 *= value;
                    break;
                case "-":
                    op1 = op1 - value;
                    break;
                case "+":
                    op1 += value;
                    break;
            }
        }
        result.setText(op1.toString());
        newNumber.setText("");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOp = savedInstanceState.getString(SAVED_OPERATION);
        op1 = savedInstanceState.getDouble(SAVED_OPERAND);
        displayOperation.setText(pendingOp);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SAVED_OPERATION, pendingOp);
        if (op1 != null) {
            outState.putDouble(SAVED_OPERAND, op1);
        }
        super.onSaveInstanceState(outState);
    }
}