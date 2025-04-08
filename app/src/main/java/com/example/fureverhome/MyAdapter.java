package com.example.fureverhome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final Context context;
    private List<Animal> animals;

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

        // setting image
        if (!animal.getImageList().isEmpty()) {
            holder.imageView.setImageResource(animal.getImageList().get(0));
        } else {
            holder.imageView.setImageResource(R.drawable.imageplaceholder); // fallback image if list is empty
        }


        // View button
        holder.viewButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AnimalDetailActivity.class);
            intent.putExtra("animal", animal); // Ensure Animal implements Serializable
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        // Update button
        holder.updateButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AnimalUpdateActivity.class);
            intent.putExtra("animal", animal);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animals != null ? animals.size() : 0;
    }

    // Called when filters are applied
    public void setFilteredList(List<Animal> filteredList) {
        this.animals = filteredList;
        notifyDataSetChanged();
    }
}
