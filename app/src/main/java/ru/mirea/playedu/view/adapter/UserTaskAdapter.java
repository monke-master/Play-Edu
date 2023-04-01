package ru.mirea.playedu.view.adapter;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.mirea.playedu.R;
import ru.mirea.playedu.model.UserTask;

// Адаптер для отображения юзерских задач
public class UserTaskAdapter extends RecyclerView.Adapter<UserTaskAdapter.ViewHolder> {

    // Список юзерских задач,
    private ArrayList<UserTask> tasks;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View colorShape;
        private TextView labelTxt;
        private TextView deadlineTxt;
        private TextView rewardTxt;
        private CheckBox completeBox;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            colorShape = itemView.findViewById(R.id.category_layout);
            labelTxt = itemView.findViewById(R.id.label_txt);
            rewardTxt = itemView.findViewById(R.id.price_txt);
            deadlineTxt = itemView.findViewById(R.id.deadline_txt);
            completeBox = itemView.findViewById(R.id.complete_box);
        }

        public void bind(UserTask task) {
            colorShape.setBackgroundTintList(ColorStateList.valueOf(task.getColor()));
            labelTxt.setText(task.getLabel());
            rewardTxt.setText(task.getCoinsReward());
            // TODO сделать отображение дедлайна
        }


    }

    public UserTaskAdapter(ArrayList<UserTask> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public UserTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_task, parent, false);
        return new UserTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTaskAdapter.ViewHolder holder, int position) {
        holder.bind(tasks.get(position));
    }


    // Возвращает количество задач
    @Override
    public int getItemCount() {
        return tasks.size();
    }


}
