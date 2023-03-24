package ru.mirea.playedu.view.adapter;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.playedu.R;

public class PowerAdapter extends RecyclerView.Adapter<PowerAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String name);
    }

    private String[] powers;
    private OnItemClickListener onItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTxt;
        private ImageView powerImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_txt);
            powerImg = itemView.findViewById(R.id.power_img);
        }

        public void bind(String powerName, OnItemClickListener listener) {
            nameTxt.setText(powerName);

            itemView.setOnClickListener(view -> {
                listener.onItemClick(powerName);
            });
        }



    }

    public PowerAdapter(String[] powers, OnItemClickListener onItemClickListener) {
        this.powers = powers;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PowerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.view_power_item, parent, false);
        return new PowerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(powers[position], onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return powers.length;
    }


}

