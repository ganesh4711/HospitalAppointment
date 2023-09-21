package com.main.responseDto;


import com.main.RequestDto.PatientDto;
import liquibase.pro.packaged.C;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
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
