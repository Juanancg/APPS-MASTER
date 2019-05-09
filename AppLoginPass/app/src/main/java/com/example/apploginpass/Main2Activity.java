package com.example.apploginpass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public static final int ACTIVITY_ID = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    public void onOk(View v){
        Log.e("hola", "Ok"); // Probar si el codigo funciona - Cuando da al OK deberia salir el mensaje

        /* Abrir el archivo de las SharedPreferences para guardar que el user ha hecho login: */
        SharedPreferences sp = getSharedPreferences("Datos", MODE_PRIVATE); // MODO PRIVADO: Solo esta app puede abrirlo
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean("dentro", true);
        // Para guardar los datos en el SharedPreferences
        e.commit();

        /* Pasar los datos al activity 1:  */
        // PAra pasar Datos de un activity a otro se utiliza un intent
        Intent intent = new Intent();
        intent.putExtra("email",
                ((TextView)findViewById(R.id.text_login)).getText().toString());

        setResult(RESULT_OK, intent); // Valor a devolver
        finish();
    }

    public void onCANCEL(View v){
        Log.e("hola", "CANCEL"); // Probar si el codigo funciona - Cuando da al CANCEL deberia salir el mensaje

        setResult(RESULT_CANCELED); // Valor a devolver
        finish();
    }
}
