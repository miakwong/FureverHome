package com.example.fureverhome;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView, speciesView, breedView, colourView, sizeView, ageView, genderView, locationView, statusView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        nameView = itemView.findViewById(R.id.name);
        speciesView = itemView.findViewById(R.id.species);
        breedView = itemView.findViewById(R.id.breed);
        colourView = itemView.findViewById(R.id.colour);
        sizeView = itemView.findViewById(R.id.size);
        ageView = itemView.findViewById(R.id.age);
        genderView = itemView.findViewById(R.id.gender);
        locationView = itemView.findViewById(R.id.location);
        statusView = itemView.findViewById(R.id.status);
    }
}
