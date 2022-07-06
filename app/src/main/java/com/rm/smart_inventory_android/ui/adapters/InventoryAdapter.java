package com.rm.smart_inventory_android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.ClickListener;
import com.rm.smart_inventory_android.io.models.inventory.InventoryData;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {

    private Context context;
    private List<InventoryData> inventoryDataList;
    private ClickListener listener;

    public InventoryAdapter(Context context, ClickListener listener){
        this.context = context;
        this.listener = listener;
        inventoryDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryData inventoryData = inventoryDataList.get(position);
        holder.sku.setText(inventoryData.getSku());
        holder.skuName.setText(inventoryData.getSkuName());
        holder.theoretical.setText(String.valueOf(inventoryData.getTheoretical()));
        holder.physical.setText(String.valueOf(inventoryData.getPhysical()));
        holder.difference.setText(String.valueOf(inventoryData.getDifference()));
    }

    @Override
    public int getItemCount() {
        return inventoryDataList.size();
    }

    public void addList(ArrayList<InventoryData> list){
        inventoryDataList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView sku;
        private TextView skuName;
        private TextView theoretical;
        private TextView physical;
        private TextView difference;
        private ClickListener listener;

        public ViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);

            sku = itemView.findViewById(R.id.txt_sku);
            skuName = itemView.findViewById(R.id.txt_sku_name);
            theoretical = itemView.findViewById(R.id.txt_theoretical);
            physical = itemView.findViewById(R.id.txt_physical);
            difference = itemView.findViewById(R.id.txt_difference);

            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(getAdapterPosition());
        }
    }
}
