package com.rm.smart_inventory_android.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.models.bodega.CenterData;
import com.rm.smart_inventory_android.io.models.bodega.WarehouseData;

import java.util.ArrayList;
import java.util.List;

public class CenterAdapter extends RecyclerView.Adapter<CenterAdapter.ViewHolder> {

    private List<CenterData> centerList;
    private List<WarehouseData> list = new ArrayList<>();
    private Context context;

    public CenterAdapter(List<CenterData> centerList, Context context){
        this.centerList = centerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_center, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CenterData centerData = centerList.get(position);

        holder.centerId.setText(String.valueOf(centerData.getId()));
        holder.centerName.setText(centerData.getName());

        boolean isExpandable = centerData.isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if(isExpandable){
            holder.arrowImage.setImageResource(R.drawable.ic_arrow_up);
        }
        else{
            holder.arrowImage.setImageResource(R.drawable.ic_arrow_down);
        }

        CenterChildrenAdapter adapter = new CenterChildrenAdapter(list, context);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(adapter);
        if(centerData.getCount() == 1){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#868B8E"));
        }
        holder.linearLayout.setOnClickListener(v -> {
            centerData.setExpandable(!centerData.isExpandable());
            list = centerData.getWarehouses();
            notifyItemChanged(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return centerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        private RelativeLayout expandableLayout;
        private RecyclerView recyclerView;
        private TextView centerId;
        private TextView centerName;
        private ImageView arrowImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.main_linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            recyclerView = itemView.findViewById(R.id.children_recyclerview);
            centerId = itemView.findViewById(R.id.txt_center_id);
            centerName = itemView.findViewById(R.id.txt_center_name);
            arrowImage = itemView.findViewById(R.id.arrow_image);

        }
    }
}
