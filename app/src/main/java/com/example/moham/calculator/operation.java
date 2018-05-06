package com.example.moham.calculator;

import android.text.Editable;
import android.text.Selection;
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

    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MULTI = "×";
    private static final String DIV = "÷";

    private EditText editText;
    private TextView tvAnswer;
    DecimalFormat df = new DecimalFormat("0.########");

    public operation(EditText editText, TextView tvAnswer) {
        this.editText = editText;
        this.tvAnswer = tvAnswer;
    }

    void add() {

        if (lastExp()) {
            return;
        } else {
            if (!TextUtils.isEmpty(editText.getText())) {
                insert(ADD);
            } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
                insert(tvAnswer.getText().toString() + ADD);
            }
            select();
        }
    }

    void sub() {
        if (lastExp()) {
            return;
        } else {
            if (!TextUtils.isEmpty(editText.getText())) {
                insert(SUB);
            } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
                insert(tvAnswer.getText().toString() + SUB);
            }
            select();
        }
    }

    void multi() {
        if (lastExp()) {
            return;
        } else {
            if (!TextUtils.isEmpty(editText.getText())) {
                insert(MULTI);
            } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
                insert(tvAnswer.getText().toString() + MULTI);
            }
            select();
        }
    }

    void div() {
        if (lastExp()) {
            return;
        } else {
            if (!TextUtils.isEmpty(editText.getText())) {
                insert(DIV);
            } else if (TextUtils.isEmpty(editText.getText()) && tvAnswer != null) {
                insert(tvAnswer.getText().toString() + DIV);
            }
            select();
        }
    }

    void delete() {
        int length = editText.getText().length();
        if (length > 0) {
            editText.getText().delete(length - 1, length);
        }
    }

    void equaleMethod() {
        if (lastExp()) {
            return;
        } else if (!TextUtils.isEmpty(editText.getText())){
            String ex = editText.getText().toString();
            String exp = ex.replaceAll("×", "*").replaceAll("÷", "/");

            Expression e = new Expression(exp);

            Double answer = e.calculate();
            String S = df.format(answer);
            tvAnswer.setText(S);
            editText.setText(null);
            select();
        }
    }

    public void insert(String n) {
        editText.setText(editText.getText() + n);
        select();
    }


    void select() {
        Editable editable = editText.getText();
        Selection.setSelection(editable, editText.getText().toString().length());
    }

    boolean lastExp() {
        boolean flag = false;
        if (!TextUtils.isEmpty(editText.getText())) {
            String ex = editText.getText().toString();
            char x = ex.charAt(ex.length() - 1);
            if (x == '.' || x == '÷' || x == '+' || x == '-' || x == '×') {

                flag = true;
            }

        } else if (TextUtils.isEmpty(editText.getText())&&TextUtils.isEmpty(tvAnswer.getText())) {
            flag = true;
        }
        return flag;
    }
}
