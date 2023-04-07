package ru.mirea.playedu.model;

import java.util.Date;

public class PlayEduTask {

    private int taskId;
    private String label;
    private PlayEduEvent event;
    private boolean status;
    private int coinsReward;
    private Date deadlineDate;
    private Date creationDate;

    public PlayEduTask(int taskId, String label, PlayEduEvent event, boolean status,
                       int coinsReward, Date deadlineDate, Date creationDate) {
        this.taskId = taskId;
        this.label = label;
        this.event = event;
        this.status = status;
        this.coinsReward = coinsReward;
        this.deadlineDate = deadlineDate;
        this.creationDate = creationDate;
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

    public PlayEduEvent getEvent() {
        return event;
    }

    public void setEvent(PlayEduEvent event) {
        this.event = event;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayEduTask task = (PlayEduTask) o;

        if (taskId != task.taskId) return false;
        if (status != task.status) return false;
        if (coinsReward != task.coinsReward) return false;
        if (!label.equals(task.label)) return false;
        if (!event.equals(task.event)) return false;
        if (!deadlineDate.equals(task.deadlineDate)) return false;
        return creationDate.equals(task.creationDate);
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + label.hashCode();
        result = 31 * result + event.hashCode();
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + coinsReward;
        result = 31 * result + deadlineDate.hashCode();
        result = 31 * result + creationDate.hashCode();
        return result;
    }
}
