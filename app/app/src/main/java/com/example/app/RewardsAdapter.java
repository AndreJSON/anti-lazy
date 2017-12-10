package com.example.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andre on 2017-12-10.
 */

public class RewardsAdapter extends ArrayAdapter<String> {

    public RewardsAdapter(@NonNull Context context, String[] list) {
        super(context, R.layout.reward_row, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rewardView = inflater.inflate(R.layout.reward_row, parent, false);

        String rewardItem = getItem(position);
        TextView rewardText = (TextView) rewardView.findViewById(R.id.row_text);
        rewardText.setText(rewardItem);

        return rewardView;
    }
}
