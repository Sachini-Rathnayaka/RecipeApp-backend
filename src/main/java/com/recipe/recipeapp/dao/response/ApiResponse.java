package com.hasthiyait.recipeapp.dao.response;

import lombok.Getter;

public record ApiResponse<T>(com.hasthiyait.recipeapp.dao.response.ApiResponse.Status status, @Getter T data) {
    public record Status(int code, String message) {
    }
}