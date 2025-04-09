package com.example.fureverhome.ui.community;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Discussion;


import java.util.List;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder> {

    private final List<Discussion> discussionList;
    private final Context context;

    public DiscussionAdapter(List<Discussion> discussionList, Context context) {
        this.discussionList = discussionList;
        this.context = context;
    }

    @NonNull
    @Override
    public DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discussion_item, parent, false);
        return new DiscussionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position) {
        Discussion discussion = discussionList.get(position);
        holder.title.setText(discussion.getTitle());
        holder.time.setText(discussion.getPostDate());
        holder.icon.setImageResource(discussion.getImageId());

        //Click arrow to details page
        holder.arrow.setOnClickListener(v -> {
            Intent intent = new Intent(context, DiscussionDetailsActivity.class);
            intent.putExtra("discussion", discussion);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return discussionList.size();
    }

    static class DiscussionViewHolder extends RecyclerView.ViewHolder {
        TextView title, time;
        ImageView icon, arrow;

        public DiscussionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.discussionTitle);
            time = itemView.findViewById(R.id.discussionTime);
            icon = itemView.findViewById(R.id.discussionImage);
            arrow = itemView.findViewById(R.id.discussionArrow);
        }
    }

    //Method to update the list
    public void updateDiscussionList(List<Discussion> newDiscussionList) {
        discussionList.clear();
        discussionList.addAll(newDiscussionList);
        notifyDataSetChanged(); //Notify the adapter to refresh the data
    }
}
