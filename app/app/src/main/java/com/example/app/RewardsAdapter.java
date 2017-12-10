package com.example.app;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andre on 2017-12-10.
 */

public class RewardsAdapter extends ArrayAdapter<Reward> {

    public RewardsAdapter(@NonNull Context context, ArrayList<Reward> list) {
        super(context, R.layout.reward_row, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rewardView = inflater.inflate(R.layout.reward_row, parent, false);

        Reward rewardItem = getItem(position);
        TextView rewardText = (TextView) rewardView.findViewById(R.id.row_text);
        rewardText.setText(rewardItem.getDescription());
        ProgressBar rewardProgress = (ProgressBar) rewardView.findViewById(R.id.row_progress);
        rewardProgress.setMax(rewardItem.getPoints());
        rewardProgress.setProgress(27);
        Button rewardButton = (Button) rewardView.findViewById(R.id.row_button);
        if (27 >= rewardItem.getPoints()) {
            rewardButton.setBackgroundColor(Color.parseColor("#0e9865"));
        }

        return rewardView;
    }
}
