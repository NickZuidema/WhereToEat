package com.example.wheretoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.wheretoeat.databinding.ActivityDirectionsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DirectionsActivity extends AppCompatActivity {

    private String loc;
    ActivityDirectionsBinding binding;
    DatabaseReference reference;
    private String restoLat;
    private String restoLng;
    private String id = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loc = getIntent().getStringExtra("location");
        loc = loc.replace("lat/lng: (", "");
        loc = loc.replace(",", ", ");
        loc = loc.replace(")", "");

        //String id = "2";
        id = getIntent().getStringExtra("RVid");
        readData(id);
        //inte
    }

    private void readData(String id) {

        reference = FirebaseDatabase.getInstance().getReference("dbresto");
        reference.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if(task.getResult().exists()){


                        DataSnapshot dataSnapshot = task.getResult();

                        restoLat = String.valueOf(dataSnapshot.child("lat").getValue());
                        restoLng = String.valueOf(dataSnapshot.child("lng").getValue());
                        //Toast.makeText(getApplicationContext(), loc, Toast.LENGTH_LONG).show();
                        //Toast.makeText(DirectionsActivity.this, restoLat + restoLng, Toast.LENGTH_SHORT).show();
                        Uri uri = Uri.parse("https://www.google.com/maps/dir/" + loc + "/" + restoLat + ", " + restoLng);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        intent.setPackage("com.google.android.apps.maps");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }else{
                        //Toast.makeText(DirectionsActivity.this, "id does not exist", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //Toast.makeText(DirectionsActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}