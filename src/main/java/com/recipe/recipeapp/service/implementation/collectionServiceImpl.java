package com.hasthiyait.recipeapp.service.implementation;

import com.hasthiyait.recipeapp.dao.request.collectionRequest;
import com.hasthiyait.recipeapp.dao.response.collectionCardResponse;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.model.collection;
import com.hasthiyait.recipeapp.repository.collectionRepository;
import com.hasthiyait.recipeapp.service.collectionService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class collectionServiceImpl implements collectionService {
    private final collectionRepository collectionRepository;

    public collectionServiceImpl(com.hasthiyait.recipeapp.repository.collectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public collection addNewCollection(collectionRequest collectionRequest) {
        collection collection = new collection(collectionRequest.getCollection_name(),collectionRequest.getCollection_status());
    return collectionRepository.save(collection);
    }

    @Override
    public List<collection> getAllCOllections() throws NotFountException {
        List<collection> collectionList = collectionRepository.findAll();
        if(collectionList.isEmpty()){
            throw  new NotFountException("Category list is empty");
        }
        else{
            return collectionList;
        }
    }

    @Override
    public List<collectionCardResponse> getAllCOllectionCards() throws NotFountException {
        List<collection> collectionList = collectionRepository.findAll();
        List<collectionCardResponse> collection1 = new ArrayList<>();
        if(collectionList.isEmpty()){
            throw  new NotFountException("Category list is empty");
        }
        else{
            for (collection collectionIn :  collectionList) {
                // new Blog
               // Blog blog = new Blog(blogIn.getTitle(), blogIn.getCategory(), blogIn.getContent());
                int val= collectionIn.getRecipeList().size();
                collectionCardResponse collectionCardResponse = new collectionCardResponse(collectionIn.getCollectionName(),collectionIn.getRecipeList(),val);
                collection1.add(collectionCardResponse);



            }
            return collection1;

        }
    }
}
