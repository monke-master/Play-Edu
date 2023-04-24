package ru.mirea.playedu.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import ru.mirea.playedu.DateHelper;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.ViewDateBinding;

// Адаптер для отображения списка дат
public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Calendar date);
    }

    private ArrayList<Calendar> dateList;
    private OnItemClickListener onItemClickListener;
    private ViewHolder pickedDateVH;
    private Calendar pickedDate;

    public DateAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        dateList = new ArrayList<>();
        pickedDateVH = null;
    }

    public void addItem(Calendar date) {
        dateList.add(date);
        notifyItemChanged(dateList.size() - 1);
    }

    public void setDateList(ArrayList<Calendar> dateList) {
        this.dateList.addAll(dateList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDateBinding binding = ViewDateBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dateList.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public Calendar getPickedDate() {
        return pickedDateVH.getDate();
    }

    public void setPickedDate(Calendar date) {
        pickedDate = date;
    }

    // View-Holder для элемента даты
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDateBinding binding;
        private Calendar date;


        public ViewHolder(ViewDateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Calendar date, OnItemClickListener listener) {
            binding.setDate(date);
            this.date = date;
            setBackgroundColor(getDefaultColor(date));
            if (pickedDate == date)
                pickedDateVH = this;
            // Обработка нажатия
            itemView.setOnClickListener(view -> {
                // Если нажатие произошло на виджет другой даты, то
                // Возвращение предыдущей выбранной дате ее исходного цвета
                if (pickedDateVH != null && pickedDateVH != this) {
                    pickedDateVH.setBackgroundColor(getDefaultColor(pickedDateVH.getDate()));
                }
                // Установка новой выбранной даты
                setBackgroundColor(itemView.getResources().getColor(R.color.purple_100));
                pickedDateVH = this;
                listener.onItemClick(date);
            });

        }

        private void setBackgroundColor(int color) {
            binding.layout.getBackground().setTint(color);
        }

        private int getDefaultColor(Calendar date) {
            if (DateHelper.equalDate(date, pickedDate))
                itemView.getResources().getColor(R.color.purple_100);
            if (DateHelper.isToday(date))
                return itemView.getResources().getColor(R.color.purple_200);
            return itemView.getResources().getColor(R.color.purple_400);
        }

        public Calendar getDate() {
            return date;
        }

        public void setDate(Calendar date) {
            this.date = date;
        }
    }


}
