package com.main;


import org.springframework.http.HttpStatus;

import java.util.Map;

public class ApiResponse<T> {
    private int code;
    private String status;
    private String message;
    private Map<String,Object> data;

    public ApiResponse() {

    }

    public ApiResponse(HttpStatus httpStatus, String message) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

    public ApiResponse(HttpStatus httpStatus, String message, Map<String,Object> data) {
        this(httpStatus,message);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
