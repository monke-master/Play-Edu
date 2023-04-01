package ru.mirea.playedu.model;

import java.net.URL;

public class Achievement {

    private int achievementId;
    private int userAchievementId;
    private String title;
    private String description;
    private boolean status;
    private URL icon;

    public Achievement(int achievementId, int userAchievementId, String title,
                       String description, boolean status, URL icon) {
        this.achievementId = achievementId;
        this.userAchievementId = userAchievementId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.icon = icon;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public int getUserAchievementId() {
        return userAchievementId;
    }

    public void setUserAchievementId(int userAchievementId) {
        this.userAchievementId = userAchievementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }



}
