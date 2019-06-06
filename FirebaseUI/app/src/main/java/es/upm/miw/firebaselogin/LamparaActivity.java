package es.upm.miw.firebaselogin;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;

import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.rm.rmswitch.RMSwitch;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class LamparaActivity extends Activity {

    public static final int ACTIVITY_ID = 23;
    RMSwitch mSwitch;
    private FusedLocationProviderClient client;

    // Create the Handler object
    private Handler handler = new Handler();

    Boolean isCheckedGlobal = Boolean.FALSE;
    Double longitude;
    Double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lampara_layout);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        layout.setBackgroundColor(getResources().getColor(R.color.colorSecondary));

        mSwitch = (RMSwitch) findViewById(R.id.on_off_switch);

        // Add a Switch state observer
        SharedPreferences sp = getSharedPreferences("Datos", MODE_PRIVATE);

        mSwitch.setChecked(sp.getBoolean("lampara_on", false));


        mSwitch.addSwitchObserver(new RMSwitch.RMSwitchObserver() {
            @Override
            public void onCheckStateChange(RMSwitch switchView, boolean isChecked) {
                isCheckedGlobal = isChecked;
                SharedPreferences sp = getSharedPreferences("Datos", MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean("lampara_on", isChecked);
                // Para guardar los datos en el SharedPreferences
                e.commit();


                /** CHECKEAR LAS POSICIONES */

                if (isChecked == Boolean.TRUE) {

                    requestPermission();
                    client = LocationServices.getFusedLocationProviderClient(LamparaActivity.this);

                    if (ActivityCompat.checkSelfPermission(LamparaActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    client.getLastLocation().addOnSuccessListener(LamparaActivity.this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {


                            if (location != null) {

                                latitude = location.getLatitude();
                                longitude = location.getLongitude();



                            }
                        }
                    });


                }
            }
        });



    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }




}
