package com.ofss.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("Success", data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message, null);
    }
}
