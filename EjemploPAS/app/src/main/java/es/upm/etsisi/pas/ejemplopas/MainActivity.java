package es.upm.etsisi.pas.ejemplopas;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends Activity {

    private List<HashMap<String, String>> datos = new ArrayList<>();
    private SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0; i<5; i++) {
            HashMap<String, String> hm =
                    new HashMap<>();
            hm.put("nombre", "Juan " +i );
            hm.put("email", "juan"+i+"@upm.es");
            hm.put("imagen", "https://www.etsisi.upm.es/sites/all/themes/zircon/images/UPM.png");

            datos.add(hm);
        }

        sa = new MiSimpleAdapter(
                getApplicationContext(), // this
                datos,
                R.layout.fila, // layout para las filas
                new String[] {"nombre","email","imagen"},
                new int[] {R.id.nombre, R.id.email, R.id.imagen}
        );

        ((ListView)findViewById(R.id.listaview)).setAdapter(sa);


    }

    public void addFila(View view) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("nombre", "YO");
        hm.put("email", "YO");
        hm.put("imagen", "YO");
        datos.add(hm);

        sa.notifyDataSetChanged();

    }

}
