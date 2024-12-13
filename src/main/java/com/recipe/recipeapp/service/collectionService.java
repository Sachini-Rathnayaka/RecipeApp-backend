package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.collectionRequest;
import com.hasthiyait.recipeapp.dao.response.collectionCardResponse;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.model.collection;

import java.util.List;

public interface collectionService {
    collection addNewCollection(collectionRequest collectionRequest);

    List<collection> getAllCOllections() throws NotFountException;

    List<collectionCardResponse> getAllCOllectionCards() throws NotFountException;
}
