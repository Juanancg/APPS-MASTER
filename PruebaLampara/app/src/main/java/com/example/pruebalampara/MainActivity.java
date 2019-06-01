package com.example.pruebalampara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pruebalampara.fcube.commands.FCColor;
import com.example.pruebalampara.fcube.config.FeedbackCubeConfig;
import com.example.pruebalampara.fcube.config.FeedbackCubeManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onGreen(View v){
        FCColor fcc = new FCColor(FeedbackCubeConfig.getSingleInstance().getIp(), "0",
                "200", "0");
        new FeedbackCubeManager().execute(fcc);
    }

    public void onRed(View v){
        FCColor fcc = new FCColor(FeedbackCubeConfig.getSingleInstance().getIp(), "220",
                "0", "0");
        new FeedbackCubeManager().execute(fcc);
    }

    public void onBlue(View v){
        FCColor fcc = new FCColor(FeedbackCubeConfig.getSingleInstance().getIp(), "0",
                "0", "220");
        new FeedbackCubeManager().execute(fcc);
    }

    public void onYellow(View v){
        FCColor fcc = new FCColor(FeedbackCubeConfig.getSingleInstance().getIp(), "200",
                "200", "0");
        new FeedbackCubeManager().execute(fcc);
    }
}
