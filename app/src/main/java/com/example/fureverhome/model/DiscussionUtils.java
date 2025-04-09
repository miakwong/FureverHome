package com.example.fureverhome.model;

import com.example.fureverhome.R;

import java.util.ArrayList;
import java.util.List;

public class DiscussionUtils {

    private static final List<Discussion> discussionList1 = new ArrayList<>();

    //For Discussion Section
    public static List<Discussion> loadDiscussions() {
        List<Discussion> discussionList = new ArrayList<>();

        //"Volunteer", "Workshop", "Networking", "Seminar", "Others"
        discussionList.add(new Discussion(R.drawable.ic_pet, "All you need to know about Vaccination", "2024/04/01", "1", "Hello everyone, my name is Doctor, today...", "Seminar"));

        List<Discussion> mergedList = new ArrayList<>();
        mergedList.addAll(discussionList);
        mergedList.addAll(discussionList1);
        return mergedList;
    }

    public static void addDiscussion(Discussion discussion) {discussionList1.add(discussion);
    }

    public static Discussion getDiscussionById(List<Discussion> discussionList, String discussionId) {
        for (Discussion discussion : discussionList) {
            if (discussion.getId().equals(discussionId)) {
                return discussion;
            }
        }
        return null;
    }
}
