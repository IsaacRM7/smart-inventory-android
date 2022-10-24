package com.rm.smart_inventory_android.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.ClickListener;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.db.inventroy.InventoryDataBase;
import com.rm.smart_inventory_android.io.models.inventory.InventoryData;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<InventoryData> inventoryDataList;
    private InventoryDataBase db;
    private final ClickListener listener;

    public InventoryAdapter(Context context, ClickListener listener){
        db = InventoryDataBase.getInstance(context);
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryData inventoryData = inventoryDataList.get(position);

        if(inventoryData.getTheoretical() > inventoryData.getPhysical()){
            holder.circleImage.setColorFilter(Color.parseColor("#FF0000"));
        }
        else if(inventoryData.getTheoretical() < inventoryData.getPhysical()){
            holder.circleImage.setColorFilter(Color.parseColor("#0000FF"));
        }
        else{
            holder.circleImage.setColorFilter(Color.parseColor("#00FF00"));
        }
        if(Preferences.get(context, "user_type").equals("1")){
            double difference = inventoryData.getTheoretical() - inventoryData.getPhysical();
            holder.sku.setText(inventoryData.getSku());
            holder.skuName.setText(inventoryData.getSkuName());
            holder.theoretical.setText(String.valueOf(inventoryData.getTheoretical()));
            holder.physical.setText(String.valueOf(inventoryData.getPhysical()));
            holder.difference.setText(String.valueOf(difference));
        }
        else{
            holder.sku.setText(inventoryData.getSku());
            holder.skuName.setText(inventoryData.getSkuName());
            holder.theoretical.setVisibility(View.INVISIBLE);
            holder.physical.setVisibility(View.INVISIBLE);
            holder.difference.setVisibility(View.INVISIBLE);
            holder.vTheoretical.setVisibility(View.INVISIBLE);
            holder.vPhysical.setVisibility(View.INVISIBLE);
            holder.vState.setVisibility(View.INVISIBLE);
            holder.vLast.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return inventoryDataList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addList(ArrayList<InventoryData> list){
        this.inventoryDataList = list;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {

        return exampleFilter;
    }

    public List<InventoryData> getFilteredList() {
        return inventoryDataList;
    }

    private final Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            return null;
        }

        //Solo este método hace el fitro de los sku
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            inventoryDataList.clear();
            inventoryDataList.addAll(db.inventoryDao().findBySku(charSequence.toString()));
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView sku;
        private TextView skuName;
        private TextView theoretical;
        private TextView physical;
        private TextView difference;
        private ImageView circleImage;
        private View vTheoretical;
        private View vPhysical;
        private View vState;
        private View vLast;
        private ClickListener listener;

        public ViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);

            sku = itemView.findViewById(R.id.txt_sku);
            skuName = itemView.findViewById(R.id.txt_sku_name);
            theoretical = itemView.findViewById(R.id.txt_theoretical);
            physical = itemView.findViewById(R.id.txt_physical);
            difference = itemView.findViewById(R.id.txt_difference);
            circleImage = itemView.findViewById(R.id.image_circle);
            vTheoretical = itemView.findViewById(R.id.view_theoretical_separator);
            vPhysical = itemView.findViewById(R.id.view_physical_separator);
            vState = itemView.findViewById(R.id.view_state_separator);
            vLast = itemView.findViewById(R.id.view_last_separator);

            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(getAdapterPosition());
        }
    }
}
