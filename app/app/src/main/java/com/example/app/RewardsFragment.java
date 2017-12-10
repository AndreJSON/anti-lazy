package com.example.app;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment {

    OnRewardsRequest dataPasser;
    ArrayList<Reward> rewards;

    public RewardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnRewardsRequest) context;
        rewards = dataPasser.onRewardsRequest();
        Log.i("LOG", rewards.get(0).getId());
    }

    public interface OnRewardsRequest{
        public ArrayList<Reward> onRewardsRequest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);
        String[] stuff = {"apple", "banana", "pear"};
        ListView listView = (ListView) view.findViewById(R.id.list);
        ListAdapter listAdapter = new RewardsAdapter(listView.getContext(), stuff);
        listView.setAdapter(listAdapter);
        listView.setDivider(null);
        return view;
    }
}
