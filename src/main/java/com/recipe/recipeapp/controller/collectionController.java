package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.request.collectionRequest;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.dao.response.collectionCardResponse;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.model.collection;
import com.hasthiyait.recipeapp.service.collectionService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collection")
public class collectionController {
    private final collectionService collectionService;

    @Autowired
    public collectionController(
            com.hasthiyait.recipeapp.service.collectionService collectionService
    ) {
        this.collectionService = collectionService;
    }

    @PostMapping("/add-new-collection")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<collection>> addNewCollection(
            @Valid @RequestBody collectionRequest collectionRequest
    ) {
        ApiResponse.Status status = new ApiResponse.Status(
                HttpStatus.CREATED.value(),
                "add the collection successfully"
        );
        ApiResponse<collection> response = new ApiResponse<>(
                status,
                collectionService.addNewCollection(collectionRequest)
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-collections")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<List<collection>>> getAllCollections()
            throws NotFountException {
        ApiResponse.Status status = new ApiResponse.Status(
                HttpStatus.OK.value(),
                "received all collection successfully"
        );
        ApiResponse<List<collection>> response = new ApiResponse<>(
                status,
                collectionService.getAllCOllections()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all-collection-cards")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<List<collectionCardResponse>>> getAllCollectionsCards()
            throws NotFountException {
        ApiResponse.Status status = new ApiResponse.Status(
                HttpStatus.OK.value(),
                "received all collection cards successfully"
        );
        ApiResponse<List<collectionCardResponse>> response = new ApiResponse<>(
                status,
                collectionService.getAllCOllectionCards()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
