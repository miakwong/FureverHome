package com.example.fureverhome.ui.pets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.ui.pets.PetDetailsActivity;
import com.example.fureverhome.model.Pet;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private List<Pet> petList;

    public PetAdapter(List<Pet> petList) {
        this.petList = petList;
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the pet card layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_card_item, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PetViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.petName.setText(pet.getName());
        holder.petImage.setImageResource(pet.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            // Handle pet click action, e.g., navigate to pet details
            Context context = v.getContext();
            Intent intent = new Intent(context, PetDetailsActivity.class);
            intent.putExtra("petId", pet.getId());

            intent.putExtra("galleryResIds", pet.getGalleryResIds());

            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return petList.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        TextView petName;
        ImageView petImage;

        public PetViewHolder(View itemView) {
            super(itemView);
            petName = itemView.findViewById(R.id.petName);
            petImage = itemView.findViewById(R.id.petImage);
        }
    }
}
