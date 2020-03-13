package com.example.gpsapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

//for GPS need to implement the location listener
public class GPSTracker implements LocationListener {

    Context context;
    GPSTracker(Context c){
        context=c;
    }



    public Location getLocation(){

        //when get location is called this will check if permission has been granted to access location
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context, "Permission not granted",Toast.LENGTH_SHORT).show();
            return null;
        }

        LocationManager lm=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        //check that the GPS is enabled on the device
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            //get the last known location from the GPS
            Location l=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else{
            //we will create a toast saying to please enable gps
            Toast.makeText(context, "Please enable GPS",Toast.LENGTH_LONG).show();
        }
        //if gps is not enabled:
        return null;
    }

    @Override
    public void onProviderDisabled(String s) {

    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }


}
