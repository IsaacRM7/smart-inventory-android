package com.rm.smart_inventory_android.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.models.center.WarehouseData;
import com.rm.smart_inventory_android.ui.activities.Inventory;

import java.util.List;

public class CenterChildrenAdapter extends RecyclerView.Adapter<CenterChildrenAdapter.ViewHolder> {

    private List<WarehouseData> childrenList;
    private Context context;

    public CenterChildrenAdapter(List<WarehouseData> childrenList, Context context){
        this.childrenList = childrenList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_center_children, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WarehouseData warehouseData = childrenList.get(position);
        holder.warehouseId.setText(String.valueOf(warehouseData.getId()));
        holder.warehouseName.setText(warehouseData.getName());
        holder.code.setText(warehouseData.getCode());
        holder.linearLayout.setOnClickListener(v -> {
            Preferences.save((Activity) context, "warehouse_id", holder.warehouseId.getText().toString());
            Intent intent = new Intent(context, Inventory.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView warehouseId;
        private TextView warehouseName;
        private TextView code;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.children_linearlayout);
            warehouseId = itemView.findViewById(R.id.txt_warehouse_id);
            warehouseName = itemView.findViewById(R.id.txt_warehouse_name);
            code = itemView.findViewById(R.id.txt_stock_code);
        }
    }
}
