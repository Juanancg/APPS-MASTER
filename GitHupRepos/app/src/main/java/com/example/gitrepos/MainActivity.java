package com.example.gitrepos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gitrepos.apirest.GitHubService;
import com.example.gitrepos.apirest.Repositorio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private List<String> datos = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                datos);

        ((ListView)findViewById(R.id.lista)).setAdapter(arrayAdapter);


    /* Peticiones a URL tienen que ser asincornas para que funcione bien (No dejar bloqueado el UI) */

        /**< 1- Crear objeto retrofit */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()) // Si no añadimos esto, devuelve un JSON de textos
                .build();

        GitHubService service = retrofit.create(GitHubService.class);


        /**< 2 - Crear peticion asincrona */

        service.listRepos("juanancg").enqueue(new Callback<List<Repositorio>>() { // enqueue hace la peticion asincrona - execute la hace sincrona

            @Override
            public void onResponse(Call<List<Repositorio>> call, Response<List<Repositorio>> response) {

                // Creamos una lista con todos los repositorios
                List<Repositorio> respuesta = response.body();

                // Bucle para iterar en todos los repositorios de la lista e imprimir el nombre de los repositorios
                for(Repositorio rep : respuesta){

                    datos.add(rep.getName());
                    Log.e("Repositiorio", rep.getName());

                }

                // refrescar la lista :
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Repositorio>> call, Throwable t) {

                Log.e("FALLO",  "FALLO");

            }
        });
    }
}
