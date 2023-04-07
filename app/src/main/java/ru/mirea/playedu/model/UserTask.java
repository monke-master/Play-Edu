package ru.mirea.playedu.model;

import java.util.Date;

public class UserTask {

    private int taskId;
    private String label;
    private Category category;
    private boolean status;
    private int coinsReward;
    private Date deadlineDate;
    private Date creationDate;
    private int color;

    public UserTask(String label, Category category, boolean status, int coinsReward,
                    Date deadlineDate, Date creationDate, int color) {
        this.label = label;
        this.category = category;
        this.status = status;
        this.coinsReward = coinsReward;
        this.deadlineDate = deadlineDate;
        this.creationDate = creationDate;
        this.color = color;
    }

    public UserTask() {
        label = "";
        category = new Category("");
        status = true;
        coinsReward = 0;
        deadlineDate = new Date();
        creationDate = new Date();
        color = 0;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCoinsReward() {
        return coinsReward;
    }

    public void setCoinsReward(int coinsReward) {
        this.coinsReward = coinsReward;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
