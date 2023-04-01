package ru.mirea.playedu.model;

import java.net.URL;
import java.util.ArrayList;

public class User {

    private int userId;
    private String login;
    private String password;
    private String phoneNumber;
    private String group;
    private int goldenCoins;
    private int silverCoins;
    private URL userIcon;
    private ArrayList<Achievement> profileAchievements;


    public User(int userId, String login, String password, String phoneNumber,
                String group, int goldenCoins, int silverCoins, URL userIcon,
                ArrayList<Achievement> profileAchievements) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.group = group;
        this.goldenCoins = goldenCoins;
        this.silverCoins = silverCoins;
        this.userIcon = userIcon;
        this.profileAchievements = profileAchievements;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getGoldenCoins() {
        return goldenCoins;
    }

    public void setGoldenCoins(int goldenCoins) {
        this.goldenCoins = goldenCoins;
    }

    public int getSilverCoins() {
        return silverCoins;
    }

    public void setSilverCoins(int silverCoins) {
        this.silverCoins = silverCoins;
    }

    public URL getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(URL userIcon) {
        this.userIcon = userIcon;
    }

    public ArrayList<Achievement> getProfileAchievements() {
        return profileAchievements;
    }

    public void setProfileAchievements(ArrayList<Achievement> profileAchievements) {
        this.profileAchievements = profileAchievements;
    }

}
