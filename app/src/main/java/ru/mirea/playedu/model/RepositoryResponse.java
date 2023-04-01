package ru.mirea.playedu.model;

public class RepositoryResponse {

    private int code;
    private String body;


    public RepositoryResponse(int code, String body) {
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
}
