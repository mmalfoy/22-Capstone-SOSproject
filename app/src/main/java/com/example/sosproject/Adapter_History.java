package com.example.sosproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

//protected TextView text_day;
//protected TextView text_pay;
//protected TextView text_where;
//protected TextView text_sum;
//protected TextView text_time;

public class Adapter_History extends RecyclerView.Adapter<ViewHolder_History> {
    private ArrayList<String> arrayList_day;
    private ArrayList<String> arrayList_pay;
    private ArrayList<String> arrayList_where;
    private ArrayList<String> arrayList_sum;
    private ArrayList<String> arrayList_time;




    public Adapter_History() {
        arrayList_day = new ArrayList<>();
        arrayList_pay = new ArrayList<>();
        arrayList_where = new ArrayList<>();
        arrayList_sum = new ArrayList<>();
        arrayList_time = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolder_History onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_list_history, parent, false);

        ViewHolder_History viewholder = new ViewHolder_History(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_History holder, int position) {
        String text = arrayList_day.get(position);
        holder.text_day.setText(text);

        text = arrayList_pay.get(position);
        holder.text_pay.setText(text);

        text = arrayList_where.get(position);
        holder.text_where.setText(text);

        text = arrayList_sum.get(position);
        holder.text_sum.setText(text);

        text = arrayList_time.get(position);
        holder.text_time.setText(text);




    }

    @Override
    public int getItemCount() {
        return arrayList_day.size();
    }

    // 데이터 입력 관련 코드
    public void setArrayData(String strData,int option) {
        switch (option){
            case 0:
                arrayList_day.add(strData);
                break;
            case 1:
                arrayList_pay.add(strData);
                break;

            case 2:
                arrayList_where.add(strData);
                break;
            case 3:
                arrayList_sum.add(strData);
                break;
            case 4:
                arrayList_time.add(strData);
                break;

        }


    }

}

