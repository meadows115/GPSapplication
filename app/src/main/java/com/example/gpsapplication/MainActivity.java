package com.example.gpsapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

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
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
                    Toast.makeText(getApplicationContext(), "Values Saved in Txt File",Toast.LENGTH_LONG ).show();
                    String textToSaveLat = "Latitude: " + Double.toString( lat) ;
                    String textToSaveLon = "\nLongitude: "+ Double.toString( lon);

                File root = android.os.Environment.getExternalStorageDirectory();

                File dir = new File (root.getAbsolutePath() + "/download");
                dir.mkdirs();
                File file = new File(dir, "longitudelatitude.txt");

                try {
                    FileOutputStream f = new FileOutputStream(file);
                    PrintWriter pw = new PrintWriter(f);
                    f.write(textToSaveLat.getBytes());
                    f.write(textToSaveLon.getBytes());
                    f.close();
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


