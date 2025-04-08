package com.example.fureverhome;

import android.content.Context;
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
        holder.nameView.setText(animals.get(position).getName());
        holder.speciesView.setText(animals.get(position).getSpecies());
        holder.breedView.setText(animals.get(position).getBreed());
        holder.colourView.setText(animals.get(position).getColour());
        holder.sizeView.setText(animals.get(position).getSize());
        holder.ageView.setText(String.valueOf(animals.get(position).getAge()));
        holder.genderView.setText(animals.get(position).getGender());
        holder.locationView.setText(animals.get(position).getLocation());
        holder.statusView.setText(animals.get(position).getStatus());
        holder.imageView.setImageResource(animals.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}
