package ru.mirea.playedu.model;

import java.util.Date;

public class UserTask {

    private int taskId;
    private String label;
    private Category category;
    private boolean completed;
    private int coinsReward;
    private Date deadlineDate;
    private Date creationDate;
    private int color;

    public UserTask(String label, Category category, boolean completed, int coinsReward,
                    Date deadlineDate, Date creationDate, int color) {
        this.label = label;
        this.category = category;
        this.completed = completed;
        this.coinsReward = coinsReward;
        this.deadlineDate = deadlineDate;
        this.creationDate = creationDate;
        this.color = color;
    }

    public UserTask() {
        label = "";
        category = new Category("");
        completed = true;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTask task = (UserTask) o;

        if (taskId != task.taskId) return false;
        if (completed != task.completed) return false;
        if (coinsReward != task.coinsReward) return false;
        if (color != task.color) return false;
        if (!label.equals(task.label)) return false;
        if (!category.equals(task.category)) return false;
        if (!deadlineDate.equals(task.deadlineDate)) return false;
        return creationDate.equals(task.creationDate);
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + label.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + coinsReward;
        result = 31 * result + deadlineDate.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + color;
        return result;
    }
}
