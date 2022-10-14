package com.rm.smart_inventory_android.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.models.count.RecountData;

import java.util.List;

public class CountedAdapter extends RecyclerView.Adapter<CountedAdapter.CountedViewHolder> {

    private List<RecountData> countedDataList;
    private Context context;

    public CountedAdapter(Context context, List<RecountData> countedDataList){
        this.context = context;
        this.countedDataList = countedDataList;
    }

    @NonNull
    @Override
    public CountedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_counted, parent, false);
        return new CountedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountedViewHolder holder, int position) {
        RecountData recountData = countedDataList.get(position);
        holder.id = recountData.getId();
        holder.txtCountedPlasticPlatforms.setText(recountData.getPlasticPlatforms());
        holder.txtCountedWoodenPlatforms.setText(recountData.getWoodenPlatforms());
        holder.txtCountedBoxes.setText(recountData.getBoxes());
        holder.txtCountedUnits.setText(recountData.getUnits());
        holder.txtCountedState.setText(String.valueOf(recountData.getStatus()));
        holder.txtCountedUser.setText(recountData.getUser());
        holder.txtCountedDate.setText(recountData.getDate());
        holder.countedLinearLayout.setOnClickListener(v -> {
            Preferences.save((Activity) context, "counted_id", String.valueOf(holder.id));
        });
    }

    @Override
    public int getItemCount() {
        return countedDataList.size();
    }

    public class CountedViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCountedPlasticPlatforms;
        private TextView txtCountedWoodenPlatforms;
        private TextView txtCountedBoxes;
        private TextView txtCountedUnits;
        private TextView txtCountedState;
        private TextView txtCountedUser;
        private TextView txtCountedDate;
        private int id;
        private LinearLayout countedLinearLayout;

        public CountedViewHolder(@NonNull View itemView) {
            super(itemView);

            countedLinearLayout = itemView.findViewById(R.id.counted_linear_layout);
            txtCountedPlasticPlatforms = itemView.findViewById(R.id.txt_counted_plastic_platforms);
            txtCountedWoodenPlatforms = itemView.findViewById(R.id.txt_counted_wooden_platforms);
            txtCountedBoxes = itemView.findViewById(R.id.txt_counted_boxes);
            txtCountedUnits = itemView.findViewById(R.id.txt_counted_units);
            txtCountedState = itemView.findViewById(R.id.txt_counted_state);
            txtCountedUser = itemView.findViewById(R.id.txt_counted_user);
            txtCountedDate = itemView.findViewById(R.id.txt_counted_date);

        }
    }
}
