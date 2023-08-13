package ru.client.way.dragon.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.client.way.dragon.backend.entity.CategoryEntity;
import ru.client.way.dragon.backend.service.CategoryDragonService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryDragonController {

    private final CategoryDragonService categoryDragonService;

    public CategoryDragonController(CategoryDragonService categoryDragonService) {
        this.categoryDragonService = categoryDragonService;
    }

    @PostMapping("/all")
    public ResponseEntity<List<CategoryEntity>> findAll(@RequestBody List<Long> dishIds) {

        List<CategoryEntity> categories = null;

        try {
            categories = categoryDragonService.findAll(dishIds);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("dishIds[]=" + Collections.singletonList(dishIds) + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categories);
    }

    // todo: возможно просто навигационный вариант, без привязки к блюдам
    @GetMapping("/all")
    public ResponseEntity<List<CategoryEntity>> findAllCategory() {

        List<CategoryEntity> categories = null;

        try {
            categories = categoryDragonService.findAll();
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("categories[] not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categories);
    }

    @PostMapping("/salads")
    public ResponseEntity<CategoryEntity> findAllSalads(@RequestBody Long dishId) {

        CategoryEntity category = null;

        try {
            category = categoryDragonService.findSalads(dishId);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("dishId=" + dishId + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @PostMapping("/snacks")
    public ResponseEntity<CategoryEntity> findAllSnacks(@RequestBody Long dishId) {

        CategoryEntity category = null;

        try {
            category = categoryDragonService.findSnacks(dishId);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("dishId=" + dishId + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @PostMapping("/soups")
    public ResponseEntity<CategoryEntity> findAllSoups(@RequestBody Long dishId) {

        CategoryEntity category = null;

        try {
            category = categoryDragonService.findSoups(dishId);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("dishId=" + dishId + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @PostMapping("/hot")
    public ResponseEntity<CategoryEntity> findAllHotDishes(@RequestBody Long dishId) {

        CategoryEntity category = null;

        try {
            category = categoryDragonService.findHotDishes(dishId);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("dishId=" + dishId + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @PostMapping("/side_dishes")
    public ResponseEntity<CategoryEntity> findAllSideDishes(@RequestBody Long dishId) {

        CategoryEntity category = null;

        try {
            category = categoryDragonService.findSideDishes(dishId);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("dishId=" + dishId + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @PostMapping("/desserts")
    public ResponseEntity<CategoryEntity> findAllDesserts(@RequestBody Long dishId) {

        CategoryEntity category = null;

        try {
            category = categoryDragonService.findDesserts(dishId);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("dishId=" + dishId + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @PostMapping("/drinks")
    public ResponseEntity<CategoryEntity> findDrinks(@RequestBody Long dishId) {

        CategoryEntity category = null;

        try {
            category = categoryDragonService.findDrinks(dishId);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("dishId=" + dishId + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }
}
