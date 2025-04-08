package com.example.fureverhome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    List<Animal> animals;

    public MyAdapter(Context context, List<Animal> animals) {
        this.context = context;
        this.animals = animals;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.animal_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Animal animal = animals.get(position);

        holder.nameView.setText(animal.getName());
        holder.speciesView.setText(animal.getSpecies());
        holder.breedView.setText(animal.getBreed());
        holder.colourView.setText(animal.getColour());
        holder.sizeView.setText(animal.getSize());
        holder.ageView.setText(String.valueOf(animal.getAge()));
        holder.genderView.setText(animal.getGender());
        holder.locationView.setText(animal.getLocation());
        holder.statusView.setText(animal.getStatus());
        holder.imageView.setImageResource(animal.getImage());

        // Handle View button click
        holder.viewButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AnimalDetailActivity.class);
            intent.putExtra("animal", animal); // FIXED
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        // Handle Update button click
        holder.updateButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AnimalUpdateActivity.class);
            intent.putExtra("animal", animal); // FIXED
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}
