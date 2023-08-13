package ru.client.way.dragon.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.client.way.dragon.backend.entity.CategoryEntity;
import ru.client.way.dragon.backend.repository.CategoryDragonRepository;

import java.util.List;

@Service
@Transactional
public class CategoryDragonService {

    private final CategoryDragonRepository repository;

    public CategoryDragonService(CategoryDragonRepository repository) {
        this.repository = repository;
    }

    public List<CategoryEntity> findAll(List<Long> dishIds) {
        return repository.findAllById(dishIds);
    }

    // поиск категорий пользователя по названию
    public List<CategoryEntity> findByDishTitle(String text) {
        return repository.findByDishTitle(text);
    }

    // поиск категорий пользователя по названию
    public List<CategoryEntity> findByTitleQueryUsing(String text) {
        return repository.findByTitle(text);
    }

    public List<CategoryEntity> findAll() {
        return repository.findAll();
    }

    public CategoryEntity findSalads(Long dishId) {
        return repository.findById(dishId).get();
    }

    public CategoryEntity findSnacks(Long dishId) {
        return repository.findById(dishId).get();
    }

    public CategoryEntity findSoups(Long dishId) {
        return repository.findById(dishId).get();
    }

    public CategoryEntity findHotDishes(Long dishId) {
        return repository.findById(dishId).get();
    }

    public CategoryEntity findSideDishes(Long dishId) {
        return repository.findById(dishId).get();
    }

    public CategoryEntity findDesserts(Long dishId) {
        return repository.findById(dishId).get();
    }

    public CategoryEntity findDrinks(Long dishId) {
        return repository.findById(dishId).get();
    }
}
