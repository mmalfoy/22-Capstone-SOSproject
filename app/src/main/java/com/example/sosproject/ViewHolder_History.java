package com.example.sosproject;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder_History extends RecyclerView.ViewHolder {

    protected TextView text_day;
    protected TextView text_pay;
    protected TextView text_where;
    protected TextView text_sum;
    protected TextView text_time;

    ViewHolder_History(Context context, View itemView) {
        super(itemView);

        text_day = itemView.findViewById(R.id.text_day);
        text_pay = itemView.findViewById(R.id.text_pay);
        text_where = itemView.findViewById(R.id.text_where);
        text_time = itemView.findViewById(R.id.text_time);
        text_sum = itemView.findViewById(R.id.text_sum);
    }

}
