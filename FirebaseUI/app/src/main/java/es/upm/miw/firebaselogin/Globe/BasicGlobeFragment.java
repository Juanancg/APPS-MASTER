/*
 * Copyright (c) 2016 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration. All Rights Reserved.
 */

package es.upm.miw.firebaselogin.Globe;

import android.content.Context;

import android.os.Handler;
import android.support.v4.app.Fragment;

import es.upm.miw.firebaselogin.APIRest.ISSHelper;
import es.upm.miw.firebaselogin.R;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.globe.BasicElevationCoverage;
import gov.nasa.worldwind.layer.BackgroundLayer;
import gov.nasa.worldwind.layer.BlueMarbleLandsatLayer;
import gov.nasa.worldwind.layer.RenderableLayer;
import gov.nasa.worldwind.render.ImageSource;
import gov.nasa.worldwind.shape.SurfaceImage;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
public class BasicGlobeFragment extends Fragment {

    private WorldWindow wwd;

    // Create the Handler object
    private Handler handler = new Handler();


    /* Api Rest */
    private ISSHelper issHelper = new ISSHelper() ;

    /* ICON ISS */
    private int ISSIconId = R.drawable.iss_icon;
    private Sector sectorISS = new Sector();

    // Por defecto, el icono de Lock está activo
    private Boolean isUnlockOption = Boolean.FALSE;



    /******************************************************************************************//***
     * Set the value of the variable that locks or not the camera to the ISS location
     * @param value: TRUE if the lock option is choose, FALSE if not
     **********************************************************************************************/
    public void setIsUnlockOption(Boolean value){
        isUnlockOption = value;
    }

    /**
     *  CONSTRUCTOR: Creates the ISSHelper
     */
    public BasicGlobeFragment() {

        issHelper.createObject();
    }

    /******************************************************************************************//***
     * Creates a new WorldWindow (GLSurfaceView) object.
     **********************************************************************************************/
    public WorldWindow createWorldWindow(Context prueba) {

        // Create the WorldWindow (a GLSurfaceView) which displays the globe.
        this.wwd = new WorldWindow(prueba);

        // Setup the WorldWindow's layers.
        this.wwd.getLayers().addLayer(new BackgroundLayer());
        this.wwd.getLayers().addLayer(new BlueMarbleLandsatLayer());


        // Setup the WorldWindow's elevation coverages.
        this.wwd.getGlobe().getElevationModel().addCoverage(new BasicElevationCoverage());
        this.wwd.getNavigator().setAltitude(20000000);
        return this.wwd;
    }

    /******************************************************************************************//***
     * Function that moves the view to the two parameters given
     * @param latitude
     * @param longitude
     **********************************************************************************************/
    public void move(Double latitude, Double longitude, Boolean buttonClicked){

        // To not delete nonexistent layer
        if(this.wwd.getLayers().count() > 2){
            this.wwd.getLayers().removeLayer(2);
        }

        moveISSicon(latitude, longitude);

        if(isUnlockOption == Boolean.FALSE || buttonClicked == Boolean.TRUE) {
            this.wwd.getNavigator().setLatitude(latitude);
            this.wwd.getNavigator().setLongitude(longitude);
        }
        this.wwd.requestRedraw();
    }


    /******************************************************************************************//***
     *
     * @param latitude
     * @param longitude
     **********************************************************************************************/
    public void moveISSicon(Double latitude, Double longitude){
        // Configure a Surface Image to display an Android resource showing the iss logo.
        sectorISS.set(latitude, longitude,7.5,7.5);
        SurfaceImage surfaceImageResource = new SurfaceImage(sectorISS, ImageSource.fromResource(ISSIconId));
        RenderableLayer issIconLayer = new RenderableLayer("Surface Image");
        issIconLayer.addRenderable(surfaceImageResource);
        this.wwd.getLayers().addLayer(issIconLayer);
    }

    /******************************************************************************************//***
     * Hace una update a la API para obtener la localización de la ISS y mueve la vista a esa
     * localización
     **********************************************************************************************/
    public void goToISS(Boolean buttonClicked){
        issHelper.realizarUpdate();
        move(issHelper.getLatitude(), issHelper.getLongitude(), buttonClicked);
    }

    /******************************************************************************************//***
     *
     **********************************************************************************************/
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            goToISS(Boolean.FALSE);
            handler.postDelayed(this, 3000);
        }
    };

    /******************************************************************************************//***
     *
     **********************************************************************************************/
    public void startMoving() {
        // Start the initial runnable task by posting through the handler
        handler.post(runnableCode);
    }

    /******************************************************************************************//***
     * Gets the WorldWindow (GLSurfaceView) object.
     **********************************************************************************************/
    public WorldWindow getWorldWindow() {
        return this.wwd;
    }

    /******************************************************************************************//***
     * Resumes the WorldWindow's rendering thread
     **********************************************************************************************/
    @Override
    public void onResume() {
        super.onResume();
        this.wwd.onResume(); // resumes a paused rendering thread
    }

    /******************************************************************************************//***
     * Pauses the WorldWindow's rendering thread
     **********************************************************************************************/
    @Override
    public void onPause() {
        super.onPause();
        this.wwd.onPause(); // pauses the rendering thread
    }

    /******************************************************************************************//***
     *
     **********************************************************************************************/
    public void checkIoTLamp(){


    }
}