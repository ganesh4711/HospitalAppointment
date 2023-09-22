package com.main.responseDto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private T data;
    private Map<String,Object> meta;


    public ApiResponse(T data) {
        this.data = data;
    }
}
