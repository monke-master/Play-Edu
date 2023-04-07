package ru.mirea.playedu.model;

import java.util.Date;

public class UserStats {

    private int statsId;
    private Date registrationDate;
    private int playEduTasksCompleted;
    private int userTasksCompleted;
    private int fightsCount;
    private int enemiesKilled;
    private int quizCompleted;
    private int profilesVisited;


    public UserStats(int statsId, Date registrationDate, int playEduTasksCompleted,
                     int userTasksCompleted, int fightsCount, int enemiesKilled,
                     int quizCompleted, int profilesVisited) {
        this.statsId = statsId;
        this.registrationDate = registrationDate;
        this.playEduTasksCompleted = playEduTasksCompleted;
        this.userTasksCompleted = userTasksCompleted;
        this.fightsCount = fightsCount;
        this.enemiesKilled = enemiesKilled;
        this.quizCompleted = quizCompleted;
        this.profilesVisited = profilesVisited;
    }


    public int getStatsId() {
        return statsId;
    }

    public void setStatsId(int statsId) {
        this.statsId = statsId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getPlayEduTasksCompleted() {
        return playEduTasksCompleted;
    }

    public void setPlayEduTasksCompleted(int playEduTasksCompleted) {
        this.playEduTasksCompleted = playEduTasksCompleted;
    }

    public int getUserTasksCompleted() {
        return userTasksCompleted;
    }

    public void setUserTasksCompleted(int userTasksCompleted) {
        this.userTasksCompleted = userTasksCompleted;
    }

    public int getFightsCount() {
        return fightsCount;
    }

    public void setFightsCount(int fightsCount) {
        this.fightsCount = fightsCount;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    public int getQuizCompleted() {
        return quizCompleted;
    }

    public void setQuizCompleted(int quizCompleted) {
        this.quizCompleted = quizCompleted;
    }

    public int getProfilesVisited() {
        return profilesVisited;
    }

    public void setProfilesVisited(int profilesVisited) {
        this.profilesVisited = profilesVisited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStats userStats = (UserStats) o;

        if (statsId != userStats.statsId) return false;
        if (playEduTasksCompleted != userStats.playEduTasksCompleted) return false;
        if (userTasksCompleted != userStats.userTasksCompleted) return false;
        if (fightsCount != userStats.fightsCount) return false;
        if (enemiesKilled != userStats.enemiesKilled) return false;
        if (quizCompleted != userStats.quizCompleted) return false;
        if (profilesVisited != userStats.profilesVisited) return false;
        return registrationDate.equals(userStats.registrationDate);
    }

    @Override
    public int hashCode() {
        int result = statsId;
        result = 31 * result + registrationDate.hashCode();
        result = 31 * result + playEduTasksCompleted;
        result = 31 * result + userTasksCompleted;
        result = 31 * result + fightsCount;
        result = 31 * result + enemiesKilled;
        result = 31 * result + quizCompleted;
        result = 31 * result + profilesVisited;
        return result;
    }
}
