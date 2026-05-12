package com.habib.springbootmysqlcrud.response;

public class ApiResponse<T> {

    private int code;
    private String message;
    private T content;

    public ApiResponse() {}

    public ApiResponse(int code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    // getters & setters
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getContent() { return content; }
    public void setContent(T content) { this.content = content; }
}