package ru.mirea.playedu.viewmodel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.playedu.model.User;
import ru.mirea.playedu.usecases.GetUserUseCase;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<User> user;
    private GetUserUseCase getUserUseCase;

    public ProfileViewModel(GetUserUseCase getUserUseCase) {
        user = new MutableLiveData<>();
        user.setValue(getUserUseCase.execute());
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void setUser(MutableLiveData<User> user) {
        this.user = user;
    }

}
