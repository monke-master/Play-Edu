package ru.mirea.playedu.model;

public abstract class PlayEduEvent {

    private int eventId;
    private String description;

    public PlayEduEvent(int eventId, String description) {
        this.eventId = eventId;
        this.description = description;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
