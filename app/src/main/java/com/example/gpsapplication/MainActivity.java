package com.example.gpsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button butGetLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butGetLoc= (Button) findViewById(R.id.butGetLoc);
        //give pop up to the user to grant permission
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        //listener for the button
        butGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //use methods from the GPS tracker class
                GPSTracker g= new GPSTracker(getApplicationContext());
                Location l=g.getLocation();

                if(l != null){
                    //if location is not null, we will get the latitude and longitude
                    double lat=l.getLatitude();
                    double lon=l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: "+lat+ " \n LON: "+lon,Toast.LENGTH_LONG ).show();

                }

            }
        });
    }

}
