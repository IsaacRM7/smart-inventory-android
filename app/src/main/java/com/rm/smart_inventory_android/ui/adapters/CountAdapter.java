package com.rm.smart_inventory_android.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.ClickListener;
import com.rm.smart_inventory_android.io.models.count.CountData;

import java.util.ArrayList;
import java.util.List;

public class CountAdapter extends RecyclerView.Adapter<CountAdapter.ViewHolder> {

    private List<CountData> countDataList;
    private Context context;
    private ClickListener listener;

    public CountAdapter(Context context, ClickListener listener){
        this.context = context;
        this.listener = listener;
        countDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_count, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CountData countData = countDataList.get(position);
        holder.measure.setText(countData.getMeasure());
        holder.amount.setText(String.valueOf(countData.getAmount()));
    }

    @Override
    public int getItemCount() {
        return countDataList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addList(ArrayList<CountData> list){
        countDataList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView measure;
        private TextView amount;
        private ClickListener listener;

        public ViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);

            measure = itemView.findViewById(R.id.txt_measure);
            amount = itemView.findViewById(R.id.txt_amount);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(getAdapterPosition());
        }
    }
}
