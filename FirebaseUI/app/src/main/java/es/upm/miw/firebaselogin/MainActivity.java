package es.upm.miw.firebaselogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

// Firebase
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import es.upm.miw.firebaselogin.Globe.BasicGlobeFragment;
import gov.nasa.worldwind.WorldWindow;

public class MainActivity extends Activity implements View.OnClickListener {

    final static String LOG_TAG = "MiW";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private static final int RC_SIGN_IN = 2018;

    BasicGlobeFragment worldGlobe = new BasicGlobeFragment();

    ToggleButton unlockButton;
    Boolean isLockSelected = new Boolean(Boolean.FALSE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //findViewById(R.id.logoutButton).setOnClickListener(this);

        /** FIREBASE */
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // user is signed in
                    CharSequence username = user.getDisplayName();
                    Toast.makeText(MainActivity.this, getString(R.string.firebase_user_fmt, username), Toast.LENGTH_LONG).show();
                    Log.i(LOG_TAG, "onAuthStateChanged() " + getString(R.string.firebase_user_fmt, username));
                    //((TextView) findViewById(R.id.textView)).setText(getString(R.string.firebase_user_fmt, username));
                } else {
                    // user is signed out
                    startActivityForResult(
                            // Get an instance of AuthUI based on the default app
                            AuthUI.getInstance().
                                    createSignInIntentBuilder().
                                    setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()
                                    )).
                                    setIsSmartLockEnabled(!BuildConfig.DEBUG /* credentials */, true /* hints */).
                                    build(),
                                    RC_SIGN_IN
                    );
                }
            }
        };

        /**< Boton de ir a la ISS */
        /*FloatingActionButton fab = findViewById(R.id.iss_locate_icon2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                worldGlobe.goToISS(Boolean.TRUE);
            }
        });*/

        /**< PARTE PARA PROGRAMAR */
        WorldWindow wwd = worldGlobe.createWorldWindow(getApplicationContext());

        FrameLayout globeLayout =  findViewById(R.id.globe);
        globeLayout.addView(wwd);

        worldGlobe.startMoving();

        unlockButton = (ToggleButton) findViewById(R.id.unlockButton2); // initiate a toggle button
        isLockSelected = unlockButton.isChecked(); // check current state of a toggle button (true or false)
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                worldGlobe.setIsUnlockOption(unlockButton.isChecked());
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, R.string.signed_in, Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, "onActivityResult " + getString(R.string.signed_in));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, R.string.signed_cancelled, Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, "onActivityResult " + getString(R.string.signed_cancelled));
                finish();
            }
        }
    }


    public void onClick(View v){

        worldGlobe.goToISS(Boolean.TRUE);
    }
}
