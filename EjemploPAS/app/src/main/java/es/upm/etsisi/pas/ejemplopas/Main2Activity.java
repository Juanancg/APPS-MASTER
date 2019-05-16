package es.upm.etsisi.pas.ejemplopas;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends Activity {

    public static final int ACTIVITY_ID = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onOK(View v) {
        Log.e("kkk", "OK");

        SharedPreferences sp = getSharedPreferences("Datos", MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean("dentro", true);
        e.commit();

        Intent intent = new Intent();
        intent.putExtra("email",
                ((TextView)findViewById(R.id.txt_login)).getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onCANCEL(View v) {
        Log.e("kkk", "CANCEL");

        setResult(RESULT_CANCELED);
        finish();
    }
}
