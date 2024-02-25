package com.example.quadraticequationhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView equation;
    Button button;
    private EditText X1;
    private EditText X2;
    int a, b, c = 0;

    int CorrectX1, CorrectX2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equation = findViewById(R.id.equation);
        button = findViewById(R.id.button);
        X1 = findViewById(R.id.X1);
        X2 = findViewById(R.id.X2);

        generateEquation();

        X1.setOnFocusChangeListener(focusChangeListener);
        X2.setOnFocusChangeListener(focusChangeListener);
        button.setOnClickListener(clickListener);
    }

    private void generateEquation() {
        randomCoefficient(100);
        String text = a + "*X^2 + " + b + "X + " + c + " = 0";
        equation.setText(text);

        CorrectX1 = (int) Math.round((Math.sqrt(Math.abs(b*b - 4*a*c)) - b) / 2*a);
        CorrectX2 = (int) Math.round(-(Math.sqrt(Math.abs(b*b - 4*a*c)) - b) / 2*a);

        System.out.println("CorrectX1 = " + CorrectX1);
        System.out.println("CorrectX2 = " + CorrectX2);

    }

    private void randomCoefficient(int limit) {
        Random random = new Random();
        a = random.nextInt(limit);
        b = random.nextInt(limit);
        c = random.nextInt(limit);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (CorrectX1 == Integer.parseInt(X1.getText().toString()) && CorrectX2 == Integer.parseInt(X2.getText().toString())) {
                Toast.makeText(MainActivity.this, "Уравнение решено правильно!", Toast.LENGTH_SHORT).show();
                generateEquation();
                return;
            }
            if (CorrectX1 == Integer.parseInt(X1.getText().toString()) || CorrectX2 == Integer.parseInt(X2.getText().toString())) {
                Toast.makeText(MainActivity.this, "Один из корней уравнения неправильный!", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Toast.makeText(MainActivity.this, "Корни уравнения неправильные!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            final int item = v.getId();

            if (item == R.id.X1) {
                if(!hasFocus) { // если с R.id.inputX произошла дефокусировка, то
                    try {
                        int inX = Integer.parseInt(X1.getText().toString()); // определяем ввод в формате int
                        if (CorrectX1 != inX) { // если ответ введён не правильно, то
                            X1.setTextColor(Color.RED); // подкрашиваем текст в красный
                        } else { // иначе
                            X1.setTextColor(0xFF177C3A); // подкрашиваем текст в исходный цвет
                        }
                    }
                    catch (NumberFormatException exception) {
                        return;
                    }
                }
                return;
            }
            if (item == R.id.X2) {
                if(!hasFocus) { // если с R.id.inputY произошла дефокусировка, то
                    try {
                        int inY = Integer.parseInt(X2.getText().toString()); // определяем ввод в формате int
                        if (CorrectX2 != inY) { // если ответ введён не правильно, то
                            X2.setTextColor(Color.RED); // подкрашиваем текст в красный
                        } else { // иначе
                            X2.setTextColor(0xFF177C3A); // подкрашиваем текст в исходный цвет
                        }
                    }
                    catch (NumberFormatException exception) {
                        return;
                    }
                }
                return;
            }
        }
    };

}