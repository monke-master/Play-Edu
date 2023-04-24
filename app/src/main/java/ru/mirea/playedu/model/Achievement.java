package ru.mirea.playedu.model;

import java.net.URL;

public class Achievement {

    private int achievementId;
    private int userAchievementId;
    private String title;
    private String description;
    private boolean unlocked;
    private int icon;

    public Achievement(int achievementId, int userAchievementId, String title,
                       String description, boolean unlocked, int icon) {
        this.achievementId = achievementId;
        this.userAchievementId = userAchievementId;
        this.title = title;
        this.description = description;
        this.unlocked = unlocked;
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

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Achievement that = (Achievement) o;

        if (achievementId != that.achievementId) return false;
        if (userAchievementId != that.userAchievementId) return false;
        if (unlocked != that.unlocked) return false;
        if (!title.equals(that.title)) return false;
        if (!description.equals(that.description)) return false;
        return icon == that.icon;
    }

    @Override
    public int hashCode() {
        int result = achievementId;
        result = 31 * result + userAchievementId;
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (unlocked ? 1 : 0);
        result = 31 * result + icon;
        return result;
    }


}
