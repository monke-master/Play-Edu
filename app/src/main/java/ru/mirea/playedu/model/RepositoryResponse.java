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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepositoryResponse that = (RepositoryResponse) o;

        if (code != that.code) return false;
        return body.equals(that.body);
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + body.hashCode();
        return result;
    }
}
