package com.example.programmer.bakingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.bakingapp.R;
import com.example.programmer.bakingapp.models.StepsItem;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.VHReciped> {
    private static SetOnItemClick setOnItemClick;
    private List<StepsItem> recipeItemList;

    public StepAdapter(SetOnItemClick setOnItemClick) {
        StepAdapter.setOnItemClick = setOnItemClick;

    }

    @NonNull
    @Override
    public VHReciped onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VHReciped(LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false), parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull VHReciped holder, int position) {
        holder.bind(recipeItemList.get(position));

    }

    public void setList(List<StepsItem> list) {
        this.recipeItemList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }

    public interface SetOnItemClick {
        void onClick(int pos);
    }

    static class VHReciped extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private Context context;

        public VHReciped(@NonNull View itemView, Context context) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_step);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnItemClick.onClick(getAdapterPosition());
                }
            });

            this.context = context;
        }

        public void bind(StepsItem item) {
            if (item.getShortDescription().isEmpty() || item.getShortDescription().equals("")) {
                txt_name.setText("no data");
            } else {
                txt_name.setText(item.getShortDescription() + "");
            }


        }
    }
}
