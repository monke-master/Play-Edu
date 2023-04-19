package ru.mirea.playedu.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.mirea.playedu.databinding.ViewPowerItemBinding;
import ru.mirea.playedu.model.Power;

// Адаптер для отображения списка сил
public class PowerAdapter extends RecyclerView.Adapter<PowerAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Power power, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Power power);
    }

    // Список сил
    private ArrayList<Power> powers;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public PowerAdapter(ArrayList<Power> powers, OnItemClickListener onItemClickListener) {
        this.powers = powers;
        this.onItemClickListener = onItemClickListener;
    }

    public PowerAdapter(ArrayList<Power> powers, OnItemClickListener onItemClickListener, OnItemLongClickListener onItemLongClickListener) {
        this.powers = powers;
        this.onItemClickListener = onItemClickListener;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public PowerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewPowerItemBinding binding = ViewPowerItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new PowerAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(powers.get(position), onItemClickListener, onItemLongClickListener, position);
    }

    @Override
    public int getItemCount() {
        return powers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewPowerItemBinding binding;

        public ViewHolder(ViewPowerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Power power, OnItemClickListener listener, OnItemLongClickListener longClickListener, int position) {
            binding.setPower(power
            );
            binding.powerImg.setImageResource(power.getIcon());
            itemView.setOnClickListener(view -> {
                listener.onItemClick(power, position);
            });
            if (longClickListener != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        longClickListener.onItemLongClick(power);
                        return false;
                    }
                });
            }
        }
    }


}

