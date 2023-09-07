package com.main;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ApiResponse<T> {
    private int code;
    private String status;
    private String message;
    private Map<String,Object> data;
    private Map<String,Object> meta;

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
    public ApiResponse(HttpStatus status, String message, Map<String, Object> data, Map<String, Object> meta) {
        this(status,message,data);
        this.meta = meta;
    }

}
