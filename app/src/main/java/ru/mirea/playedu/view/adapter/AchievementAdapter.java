package ru.mirea.playedu.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.playedu.R;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder> {

    private String[] achievements;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTxt;
        private ImageView powerImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_txt);
            powerImg = itemView.findViewById(R.id.power_img);
        }


        public TextView getNameTxt() {
            return nameTxt;
        }

        public ImageView getPowerImg() {
            return powerImg;
        }

    }

    public AchievementAdapter(String[] achievements) {
        this.achievements = achievements;
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
        holder.getNameTxt().setText(achievements[position]);
    }


    @Override
    public int getItemCount() {
        return achievements.length;
    }


}

