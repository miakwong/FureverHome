package com.example.fureverhome.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.ui.shelter_management.Animal;
import com.example.fureverhome.ui.shelter_management.MyViewHolder;

import java.util.List;
public class HomeAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final Context context;
    private List<Animal> animals;

    public HomeAdapter(Context context, List<Animal> animals) {
        this.context = context;
        this.animals = animals;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.animal_view, parent, false);
        return new MyViewHolder(view);
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

        // Hide the update and view buttons in the Home fragment
        holder.updateButton.setVisibility(View.GONE);
        holder.viewButton.setVisibility(View.GONE);

        if (!animal.getImageList().isEmpty()) {
            holder.imageView.setImageResource(animal.getImageList().get(0));
        } else {
            holder.imageView.setImageResource(R.drawable.ic_imageplaceholder);
        }
    }

    @Override
    public int getItemCount() {
        return animals != null ? animals.size() : 0;
    }
}
