package ru.mirea.playedu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.data.repository.UserStatsRepository;
import ru.mirea.playedu.data.storage.cache.UserCacheStorage;
import ru.mirea.playedu.model.Response;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.usecases.SignUpUseCase;

public class RegistrationViewModel extends ViewModel {

    private MutableLiveData<User> userLiveData;
    private MutableLiveData<String> phone;
    private MutableLiveData<String> password;
    private MutableLiveData<String> group;
    private MutableLiveData<String> userIcon;
    private MutableLiveData<String> login;
    private MutableLiveData<String> toastMessage;
    private SignUpUseCase signUpUseCase;

    public RegistrationViewModel(UserRepository userRepository, UserStatsRepository userStatsRepository) {
        userLiveData = new MutableLiveData<>();
        phone = new MutableLiveData<>();
        password = new MutableLiveData<>();
        group = new MutableLiveData<>();
        userIcon = new MutableLiveData<>();
        login = new MutableLiveData<>();
        toastMessage = new MutableLiveData<>();
        signUpUseCase = new SignUpUseCase(userRepository, userStatsRepository);
    }

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public void setUserLiveData(MutableLiveData<User> userLiveData) {
        this.userLiveData = userLiveData;
    }

    public MutableLiveData<String> getPhone() {
        return phone;
    }

    public void setPhone(MutableLiveData<String> phone) {
        this.phone = phone;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }

    public MutableLiveData<String> getGroup() {
        return group;
    }

    public void setGroup(MutableLiveData<String> group) {
        this.group = group;
    }

    public MutableLiveData<String> getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(MutableLiveData<String> userIcon) {
        this.userIcon = userIcon;
    }

    public void signUpWithPhone() {
        User user = new User(login.getValue(), password.getValue(), phone.getValue(),
                group.getValue(), userIcon.getValue());
        Response useCaseResponse = signUpUseCase.execute(user);
        if (useCaseResponse.getCode() != 200)
            toastMessage.setValue(useCaseResponse.getBody());
    }
}
