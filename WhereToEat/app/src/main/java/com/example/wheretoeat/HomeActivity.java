package com.example.wheretoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.StringPrepParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    String [] choicePrice = {"₱", "₱₱", "₱₱₱"};
    String [] choiceType = {"Eatery", "Fast Food", "Café", "Casual Dining", "Fine Dining"};
    String [] choiceRating = {"1 ★", "2 ★", "3 ★", "4 ★", "5 ★"};

    Integer intPrice=1, intType=1, intRating=1;
    String strPrice, strType, strRating;

    AutoCompleteTextView ACTVPrice, ACTVType, ACTVRating;

    ArrayAdapter<String> adapterPrice, adapterType, adapterRating;
    RecyclerView recyclerView;
    DatabaseReference database;
    RestoAdapter restoAdapter;
    ArrayList<RestoHelperClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        strPrice = getIntent().getStringExtra("strPrice");
        strType = getIntent().getStringExtra("strType");
        strRating = getIntent().getStringExtra("strRating");
        //Toast.makeText(getApplicationContext(), strPrice + ", " + strType + ", " + strRating, Toast.LENGTH_SHORT).show();

        intPrice = Integer.parseInt(strPrice);
        intType = Integer.parseInt(strType);
        intRating = Integer.parseInt(strRating);


        callRecycler();

        ACTVPrice = findViewById(R.id.listPrice);
        adapterPrice = new ArrayAdapter<String>(this, R.layout.list_price, choicePrice);

        ACTVPrice.setText(choicePrice[intPrice-1]);
        ACTVPrice.setAdapter(adapterPrice);

        ACTVPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String itemPrice = adapterView.getItemAtPosition(i).toString();
                intPrice = i+1;
                //Toast.makeText(HomeActivity.this, "Price: " + intPrice, Toast.LENGTH_SHORT).show();
                callRecycler();
            }
        });


        ACTVType = findViewById(R.id.listType);
        adapterType = new ArrayAdapter<String>(this, R.layout.list_type, choiceType);

        ACTVType.setText(choiceType[intType-1]);
        ACTVType.setAdapter(adapterType);


        ACTVType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String itemType = adapterView.getItemAtPosition(i).toString();
                intType = i+1;
                //Toast.makeText(HomeActivity.this, "Type: " + intType, Toast.LENGTH_SHORT).show();
                callRecycler();
            }
        });


        ACTVRating = findViewById(R.id.listRating);
        adapterRating = new ArrayAdapter<String>(this, R.layout.list_rating, choiceRating);

        ACTVRating.setText(choiceRating[intRating-1]);
        ACTVRating.setAdapter(adapterRating);

        ACTVRating.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String itemRating = adapterView.getItemAtPosition(i).toString();
                intRating = i+1;
                //Toast.makeText(HomeActivity.this, "Rating: " + intRating, Toast.LENGTH_SHORT).show();
                callRecycler();
            }
        });

        callRecycler();

    }
    public void callRecycler(){
        recyclerView = findViewById(R.id.restoList);
        database = FirebaseDatabase.getInstance().getReference("dbresto");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list  =new ArrayList<>();
        restoAdapter = new RestoAdapter(this,list);
        recyclerView.setAdapter(restoAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    RestoHelperClass resto = dataSnapshot.getValue(RestoHelperClass.class);

                    if(resto.price.equals(intPrice.toString()) && resto.type.equals(intType.toString()) && resto.rating.equals(intRating.toString())){
                        list.add(resto);
                    }
                }
                restoAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}