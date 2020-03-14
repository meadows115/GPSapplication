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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
                    Double lat=l.getLatitude();
                    Double lon=l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: "+lat+ " \n LON: "+lon,Toast.LENGTH_LONG ).show();


                        String textToSaveLat = "Latitude: " + Double.toString( lat) ;
                        String textToSaveLon = "\nLongitude: "+ Double.toString( lon);


                        try {
                            FileOutputStream fileOutputStream = openFileOutput("LatitudeLongitude.txt", MODE_PRIVATE);
                            fileOutputStream.write(textToSaveLat.getBytes());
                            fileOutputStream.write(textToSaveLon.getBytes());
                            fileOutputStream.close();

                            Toast.makeText(getApplicationContext(), "Text Saved", Toast.LENGTH_SHORT).show();


                           // inputField.setText("");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                }

            }
        });
    }


}
