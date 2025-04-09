package com.example.fureverhome.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.databinding.FragmentHomeBinding;
import com.example.fureverhome.ui.shelter_management.Animal;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private HomeAdapter adapter; // Updated to use HomeAdapter
    private List<Animal> animalList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Setup RecyclerView
        recyclerView = binding.homeRecyclerView;  // Make sure this ID matches your RecyclerView in fragment_home.xml
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVerticalScrollBarEnabled(true);

        // Initialize the animal list and populate it
        animalList = new ArrayList<>();
        populateAnimals();
        adapter = new HomeAdapter(getContext(), animalList);
        recyclerView.setAdapter(adapter);

        return root;
    }

    private void populateAnimals() {
        // Add animals
        animalList.add(new Animal("Larry", "Lizard", "Chinese Water Dragon", "Green", "Large", 5, "Male", List.of(R.drawable.ic_larry), "Kelowna SPCA", "Available", "A playful and curious lizard."));
        animalList.add(new Animal("Molly", "Dog", "Golden Retriever", "Yellow", "Large", 2, "Female", List.of(R.drawable.ic_molly), "Kelowna SPCA", "Pending", "An adorable girl"));
        animalList.add(new Animal("Bentley", "Cat", "short hair mix", "Grey and White", "Medium", 7, "Male", List.of(R.drawable.ic_bentley), "Peachland SPCA", "Adopted", "A playful young man"));
        animalList.add(new Animal("Poppy", "Snake", "Ball Python", "Brown and Black", "Medium", 14, "Female", List.of(R.drawable.ic_poppy), "Vernon SPCA", "Adopted", "An adventurous spirit"));
        animalList.add(new Animal("rocky", "Dog", "Golden Retriever", "Yellow", "Large", 0, "Male", List.of(R.drawable.ic_rocky), "Vernon SPCA", "Available", "Playful and rambunctious"));
        animalList.add(new Animal("Sunny", "Horse", "Thoroughbred", "Red", "Large", 3, "Male", List.of(R.drawable.ic_sunny), "Kelowna Farm Rescue", "Available", "Calm and steady. Great for kids"));
        animalList.add(new Animal("Tux", "Cat", "short hair mix", "Black and White", "Medium", 1, "Male", List.of(R.drawable.ic_tux), "Vernon SPCA", "Pending", "Will make friends with your neighbours too"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
