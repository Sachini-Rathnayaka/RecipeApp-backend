package com.hasthiyait.recipeapp.service.implementation;

import com.hasthiyait.recipeapp.dao.request.dishRequest;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.model.Dish;
import com.hasthiyait.recipeapp.repository.dishRepository;
import com.hasthiyait.recipeapp.service.dishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("null")
public class dishServiceImpl implements dishService {
    private final com.hasthiyait.recipeapp.repository.dishRepository dishRepository;

    public dishServiceImpl(dishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish addNewDish(dishRequest dishRequest) {
          Dish dish = new Dish(dishRequest.getDish_name());
          return dishRepository.save(dish);

    }

    @Override
    public List<Dish> getAllDish() throws NotFountException {
     List<Dish> dishList =  dishRepository.findAll();
     if (dishList.isEmpty()){
         throw new NotFountException("Dish lisat is empty");
     }
     else{
         return  dishList;
     }
    }

    @Override
    public Optional<Dish> deleteDishById(Integer id) throws NotFountException {
        Optional<Dish> Dishln= dishRepository.findById(id);
        if(Dishln.isEmpty()){
            throw  new NotFountException("Given ID is does not exist ");
        }
        else{
            dishRepository.deleteById(id);
            return Dishln;
        }

    }
}
