package com.example.pruebas;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Camera;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globe.Globe;
import gov.nasa.worldwind.layer.BackgroundLayer;
import gov.nasa.worldwind.layer.BlueMarbleLandsatLayer;

public class CameraViewFragment extends BasicGlobeFragment {

    /**
     * Creates a new WorldWindow with its camera positioned at a given location and configured to point in a given
     * direction.
     */
    //@Override

    // Create the Handler object (on the main thread by default)
    Handler handler = new Handler();
    /*WorldWindow wwd;
    // Create the new camera view
    Camera camera = new Camera();
    double heading;
    double tilt;
    // Create a view of Point Mugu airport as seen from an aircraft above Oxnard, CA.
    Position aircraft = new Position(34.2, -119.2, 3000);           // Above Oxnard CA, altitude in meters
    Position airport = new Position(34.1192744, -119.1195850, 4.0); // KNTD airport, Point Mugu CA, altitude MSL
    Globe globe;*/


    public WorldWindow createWorldWindow(Context prueba) {
        // Let the super class (BasicGlobeFragment) do the creation
        // WorldWindow wwd = super.createWorldWindow();
        // Create a WorldWindow (a GLSurfaceView)...
        WorldWindow wwd = new WorldWindow(prueba);
        // ... and add some map layers
        wwd.getLayers().addLayer(new BackgroundLayer());
        wwd.getLayers().addLayer(new BlueMarbleLandsatLayer());

        Position aircraft = new Position(34.2, -119.2, 3000);           // Above Oxnard CA, altitude in meters
        Position airport = new Position(34.1192744, -119.1195850, 4.0); // KNTD airport, Point Mugu CA, altitude MSL
        // Compute heading and tilt angles from aircraft to airport
        Globe globe = wwd.getGlobe();
        double heading = aircraft.greatCircleAzimuth(airport);
        double distanceRadians = aircraft.greatCircleDistance(airport);
        double distance = distanceRadians * globe.getRadiusAt(aircraft.latitude, aircraft.longitude);
        double tilt = Math.toDegrees(Math.atan(distance / aircraft.altitude));

        Camera camera = new Camera();
        //camera.set(aircraft.latitude, aircraft.longitude, aircraft.altitude, WorldWind.ABSOLUTE, heading, tilt, 0); // No roll
        camera.set(0, 0, 0, 0, 0, 0, 0); // No roll

        // Apply the view
        wwd.getNavigator().setAsCamera(globe, camera);

        // This works too!  Using the fluid api to manipulate the Navigator's camera:
//        wwd.getNavigator()
//            .setLatitude(aircraft.latitude)
//            .setLongitude(aircraft.longitude)
//            .setAltitude(aircraft.altitude)
//            .setHeading(heading)
//            .setTilt(tilt);

        return wwd;
    }


    // Define the code block to be executed
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread

            /*Log.d("Handlers", "Called on main thread");
            aircraft.latitude = aircraft.latitude + 1;
            camera.set(aircraft.latitude, aircraft.longitude, aircraft.altitude, WorldWind.ABSOLUTE, heading, tilt, 0); // No roll
            wwd.getNavigator().setAsCamera(globe, camera);
            // Repeat this the same runnable code block again another 1 seconds
            // 'this' is referencing the Runnable object
            handler.postDelayed(this, 1000);*/
        }
    };

    public void startMoving() {
        // Start the initial runnable task by posting through the handler
        handler.post(runnableCode);
    }
}