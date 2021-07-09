package com.example.layoutsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewNumber;
    private TextView textViewOp;
    private String textNumber;
    private List<Double> numbers;
    private List<String> ops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewNumber = findViewById(R.id.textViewNumber);
        textViewOp = findViewById(R.id.textViewOp);
        numbers = new ArrayList<>();
        ops = new ArrayList<>();
        textNumber = "";
    }

    public void setNumber(View view) {
        String input = ((Button)view).getText().toString();
        if(textViewNumber.getText().toString() != "0"){
            textNumber = textNumber + input;
            textViewNumber.setText(textViewNumber.getText().toString() + input);
        }
    }

    public void setPoint(View view) {
        String input = ((Button)view).getText().toString();
        if(!textNumber.contains(input)){
            textNumber = textNumber + input;
            textViewNumber.setText(textViewNumber.getText().toString() + input);
        }
    }

    public void clear(){
        textViewOp.setText("");
        textViewNumber.setText("");
        textNumber = "";
        numbers.clear();
        ops.clear();
    }

    private void result(){
        int index = 0;
        double res = 0;
        for(int i = 0; i < numbers.size()-1; i++){
            String op = ops.get(index);
            switch (op){
                case "+":
                    res = numbers.get(i) + numbers.get(i+1);
                    numbers.set(index+1, res);
                    break;
                case "-":
                    res = numbers.get(i) - numbers.get(i+1);
                    numbers.set(index+1, res);
                    break;
                case "*":
                    res = numbers.get(i) * numbers.get(i+1);
                    numbers.set(index+1, res);
                    break;
                case "/":
                    res = numbers.get(i) / numbers.get(i+1);
                    numbers.set(index+1, res);
                    break;
            }
            index = index + 1;
        }
        String resText = String.valueOf(res);
        textNumber = resText;
        textViewNumber.setText(resText);
        numbers.clear();
        ops.clear();
    }

    public void setOp(View view) {
        String selectedOp = ((Button)view).getText().toString();

        switch (selectedOp) {
            case "+":
            case "-":
            case "*":
            case "/":
                if (textNumber != "") {
                    if(textNumber.indexOf(".") == textNumber.length()-1){
                        textNumber = textNumber + "0";
                    }
                    numbers.add(Double.parseDouble(textNumber));
                    ops.add(selectedOp);
                    textViewOp.setText(selectedOp);
                    textNumber = "";
                    textViewNumber.setText("");
                }
                break;
            case "=":
                textViewOp.setText(selectedOp);
                if(ops.size() == 0){
                    break;
                }
                if(textNumber == "") {
                    ops.remove(ops.size()-1);
                } else {
                    numbers.add(Double.parseDouble(textNumber));
                }
                result();
                break;
        }
    }
}