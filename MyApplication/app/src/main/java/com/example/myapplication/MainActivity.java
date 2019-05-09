package com.example.myapplication;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w("CREATE", "CREATE");
    }

/* Para ver como se ejecuta cada on...
    @Override
    protected void onPause() {
        super.onPause();
        Log.w("PAUSE", "PAUSE");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("START", "START");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("STOP", "STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("DESTROY", "DESTROY");
    }
*/

    public void darAlta(View view){

        Log.e("Error1", "Mensaje");

    }


    // PAra coger un dato ocntenido en un elemento y pasarlo a otro activity
    public void getData(View view){
        String str = ((EditText)findViewById(R.id.editText2)).getText().toString(); // Da igual hacer el cast a EditText que a TExtView
        Log.e("Pulsa", str);

        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("dato", str);
        startActivity(intent);
    }
}
