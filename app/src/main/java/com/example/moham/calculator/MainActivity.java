package com.example.moham.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final char ADD = '+';
    private static final char SUB = '-';
    private static final char MULTI = '*';
    private static final char DIV = '/';
    private EditText editText;
    private TextView tvAnswer;
    private char currentAction;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bDot, bEqual, bDelete, bAdd, bSub, bDiv, bMulti;
    ArrayList<Double> arrayList;
    DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        bDelete = findViewById(R.id.button_del);
        bEqual = findViewById(R.id.button_equal);
        bAdd = findViewById(R.id.button_add);
        bSub = findViewById(R.id.button_sub);
        bDiv = findViewById(R.id.button_div);
        bMulti = findViewById(R.id.button_multi);
        bDot = findViewById(R.id.button_dot);
        editText = findViewById(R.id.editText);
        editText.setCursorVisible(true);
        tvAnswer = findViewById(R.id.tv_answer);
        arrayList = new ArrayList();

        df = new DecimalFormat("0.########");
        View view = this.getCurrentFocus();

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert("0");


            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert("1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert("2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert("3");


            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert("4");


            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert("5");


            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert("6");


            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert("7");


            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert("8");


            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert("9");


            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = editText.getText().length();
                if (length > 0) {
                    editText.getText().delete(length - 1, length);
                }
            }
        });
        bDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editText.setText("");
                tvAnswer.setText("");
                arrayList.clear();
                return true;
            }
        });
        bMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(editText.getText())) {
                    editText.setText(editText.getText() + "×");
                } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
                    editText.setText(tvAnswer.getText() + "×");
                }
            }
        });

        bSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(editText.getText())) {
                    editText.setText(editText.getText() + "-");
                } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
                    editText.setText(tvAnswer.getText() + "-");
                }
            }
        });


        bDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(editText.getText())) {
                    editText.setText(editText.getText() + "÷");
                } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
                    editText.setText(tvAnswer.getText() + "÷");
                }
            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(editText.getText())) {
                    editText.setText(editText.getText() + "+");
                } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
                    editText.setText(tvAnswer.getText() + "+");
                }
            }
        });
        bDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert("0.");
            }
        });

        bEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    equaleMethod();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    public void insert(String n) {


        editText.setText(editText.getText() + n);
        String ex = editText.getText().toString();
        char x = ex.charAt(ex.length() - 1);
        if (x == '.' || x == '÷' || x == '+' || x == '-' || x == '×') {
            return;
        }
        Editable editable = editText.getText();
        Selection.setSelection(editable, editText.getText().toString().length());
    }


    void equaleMethod() {
        editText.getText().toString();
        Log.d("editText", editText.getText() + "");
        String ex = editText.getText().toString();
        String exp = ex.replaceAll("×", "*").replaceAll("÷", "/");

        Expression e = new Expression(exp);

        Double answer = e.calculate();
        String S = df.format(answer);
        tvAnswer.setText(S);
        editText.setText(null);

    }
}

