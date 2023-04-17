package ru.mirea.playedu.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.ViewAchievementItemBinding;
import ru.mirea.playedu.model.Achievement;

// Адаптер для отображения списка достижений
public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Achievement achievement);
    }

    // Список достижений
    private ArrayList<Achievement> achievements;
    private OnItemClickListener clickListener;

    public AchievementAdapter(ArrayList<Achievement> achievements, OnItemClickListener clickListener) {
        this.achievements = achievements;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AchievementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewAchievementItemBinding binding = ViewAchievementItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new AchievementAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(achievements.get(position), clickListener);
    }


    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewAchievementItemBinding binding;

        public ViewHolder(ViewAchievementItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Achievement achievement, OnItemClickListener listener) {

            binding.setAchievement(achievement);
            itemView.setOnClickListener(view -> listener.onItemClick(achievement));
        }

    }


}

