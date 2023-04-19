package ru.mirea.playedu.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.mirea.playedu.databinding.ViewPowerItemBinding;
import ru.mirea.playedu.model.Power;

// Адаптер для отображения списка сил
public class PowerAdapter extends RecyclerView.Adapter<PowerAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Power power);
    }

    // Список сил
    private ArrayList<Power> powers;
    private OnItemClickListener onItemClickListener;

    public PowerAdapter(ArrayList<Power> powers, OnItemClickListener onItemClickListener) {
        this.powers = powers;
        this.onItemClickListener = onItemClickListener;
    }

    public void setPowersList(ArrayList<Power> powers) {
        this.powers = powers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PowerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewPowerItemBinding binding = ViewPowerItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new PowerAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(powers.get(position), onItemClickListener);
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

        public void bind(Power power, OnItemClickListener listener) {
            binding.setPower(power
            );

            itemView.setOnClickListener(view -> {
                listener.onItemClick(power);
            });
        }
    }


}

