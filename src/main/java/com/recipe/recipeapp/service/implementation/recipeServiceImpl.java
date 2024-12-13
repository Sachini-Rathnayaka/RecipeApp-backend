package com.hasthiyait.recipeapp.service.implementation;

import com.hasthiyait.recipeapp.dao.request.instructionRequest;
import com.hasthiyait.recipeapp.dao.request.recipeRequest;
import com.hasthiyait.recipeapp.dao.request.sourceRequest;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.exception.categoryNotFountExeption;
import com.hasthiyait.recipeapp.exception.dishNotFoundExeption;
import com.hasthiyait.recipeapp.model.*;
import com.hasthiyait.recipeapp.repository.*;
import com.hasthiyait.recipeapp.service.recipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
@SuppressWarnings("null")
public class recipeServiceImpl implements recipeService {
    private final collectionRepository collectionRepository;
    private final RecipeRepository recipeRepository;
    private final categoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final dishRepository dishRepository;

    public recipeServiceImpl(com.hasthiyait.recipeapp.repository.collectionRepository collectionRepository, RecipeRepository recipeRepository, categoryRepository categoryRepository, UserRepository userRepository, com.hasthiyait.recipeapp.repository.dishRepository dishRepository) {
        this.collectionRepository = collectionRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Recipe addNewRecipe(recipeRequest recipeRequest) throws dishNotFoundExeption, categoryNotFountExeption {
        System.out.println("recipe save called...");
        Recipe recipeIn = new Recipe(recipeRequest.getRecipe_name(), recipeRequest.getDescription());
        List<Source> sourceList = new ArrayList<>();
        for (sourceRequest sourceIn : recipeRequest.getSourceRequestList()) {
            Source source = new Source(sourceIn.getSource_name(), sourceIn.getUrl());
            source.setRecipe(recipeIn);
            sourceList.add(source);
        }
        recipeIn.setSources(sourceList);
        List<Instruction> instructionList = new ArrayList<>();
        for (instructionRequest instructionIn : recipeRequest.getInstructionRequestList()) {
            Instruction instruction = new Instruction(instructionIn.getStep_number(), instructionIn.getDescription());
            instruction.setRecipe(recipeIn);
            instructionList.add(instruction);
        }
        recipeIn.setInstructions(instructionList);
        Category categoryIn = categoryRepository.getById(recipeRequest.getCategory_id());
        recipeIn.setCategory(categoryIn);
        Dish dishIn = dishRepository.getById(recipeRequest.getDish_id());
        recipeIn.setDish(dishIn);
        User userIn = userRepository.getById(recipeRequest.getUser_id());
        recipeIn.setUser(userIn);
        collection collectionIn = collectionRepository.getById(recipeRequest.getCollection_id());
        recipeIn.setCollection(collectionIn);
        return recipeRepository.save(recipeIn);
    }

    @Override
    public Page<Recipe> getAllRecipe(int offset, int pageSize) throws NotFountException {
        Page<Recipe> recipes = recipeRepository.findAll(PageRequest.of(offset, pageSize));
        if (recipes.isEmpty()) {
            throw new NotFountException("No recipes found");
        } else return recipes;
    }

    @Override
    public Recipe getRecipeById(Integer id) throws NotFountException {
        return recipeRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFountException("No recipe found"));
    }
}