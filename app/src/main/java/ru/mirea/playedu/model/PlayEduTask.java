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
}
