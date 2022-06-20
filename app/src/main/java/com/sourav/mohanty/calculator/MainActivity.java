package com.sourav.mohanty.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Declaring Final Strings
    private final String SAVED_OPERATION = "pendingOp";
    private final String SAVED_OPERAND = "op1";

    // Declaring Variable
    private EditText newNumber;
    private EditText result;
    private TextView displayOperation;

    // Variables to hold data for operations;
    private Double op1 = null;
    private String pendingOp = "=";

    @SuppressLint({"SetTextI18n", "ResourceType", "InflateParams"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNumber = findViewById(R.id.newNumber);
        result = findViewById(R.id.result);
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
        Button buttonDot = findViewById(R.id.buttonDot);

        //Operation Buttons
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonDevide = findViewById(R.id.buttonDevide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonAdd);
        Button percentageButton = findViewById(R.id.percentage);
        Button brackets = findViewById(R.id.buttonBracket);

        //For The Scientific Operations Only
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //Scientific Operation Buttons
            Button buttonSin = findViewById(R.id.buttonSin);
            Button buttonCos = findViewById(R.id.buttonCos);
            Button buttonTan = findViewById(R.id.buttonTan);
            Button buttonRoot = findViewById(R.id.buttonRoot);
            Button buttonFactor = findViewById(R.id.buttonFactor);
            Button buttonPie = findViewById(R.id.buttonPie);
            Button buttonSquare = findViewById(R.id.buttonSquare);
            Button buttonLog = findViewById(R.id.buttonLog);


            // Scientific Operations
            buttonSquare.setOnClickListener(v -> {
                try {
                    int i = Integer.parseInt(result.getText().toString());
                    int b = (int) Math.pow(i, 2);
                    newNumber.setText(Double.toString(b));
                    result.setText(newNumber.getText());
                    op1 = null;
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "please Enter A number Before Proceed!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
            buttonPie.setOnClickListener(v -> {
                try {
                    double i = Integer.parseInt(result.getText().toString());
                    double b = i * Math.PI;
                    newNumber.setText(Double.toString(b));
                    result.setText(newNumber.getText());
                    op1 = null;
                } catch (Exception e) {
                    double d = Math.PI;
                    result.setText(Double.toString(d));
                    op1 = null;
                }
            });
            buttonRoot.setOnClickListener(v -> {
                try {
                    double c = Double.parseDouble(String.valueOf(result.getText()));
                    double d = Math.sqrt(c);
                    newNumber.setText(Double.toString(d));
                    result.setText(newNumber.getText());
                    op1 = null;
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Enter Number First:)";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });

            buttonSin.setOnClickListener(v -> {
                try {
                    op1 = Double.parseDouble(result.getText().toString());
                    double result = Math.sin(Math.toRadians(op1));
                    newNumber.setText(Double.toString(result));
                    this.result.setText("");
                    op1 = null;
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Syntax Error", Toast.LENGTH_SHORT).show();
                }
            });

            buttonCos.setOnClickListener(v -> {
                try {
                    op1 = Double.parseDouble(result.getText().toString());
                    double result = Math.cos(Math.toRadians(op1));
                    newNumber.setText(Double.toString(result));
                    this.result.setText("");
                    op1 = null;
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Syntax Error", Toast.LENGTH_SHORT).show();
                }
            });

            buttonTan.setOnClickListener(v -> {
                try {
                    op1 = Double.parseDouble(result.getText().toString());
                    double result = Math.tan(Math.toRadians(op1));
                    newNumber.setText(Double.toString(result));
                    this.result.setText("");
                    op1 = null;
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Syntax Error", Toast.LENGTH_SHORT).show();
                }
            });

            buttonLog.setOnClickListener(v -> {
                try {
                    op1 = Double.parseDouble(result.getText().toString());
                    double result = Math.log(op1);
                    newNumber.setText(Double.toString(result));
                    this.result.setText("");
                    op1 = null;
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Syntax Error", Toast.LENGTH_SHORT).show();
                }
            });
            buttonFactor.setOnClickListener(v -> {
                try {
                    op1 = Double.parseDouble(result.getText().toString());
                    double j = 1;
                    for (double i = 1; i <= op1; i++) {
                        j *= i;
                    }
                    op1 = j;
                    newNumber.setText(Double.toString(j));
                    result.setText(" ");
                    result.setText(newNumber.getText());

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Syntax Error", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //Added OnClick Action For The Number Buttons....
        View.OnClickListener onClickNumber = v -> {
            Button b = (Button) v;
            result.append(b.getText().toString());
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
        buttonDot.setOnClickListener(onClickNumber);

        //Added onClick Action For Operation Buttons
        View.OnClickListener onClickOperation = v -> {
            Button b = (Button) v;
            String operation = b.getText().toString();
            String value = result.getText().toString();
            try {
                Double doubleValue = Double.valueOf(value);
                performOperation(doubleValue, operation);
            } catch (NumberFormatException e) {
                result.setText("");
            }
            pendingOp = operation;
            displayOperation.setText(pendingOp);
        };

        //Assigning onClick Actions For Operation Buttons
        buttonEquals.setOnClickListener(onClickOperation);
        buttonDevide.setOnClickListener(onClickOperation);
        buttonMinus.setOnClickListener(onClickOperation);
        buttonPlus.setOnClickListener(onClickOperation);
        buttonMultiply.setOnClickListener(onClickOperation);
        percentageButton.setOnClickListener(onClickNumber);
        Button clearText = findViewById(R.id.clearText);


        clearText.setOnClickListener(v -> {
            newNumber.setText("");
            result.setText("");
            displayOperation.setText("");
            op1 = null;
        });

        percentageButton.setOnClickListener(v -> {
            String s = newNumber.getText().toString();
            String m = result.getText().toString();
            if ((s.length() == 0) && (m.length() == 0)) {
                newNumber.setText("0.0");
            } else {
                try {
                    Double value = Double.valueOf(s);
                    Double num = Double.valueOf(m);
                    double cal = (value * num) / 100.0;
                    this.newNumber.setText(Double.toString(cal));
                    result.setText(this.newNumber.getText());
                    displayOperation.setText("");
                    op1 = null;
                } catch (Exception e) {
                    newNumber.setText("");
                    result.setText("");
                    displayOperation.setText("");
                    op1 = null;
                }
            }
        });

        Button buttonNeg = findViewById(R.id.negSymobol);
        buttonNeg.setOnClickListener(v -> {
            String s = result.getText().toString();
            if (s.length() == 0) {
                result.setText("-");
            } else {
                try {
                    double doublevalue = Double.parseDouble(s);
                    doublevalue *= -1;
                    op1 = doublevalue;
                    result.setText(Double.toString(doublevalue));
                } catch (NumberFormatException e) {
                    result.setText("");
                }
            }
        });

        brackets.setOnClickListener(v -> {
            try {
                String str;
                str = result.getText().toString();
                str = str.substring(0, str.length() - 1);
                result.setText(str);
            } catch (Exception ignored) {
            }
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
                    op1 -= value;
                    break;
                case "+":
                    op1 += value;
                    break;
            }
        }
        newNumber.setText(Double.toString(op1));
        result.setText("");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOp = savedInstanceState.getString(SAVED_OPERATION);
        op1 = savedInstanceState.getDouble(SAVED_OPERAND);
        if (Objects.equals(pendingOp, "=")) {
            displayOperation.setText("");
        } else {
            displayOperation.setText(pendingOp);
        }
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