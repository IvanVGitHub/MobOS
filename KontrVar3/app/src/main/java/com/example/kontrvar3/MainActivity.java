package com.example.kontrvar3;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Dialog dialog; //объявляем диалоговое окно
    EditText RValue, HValue, DValue; //объявляем окна ввода радиуса, высоты и плотности конуса
    CheckBox Vchb, Mchb; //объявляем чекбоксы объёма и массы
    double ResultV, ResultM; //объём и масса конуса
    double Rds = 0; //радиус конуса
    double Hght = 0; //высота конуса
    double Dnst = 0; //плотность конуса

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //инициалзация диалогового окна в меню
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialogwindow);

        //создаём обработчик нажатия кнопки OK в меню
        Button button = (Button) dialog.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //считываем введённые значение параметров конуса
                RValue = (EditText) dialog.findViewById(R.id.editText1);
                HValue = (EditText) dialog.findViewById(R.id.editText2);
                DValue = (EditText) dialog.findViewById(R.id.editText3);

                //переводим считанные значения в числовой формал double
                Rds = Double.parseDouble(RValue.getText().toString());
                Hght = Double.parseDouble(HValue.getText().toString());
                Dnst = Double.parseDouble(DValue.getText().toString());

                //считываем статус чекбоксов
                Vchb = (CheckBox) dialog.findViewById(R.id.checkBox1);
                Mchb = (CheckBox) dialog.findViewById(R.id.checkBox2);

                dialog.dismiss(); //скрываем диалоговое окно, но все его данные занесены в кэш
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Input) {
            dialog.show();
        }
        else if (id == R.id.Work) {
            String RESULT = "Результат вычислений:";
            if (Vchb.isChecked()) {
                ResultV = (Hght * Math.PI * Math.pow(Rds, 2)) / 3; //объём конуса
                RESULT += "\nОбъём конуса: " + String.valueOf(ResultV) + " м3";
            }
            if(Mchb.isChecked()) {
                ResultM = Dnst * ResultV; //масса конуса
                RESULT += "\nМасса конуса: " + String.valueOf(ResultM) + " кг";
            }
            Toast.makeText(MainActivity.this, RESULT, Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.Exit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}