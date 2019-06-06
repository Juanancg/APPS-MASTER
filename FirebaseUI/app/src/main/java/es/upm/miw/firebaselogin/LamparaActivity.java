package es.upm.miw.firebaselogin;

import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;

import android.util.Log;
import android.view.View;

import com.rm.rmswitch.RMSwitch;


public class LamparaActivity extends Activity {

    public static final int ACTIVITY_ID = 23;
    RMSwitch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lampara_layout);
        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.constraintLayout);
        layout.setBackgroundColor(getResources().getColor(R.color.colorSecondary));

        mSwitch = (RMSwitch) findViewById(R.id.on_off_switch);

        // Add a Switch state observer
        SharedPreferences sp = getSharedPreferences("Datos", MODE_PRIVATE);

        mSwitch.setChecked(sp.getBoolean("lampara_on", false));



        mSwitch.addSwitchObserver(new RMSwitch.RMSwitchObserver() {
            @Override
            public void onCheckStateChange(RMSwitch switchView, boolean isChecked) {

                SharedPreferences sp = getSharedPreferences("Datos", MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean("lampara_on", isChecked);
                // Para guardar los datos en el SharedPreferences
                e.commit();

            }
        });

    }
}
