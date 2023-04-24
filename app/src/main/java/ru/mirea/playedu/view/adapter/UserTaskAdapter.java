package ru.mirea.playedu.view.adapter;

import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import ru.mirea.playedu.Constants;
import ru.mirea.playedu.R;
import ru.mirea.playedu.model.UserTask;

// Адаптер для отображения юзерских задач
public class UserTaskAdapter extends RecyclerView.Adapter<UserTaskAdapter.ViewHolder> {

    public interface TaskItemListener {
        void onComplete(UserTask userTask);
        void onDelete(UserTask userTask);
    }

    // Список юзерских задач,
    private ArrayList<UserTask> tasks;
    // Константы представления
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_HEADER = 1;
    private TaskItemListener taskItemListener;


    public UserTaskAdapter(ArrayList<UserTask> tasks, TaskItemListener taskItemListener) {
        this.tasks = tasks;
        this.taskItemListener = taskItemListener;
    }

    @NonNull
    @Override
    public UserTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_NORMAL) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_task, parent, false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_task_header, parent, false);
        }

        return new UserTaskAdapter.ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTaskAdapter.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_NORMAL) {
            holder.bind(tasks.get(position), VIEW_TYPE_NORMAL, taskItemListener);
        }
        else {
            holder.bind(tasks.get(position), VIEW_TYPE_HEADER, taskItemListener);
        }

    }


    // Возвращает количество задач
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return VIEW_TYPE_HEADER;
        }
        else if (!Objects.equals(tasks.get(position).getCategory().getTitle(), tasks.get(position - 1).getCategory().getTitle())) {
            return VIEW_TYPE_HEADER;
        }
        else {
            return VIEW_TYPE_NORMAL;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View colorShape;
        private TextView labelTxt;
        private TextView deadlineTxt;
        private TextView rewardTxt;
        private CheckBox completeBox;
        private TextView header;


        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if (viewType == VIEW_TYPE_HEADER) {
                header = itemView.findViewById(R.id.header);
            }
            colorShape = itemView.findViewById(R.id.category_layout);
            labelTxt = itemView.findViewById(R.id.label_txt);
            rewardTxt = itemView.findViewById(R.id.reward_txt);
            deadlineTxt = itemView.findViewById(R.id.deadline_txt);
            completeBox = itemView.findViewById(R.id.complete_box);
        }

        public void bind(UserTask task, int viewType, TaskItemListener listener) {
            if (viewType == VIEW_TYPE_HEADER) {
                header.setText(task.getCategory().getTitle());
            }
            colorShape.setBackgroundTintList(ColorStateList.valueOf(task.getColor()));
            labelTxt.setText(task.getLabel());
            rewardTxt.setText(Integer.toString(task.getCoinsReward()));
            deadlineTxt.setText(Constants.getDeadlineString(task.getDeadlineDate()));

            completeBox.setOnClickListener(view -> {
                completeBox.setChecked(false);
                listener.onComplete(task);
            });
        }


    }


}
