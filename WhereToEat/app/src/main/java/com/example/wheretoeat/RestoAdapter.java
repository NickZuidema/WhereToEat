package com.example.wheretoeat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestoAdapter extends RecyclerView.Adapter<RestoAdapter.RestoViewHolder> {

    Context context;

    ArrayList<RestoHelperClass> list;
    TextView noItemsMessage;


    public RestoAdapter(Context context, ArrayList<RestoHelperClass> list) {
        this.context = context;
        this.list = list;
        this.noItemsMessage = noItemsMessage;
    }

    @NonNull
    @Override
    public RestoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new RestoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RestoViewHolder holder, int position) {

        RestoHelperClass resto = list.get(position);
        holder.RestoName.setText(resto.getRestoName());
        holder.address.setText(resto.getAddress());
        holder.id.setText(resto.getId());
        holder.price.setText(resto.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RestoViewHolder extends RecyclerView.ViewHolder{

        TextView RestoName, address, id, price;
        String type, rating;
        Button btnGmap;

        public RestoViewHolder(@NonNull View itemView) {
            super(itemView);
            btnGmap = itemView.findViewById(R.id.btnGmap);

            RestoName = itemView.findViewById(R.id.tvRestoName);
            address = itemView.findViewById(R.id.tvAddress);
            id = itemView.findViewById(R.id.tvId);
            price = itemView.findViewById(R.id.tvPrice);

            btnGmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click for the specific item
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        RestoHelperClass selectedItem = list.get(position);

                        Context context = itemView.getContext();

                        //updateUI();

                        //Toast.makeText(context, selectedItem.getId(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, LocationActivity.class);
                        intent.putExtra("RVid", selectedItem.getId());
                        context.startActivity(intent);
                    }
                }
            });
        }
        /*private void updateUI() {
            notifyDataSetChanged(); // Notify the adapter that the dataset has changed

            // Show/hide the message based on whether items are found
            if (getItemCount() == 0) {
                noItemsMessage.setVisibility(View.VISIBLE);
            } else {
                noItemsMessage.setVisibility(View.GONE);
            }
        }*/

    }



}
