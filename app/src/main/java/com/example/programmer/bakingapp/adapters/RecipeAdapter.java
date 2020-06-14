package com.example.programmer.bakingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.bakingapp.R;
import com.example.programmer.bakingapp.models.RecipeItem;
import com.example.programmer.bakingapp.util.AllNeeds;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.VHReciped> {
    private static List<Integer> integerList;
    private static SetOnItemClick setOnItemClick;
    private List<RecipeItem> recipeItemList;

    public RecipeAdapter(SetOnItemClick setOnItemClick) {
        RecipeAdapter.setOnItemClick = setOnItemClick;
        integerList = AllNeeds.getListImagesRecpies();
    }

    @NonNull
    @Override
    public VHReciped onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VHReciped(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false), parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull VHReciped holder, int position) {
        holder.bind(recipeItemList.get(position));

    }

    public void setList(List<RecipeItem> list) {
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
        private ImageView imageView;
        private TextView txt_name;
        private Context context;

        public VHReciped(@NonNull View itemView, Context context) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_recipe_item);
            txt_name = itemView.findViewById(R.id.txt_name_recipe_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnItemClick.onClick(getAdapterPosition());
                }
            });

            this.context = context;
        }

        public void bind(RecipeItem item) {
            if (item.getName().isEmpty() || item.getName().equals("")) {
                txt_name.setText("no name");
            } else {
                txt_name.setText(item.getName() + "");
            }

            if (item.getImage().isEmpty()|| item.getImage()==null)
            {
                Picasso.with(context).load(integerList.get(getAdapterPosition())).placeholder(R.drawable.image_not_available).into(imageView);

            }
            else
            {
                Picasso.with(context).load(item.getImage()).placeholder(R.drawable.image_not_available).into(imageView);

            }


        }
    }
}
