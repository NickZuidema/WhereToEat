package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class FilterActivity extends AppCompatActivity {

    Chip price1, price2, price3, type1, type2, type3, type4, type5, rate1, rate2, rate3, rate4, rate5;
    Button cont;
    ChipGroup cgPrice, cgType, cgRate;
    String price="0", type="0", rate="0";

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        price1 = findViewById(R.id.price1);
        price2 = findViewById(R.id.price2);
        price3 = findViewById(R.id.price3);
        type1 = findViewById(R.id.type1);
        type2 = findViewById(R.id.type2);
        type3 = findViewById(R.id.type3);
        type4 = findViewById(R.id.type4);
        type5 = findViewById(R.id.type5);
        rate1 = findViewById(R.id.rate1);
        rate2 = findViewById(R.id.rate2);
        rate3 = findViewById(R.id.rate3);
        rate4 = findViewById(R.id.rate4);
        rate5 = findViewById(R.id.rate5);
        cont = findViewById(R.id.cont);

        cgPrice = findViewById(R.id.chipGroupPrice);
        cgType = findViewById(R.id.chipGroupType);
        cgRate = findViewById(R.id.chipGroupRate);

        cgPrice.setSingleSelection(true);
        cgType.setSingleSelection(true);
        cgRate.setSingleSelection(true);

        cont.setEnabled(false);


        price1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(price1.isChecked()){
                    price = "1";
                }else {
                    price ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        price2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(price2.isChecked()){
                    price = "2";
                }else {
                    price ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        price3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(price3.isChecked()){
                    price = "3";
                }else {
                    price ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type1.isChecked()){
                    type = "1";
                }else {
                    type ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type2.isChecked()){
                    type = "2";
                }else {
                    type ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        type3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type3.isChecked()){
                    type = "3";
                }else {
                    type ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        type4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type4.isChecked()){
                    type = "4";
                }else {
                    type ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        type5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type5.isChecked()){
                    type = "5";
                }else {
                    type ="0";
                }
                //Toast.makeText(getApplicationContext(), type, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        rate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate1.isChecked()){
                    rate = "1";
                }else {
                    rate ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        rate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate2.isChecked()){
                    rate = "2";
                }else {
                    rate ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        rate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate3.isChecked()){
                    rate = "3";
                }else {
                    rate ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        rate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate4.isChecked()){
                    rate = "4";
                }else {
                    rate ="0";
                }
                //Toast.makeText(getApplicationContext(), price, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });
        rate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate5.isChecked()){
                    rate = "5";
                }else {
                    rate ="0";
                }
                //Toast.makeText(getApplicationContext(), type, Toast.LENGTH_SHORT).show();
                checkCont();
            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), price + ", " + type + ", " + rate, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("strPrice", price);
                intent.putExtra("strType", type);
                intent.putExtra("strRating", rate);
                startActivity(intent);
            }
        });
    }

    public void checkCont(){
        if(price.equals("0") == false && type.equals("0") == false && rate.equals("0") == false){
            cont.setEnabled(true);
        }else{
            cont.setEnabled(false);
        }
    }
}