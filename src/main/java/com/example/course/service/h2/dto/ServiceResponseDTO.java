package com.example.course.service.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseDTO<T> {

    private HttpStatus status;
    private T response;
}