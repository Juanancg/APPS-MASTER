package com.example.apploginpass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/* COMANDOS */
// Boton derecho - Generar - Override method -- buscar el que se quiere
// alt enter para cargar los imports que aparecen en rojo/azul


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO

        SharedPreferences sp = getSharedPreferences("Datos", MODE_PRIVATE); // Si no existe, crea el .xml y si existe no hace nada

        //if(!sp.getBoolean("dentro", false)){

            // Si la key dentro no existe, manda al user a meter su login:

            Intent intent = new Intent(this, Main2Activity.class);

            startActivityForResult(intent, Main2Activity.ACTIVITY_ID); // Para delvolver el id de la actividad que esta esperando


        //}


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case Main2Activity.ACTIVITY_ID:

                if(resultCode == RESULT_OK){

                    // Ponemos en el texto del movil, el email que ha introducido en el activity2
                    ((TextView)findViewById(R.id.lbl_texto)).setText(data.getStringExtra("email"));

                    Log.e("DATOS RECIBIDOS:", data.getStringExtra("email"));


                } else if (resultCode == RESULT_CANCELED){
                    Log.e("mgs", "Ha pulsado el CANCEL");

                } else{
                    Log.e("msg", "Algo falla con el resultado, es desconocido el valor retornado");
                }

                break;
            default: Log.e("msg", "Algo falla porque no es el activity id que conocemos");
        }

        // Cuando des a Ok/Cancel, imprimirá el resultado y el codigo de la actividad que hemos establecido en el activity2
        Log.e("--------------", "Codigo Actividad: "+ requestCode + ", Resultado: " + resultCode);
    }
}
