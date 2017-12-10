package com.example.app;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment {

    OnRewardsRequest dataPasser;

    public RewardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnRewardsRequest) context;
        ArrayList<Reward> rewards = dataPasser.onRewardsRequest();
        Log.i("LOG", rewards.get(0).getId());
    }

    public interface OnRewardsRequest{
        public ArrayList<Reward> onRewardsRequest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rewards, container, false);
    }
}
