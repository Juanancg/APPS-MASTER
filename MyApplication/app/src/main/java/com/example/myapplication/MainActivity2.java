package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.e("Segunda", "He arrancado");

        Intent intent = getIntent();
        String str = intent.getStringExtra("dato"); // Consigue el dato que se ha pasado entre actividades

        Log.e("Dato recibido", str);

        ((TextView)findViewById(R.id.textView)).setText(str); // Asi se muestra el dato que se ha pasado desde otra actividad
    }
}
