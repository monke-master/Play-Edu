package ru.mirea.playedu.model;

import androidx.annotation.Nullable;

// Класс ответа репозитория
public class Response {

    // Код ответа
    private int code;
    // Тело ответа
    private String body;
    // Объект ответа
    @Nullable
    private Object responseObject;
    
    public Response(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Nullable
    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }
}
