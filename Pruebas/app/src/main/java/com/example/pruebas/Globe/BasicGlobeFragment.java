/*
 * Copyright (c) 2016 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration. All Rights Reserved.
 */

package com.example.pruebas.Globe;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.pruebas.APIRest.ISSHelper;
import com.example.pruebas.APIRest.ISSLocService;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.globe.BasicElevationCoverage;
import gov.nasa.worldwind.layer.BackgroundLayer;
import gov.nasa.worldwind.layer.BlueMarbleLandsatLayer;

public class BasicGlobeFragment extends Fragment {

    private WorldWindow wwd;
    // Create the Handler object (on the main thread by default)
    Handler handler = new Handler();

    /* Variables para controlar la posicion */
    Double lat = 15.0;
    Double longi = 0.0;

    /* Api Rest */
    ISSHelper issHelper;


    public BasicGlobeFragment() {
    }

    /**
     * Creates a new WorldWindow (GLSurfaceView) object.
     */
    public WorldWindow createWorldWindow(Context prueba) {
        // Create the WorldWindow (a GLSurfaceView) which displays the globe.
        //Context prueba = getContext();
        this.wwd = new WorldWindow(prueba);
        // Setup the WorldWindow's layers.
        this.wwd.getLayers().addLayer(new BackgroundLayer());
        this.wwd.getLayers().addLayer(new BlueMarbleLandsatLayer());
        // Setup the WorldWindow's elevation coverages.
        this.wwd.getGlobe().getElevationModel().addCoverage(new BasicElevationCoverage());
        return this.wwd;
    }

    public void move(Double latitude, Double longitude){

        this.wwd.getNavigator().setLatitude(latitude);
        this.wwd.getNavigator().setLongitude(longitude);
        this.wwd.requestRedraw();
    }

    // Define the code block to be executed
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            longi = longi + 0.05;

            move(lat, longi);

            handler.postDelayed(this, 100);
        }
    };

    public void startMoving() {
        // Start the initial runnable task by posting through the handler
        handler.post(runnableCode);
    }

    /**
     * Gets the WorldWindow (GLSurfaceView) object.
     */
    public WorldWindow getWorldWindow() {
        return this.wwd;
    }

    /**
     * Adds the WorldWindow to this Fragment's layout.

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_globe, container, false);
        FrameLayout globeLayout = (FrameLayout) rootView.findViewById(R.id.globe);

        // Add the WorldWindow view object to the layout that was reserved for the globe.
        Context prueba = getContext();
        globeLayout.addView(this.createWorldWindow(prueba));

        return rootView;
    }

    /**
     * Resumes the WorldWindow's rendering thread
     */
    @Override
    public void onResume() {
        super.onResume();
        this.wwd.onResume(); // resumes a paused rendering thread
    }

    /**
     * Pauses the WorldWindow's rendering thread
     */
    @Override
    public void onPause() {
        super.onPause();
        this.wwd.onPause(); // pauses the rendering thread
    }
}