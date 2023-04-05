package ru.mirea.playedu.view.adapter;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.playedu.R;
import ru.mirea.playedu.callbacks.OnSelectColorFilterCallback;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {

    private int[] colors;
    private final OnSelectColorFilterCallback callback;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View colorShape;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colorShape = itemView.findViewById(R.id.add_task_btn);
        }


        public View getColorShape() {
            return colorShape;
        }
    }

    public ColorAdapter(int[] colors, OnSelectColorFilterCallback callback) {
        this.colors = colors;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_color, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getColorShape().setBackgroundTintList(ColorStateList.valueOf(colors[position]));
        holder.getColorShape().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.execute(colors[holder.getBindingAdapterPosition()]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }
}
