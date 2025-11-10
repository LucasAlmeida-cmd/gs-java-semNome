package com.example.gs_java.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private int statusCode;
    private String message;
    private String path;
    private Instant timestamp;
}