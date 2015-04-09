package com.samurai.guess_the_flag;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements View.OnClickListener {
    private CheckBox
            box1,
            box2,
            box3,
            box4;
    private GoogleMap mMap;
    private LatLng addressLngLat;
    private double longtitude, latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        //instantiate the chockboxes
        instBoxes();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.setMyLocationEnabled(true);

            }
        }
    }


    private void setUpMap(double latitude, double longtitude) {
                mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longtitude))
                .title("Bulgaria")
                .snippet("Population: 76733")).showInfoWindow();
    }

    public void instBoxes(){
        box1 = (CheckBox) findViewById(R.id.check1);
        box2 = (CheckBox) findViewById(R.id.check2);
        box3 = (CheckBox) findViewById(R.id.check3);
        box4 = (CheckBox) findViewById(R.id.check4);

        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        box4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check1){
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.check2){
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.check3){
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.check4){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();


            mapAnim(latitude=42.733883, longtitude = 25.48583);
        }

    }

    public void mapAnim(final double latitude, final double longtitude){
        addressLngLat = new LatLng(latitude, longtitude);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                addressLngLat, (float) 5.00), new GoogleMap.CancelableCallback() {

            @Override
            public void onFinish() {
                Log.d("animation", "onFinishCalled");
                setUpMap(latitude, longtitude);
            }

            @Override
            public void onCancel() {
                Log.d("animation", "onCancel");
            }
        });
    }
}
