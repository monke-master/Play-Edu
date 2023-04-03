package ru.mirea.playedu.view_model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import ru.mirea.playedu.BR;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.model.Category;
import ru.mirea.playedu.model.UserTask;

public class QuestsViewModel extends BaseObservable {
    // Объявление репозитория для пользовательских заданий
    UserTaskRepository repository;
    // Объявление модели пользовательского задания
    UserTask mTask;

    // Объявление всплывающего сообщения
    String toastMassage;

    @Bindable
    public String getTitle() {
        return mTask.getLabel();
    }

    public void setTitle(String title) {
        mTask.setLabel(title);
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getCategory() {
        return mTask.getCategory().getTitle();
    }

    public void setCategory(String category) {
        mTask.setCategory(new Category(0, category));
        notifyPropertyChanged(BR.category);
    }

    @Bindable
    public int getCoinsReward() {
        return mTask.getCoinsReward();
    }

    public void setCoinsReward(int coinsReward) {
        mTask.setCoinsReward(coinsReward);
        notifyPropertyChanged(BR.coinsReward);
    }

    @Bindable
    public int getColor() {
        return mTask.getColor();
    }

    public void setColor(int color) {
        mTask.setColor(color);
        notifyPropertyChanged(BR.color);
    }

    public QuestsViewModel() {
        mTask = new UserTask();
    }

    public void onAddTaskButtonClicked() {

    }

}
