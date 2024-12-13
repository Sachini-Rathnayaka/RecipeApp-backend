package com.hasthiyait.recipeapp.exception;

import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> notValid(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();

        exception.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);
        ApiResponse.Status status = new ApiResponse.Status(400, "request body does not exist");
        ApiResponse<Object> apiResponse = new ApiResponse<>(status, result);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(categoryNotFountExeption.class)
    public ResponseEntity<ApiResponse<Object>>handleCategoryNotFoundException(categoryNotFountExeption ex){
        Map<String,String> result = new HashMap<>();
        result.put("errors:", ex.getMessage())  ;
        ApiResponse.Status status = new ApiResponse.Status(404, "category id is not found");
        ApiResponse<Object> apiResponse = new ApiResponse<>(status, result);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(dishNotFoundExeption.class)
    public ResponseEntity<ApiResponse<Object>>handleDishNotFoundException(dishNotFoundExeption ex){
        Map<String,String> result = new HashMap<>();
        result.put("errors:", ex.getMessage())  ;
        ApiResponse.Status status = new ApiResponse.Status(404, "dish id is not found");
        ApiResponse<Object> apiResponse = new ApiResponse<>(status, result);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        ApiResponse<String> response = new ApiResponse<>(status, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<ApiResponse<Object>>handleNotFoundException(NotFountException ex){
        Map<String,String> result = new HashMap<>();
        result.put("errors:", ex.getMessage())  ;
        ApiResponse.Status status = new ApiResponse.Status(404, "Not found");
        ApiResponse<Object> apiResponse = new ApiResponse<>(status, result);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
