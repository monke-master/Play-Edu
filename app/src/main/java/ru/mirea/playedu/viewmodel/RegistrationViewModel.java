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
    private MutableLiveData<String> repeatPassword;
    private MutableLiveData<String> group;
    private MutableLiveData<String> userIcon;
    private MutableLiveData<String> login;
    private String toastMessage;
    private SignUpUseCase signUpUseCase;


    public RegistrationViewModel(SignUpUseCase signUpUseCase) {
        userLiveData = new MutableLiveData<>();
        phone = new MutableLiveData<>();
        password = new MutableLiveData<>();
        group = new MutableLiveData<>();
        userIcon = new MutableLiveData<>();
        login = new MutableLiveData<>();
        repeatPassword = new MutableLiveData<>();
        this.signUpUseCase = signUpUseCase;
    }

    public int signUpWithPhone() {
        User user = new User(login.getValue(), password.getValue(), phone.getValue(),
                group.getValue(), userIcon.getValue());
        Response useCaseResponse = signUpUseCase.execute(user);
        int code = useCaseResponse.getCode();
        if (code != 200)
            toastMessage = useCaseResponse.getBody();
        return code;
    }

    public void chooseHero(String icon) {
        userIcon.setValue(icon);
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

    public MutableLiveData<String> getLogin() {
        return login;
    }

    public void setLogin(MutableLiveData<String> login) {
        this.login = login;
    }

    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
    }

    public MutableLiveData<String> getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(MutableLiveData<String> repeatPassword) {
        this.repeatPassword = repeatPassword;
    }


}
