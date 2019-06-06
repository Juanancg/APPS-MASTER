package es.upm.miw.firebaselogin.APIRest;

import android.annotation.SuppressLint;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ISSHelper {

    private ISSLocService service;

    private double latitude = 0.0;
    private double longitude = 0.0;
    private String lastupdate = new String();

    public void createObject(){
        /**< 1- Crear objeto retrofit */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.open-notify.org/")
                .addConverterFactory(GsonConverterFactory.create()) // Si no aĂ±adimos esto, devuelve un JSON de textos
                .build();
        service = retrofit.create(ISSLocService.class);
        realizarUpdate();
    }

    public double getLatitude(){ return latitude;}

    public double getLongitude(){ return longitude;}

    public String getLastupdate(){ return lastupdate;}

    /**
     * Recupera un recurso y lo muestra en el TextView tvContenido
     *
     */
    public void realizarUpdate( /*ISSLocService service*/) {

        service.jsonISSLocation().enqueue(new Callback<ISSLocationResponse>() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onResponse(Call<ISSLocationResponse> call, Response<ISSLocationResponse> response) {

                // Copiar el cuerpo de la respuesta a un Tipo ISSLocationResponse
                ISSLocationResponse respuesta = response.body();

                longitude = Double.parseDouble(respuesta.getIssPosition().getLongitude());
                latitude = Double.parseDouble(respuesta.getIssPosition().getLatitude());
                lastupdate = respuesta.getTimestampFormatted();

            }

            @Override
            public void onFailure(Call<ISSLocationResponse> call, Throwable t) {
                Log.e("FALLO", "FALLO");
            }
        });
    }
}
