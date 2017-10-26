package com.example.endlessrunnerapp;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;


/**
 *  Fragment for tracking a persons movement
 */
public class RunTrackerFragment extends Fragment {
    private static final int MILLISECONDS = 1000;

    public static final int UPDATE_INTERVAL_SECONDS = 60;
    private static final long UPDATE_INTERVAL =
            MILLISECONDS * UPDATE_INTERVAL_SECONDS;

    private static final int FASTEST_INTERVAL_SECONDS = 60;
    private static final long FASTEST_INTERVAL =
            MILLISECONDS * FASTEST_INTERVAL_SECONDS;

    LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;

    private Location lastLocation;
    private final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private float distanceWalked = 0;

    private TextView textView = null;


    public RunTrackerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_run_tracker, container, false);

        textView = (TextView) v.findViewById(R.id.distanceMoved);

        if (ContextCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
        else
        {
            trackDistance();
        }

        return v;
    }


    public void trackDistance()
    {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity());

        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(mLocationRequest);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    if (lastLocation != null) {
                        // Called when a new location is found by the network location provider.
                        distanceWalked += location.distanceTo(lastLocation);
                        String newText = "Distance Traveled = " + distanceWalked;
                        textView.setText(newText);
                    }
                    lastLocation = location;
                }
            }
        };

        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this.getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback,
                    null /* Looper */);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) if (permissions.length == 1 &&
                permissions[0].equals(android.Manifest.permission.ACCESS_FINE_LOCATION) &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            trackDistance();
        }
    }

}
