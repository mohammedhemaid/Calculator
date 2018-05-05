package com.example.moham.calculator;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;

/**
 * Created by moham on 05-May-18.
 */

public class operation {

    private static final char ADD = '+';
    private static final char SUB = '-';
    private static final char MULTI = '*';
    private static final char DIV = '÷';

    private EditText editText;
    private TextView tvAnswer;
    DecimalFormat df = new DecimalFormat("0.########");

    public operation(EditText editText, TextView tvAnswer) {
        this.editText = editText;
        this.tvAnswer = tvAnswer;
    }

    void add() {
        if (!TextUtils.isEmpty(editText.getText())) {
            editText.setText(editText.getText().toString() + ADD);
        } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
            editText.setText(tvAnswer.getText().toString() + ADD);
        }
    }

    void sub() {
        if (!TextUtils.isEmpty(editText.getText())) {
            editText.setText(editText.getText().toString() + SUB);
        } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
            editText.setText(tvAnswer.getText().toString() + SUB);
        }
    }

    void multi() {

        if (!TextUtils.isEmpty(editText.getText())) {
            editText.setText(editText.getText().toString() + MULTI);
        } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
            editText.setText(tvAnswer.getText().toString() + MULTI);
        }
    }

    void div() {
        if (!TextUtils.isEmpty(editText.getText())) {
            editText.setText(editText.getText().toString() + DIV);
        } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
            editText.setText(tvAnswer.getText().toString() + DIV);
        }

    }

    void delete() {
        int length = editText.getText().length();
        if (length > 0) {
            editText.getText().delete(length - 1, length);
        }
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
