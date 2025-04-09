package com.example.fureverhome.ui.shelter_management;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView nameView;
    public TextView speciesView;
    public TextView breedView;
    public TextView colourView;
    public TextView sizeView;
    public TextView ageView;
    public TextView genderView;
    public TextView locationView;
    public TextView statusView;
    public Button viewButton;
    public Button updateButton;
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

        viewButton = itemView.findViewById(R.id.viewButton);
        updateButton = itemView.findViewById(R.id.updateButton);
    }
}
