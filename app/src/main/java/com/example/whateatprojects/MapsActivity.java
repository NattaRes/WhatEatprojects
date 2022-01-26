package com.example.whateatprojects;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.whateatprojects.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    FirebaseDatabase database;
    DatabaseReference positeus;

    Double latitude,longitude;
    String lati, longi, coResID = "", ResName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        coResID = getIntent().getStringExtra("sendresID");

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Resname/" + coResID);

        ValueEventListener listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                lati = snapshot.child("position").child("latitude").getValue(String.class);
                longi  = snapshot.child("position").child("longitude").getValue(String.class);

                ResName = snapshot.child("Name").getValue(String.class);

                latitude = Double.parseDouble(lati);
                longitude = Double.parseDouble(longi);


               LatLng location = new LatLng(latitude,longitude);

               mMap.addMarker(new MarkerOptions().position(location).title(ResName));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // Add a marker in Sydney and move the camera
    }
}