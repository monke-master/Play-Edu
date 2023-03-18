package ru.mirea.playedu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

// Адаптер для отображения юзерских задач
public class UserTaskAdapter extends RecyclerView.Adapter<UserTaskAdapter.ViewHolder> {

    // Список юзерских задач, пока в формате словаря, потом будет объекты модельки
    private ArrayList<Map<String, Object>> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton colorShape;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            colorShape = itemView.findViewById(R.id.add_task_btn);
        }


        public ImageButton getColorShape() {
            return colorShape;
        }
    }

    @NonNull
    @Override
    public UserTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserTaskAdapter.ViewHolder holder, int position) {

    }


    // Возвращает количество задач
    @Override
    public int getItemCount() {
        return data.size();
    }


}
