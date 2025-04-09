package com.example.fureverhome.ui.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Discussion;
import com.example.fureverhome.model.DiscussionUtils;

import java.util.ArrayList;
import java.util.List;

public class DiscussionsFragment extends Fragment  {
    private RecyclerView recyclerView1, recyclerView2;

    private DiscussionAdapter discussionAdapter1, discussionAdapter2;
    private List<Discussion> discussionList = new ArrayList<>();
    private List<Discussion> allDiscussionList = new ArrayList<>(); // Store the original list of discussions

    private boolean isSyncingScroll = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_community, container, false);

        // Set discussion generate button
        ImageButton generateButton = root.findViewById(R.id.generateButton);
        generateButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), DiscussionGenerateActivity.class); // 改成你的页面类
            startActivity(intent);
        });



        // Set RecyclerView
        recyclerView1 = root.findViewById(R.id.discussionTasksRecycler1);
        recyclerView2 = root.findViewById(R.id.discussionTasksRecycler2);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));


        // Use DiscussionUtils to load the discussions
        discussionList = DiscussionUtils.loadDiscussions();

        // Save the original list of discussions
        allDiscussionList.addAll(discussionList);

        List<Discussion> list1 = new ArrayList<>();
        List<Discussion> list2 = new ArrayList<>();

        for (int i = 0; i < discussionList.size(); i++) {
            if (i % 2 == 0) {
                list1.add(discussionList.get(i));
            } else {
                list2.add(discussionList.get(i));
            }
        }

        discussionAdapter1 = new DiscussionAdapter(list1, requireContext());
        discussionAdapter2 = new DiscussionAdapter(list2, requireContext());


        // 设置 layoutManager 为横向
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);

        recyclerView1.setAdapter(discussionAdapter1);
        recyclerView2.setAdapter(discussionAdapter2);

        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView rv, int dx, int dy) {
                if (!isSyncingScroll) {
                    isSyncingScroll = true;
                    recyclerView2.scrollBy(dx, dy);
                    isSyncingScroll = false;
                }
            }
        });

        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView rv, int dx, int dy) {
                if (!isSyncingScroll) {
                    isSyncingScroll = true;
                    recyclerView1.scrollBy(dx, dy);
                    isSyncingScroll = false;
                }
            }
        });



        // Set SearchView for searching discussions by name
        SearchView searchView = root.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;   // Prdiscussion enter pressed
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterDiscussions(newText);  // Filter discussions when text changes
                return false;
            }
        });

        return root;
    }

    private void filterDiscussions(String query) {
        List<Discussion> matchedList = new ArrayList<>();
        String queryLowerCase = query.toLowerCase();

        for (Discussion discussion : allDiscussionList) {
            if (discussion.getTitle().toLowerCase().contains(queryLowerCase)) {
                matchedList.add(discussion);
            }
        }

        List<Discussion> filteredList1 = new ArrayList<>();
        List<Discussion> filteredList2 = new ArrayList<>();

        for (int i = 0; i < matchedList.size(); i++) {
            if (i % 2 == 0) {
                filteredList1.add(matchedList.get(i));
            } else {
                filteredList2.add(matchedList.get(i));
            }
        }

        if (discussionAdapter1 != null) {
            discussionAdapter1.updateDiscussionList(filteredList1);
        }
        if (discussionAdapter2 != null) {
            discussionAdapter2.updateDiscussionList(filteredList2);
        }
    }

}

