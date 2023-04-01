package ru.mirea.playedu.model;

import org.json.JSONObject;

public class Quiz extends PlayEduEvent {

    private JSONObject quizData;

    public Quiz(int eventId, String description, JSONObject quizData) {
        super(eventId, description);
        this.quizData = quizData;
    }

    public JSONObject getQuizData() {
        return quizData;
    }

    public void setQuizData(JSONObject quizData) {
        this.quizData = quizData;
    }

}
