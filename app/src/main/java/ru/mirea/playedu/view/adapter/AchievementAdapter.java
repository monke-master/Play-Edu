package ru.mirea.playedu.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.playedu.R;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String name);
    }

    private String[] achievements;
    private OnItemClickListener clickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTxt;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_txt);
            image = itemView.findViewById(R.id.power_img);
        }

        public void bind(String name, OnItemClickListener listener) {

            nameTxt.setText(name);
            itemView.setOnClickListener(view -> listener.onItemClick(name));
        }

    }

    public AchievementAdapter(String[] achievements, OnItemClickListener clickListener) {
        this.achievements = achievements;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AchievementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.view_power_item, parent, false);
        return new AchievementAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(achievements[position], clickListener);
    }


    @Override
    public int getItemCount() {
        return achievements.length;
    }


}

