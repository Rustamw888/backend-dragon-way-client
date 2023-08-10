package ru.client.way.dragon.backend.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.client.way.dragon.backend.service.DishDragonService;
import ru.client.way.dragon.backend.entity.DishEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish")
public class DishDragonController {

    private final DishDragonService dishDragonService;

    public DishDragonController(DishDragonService dishDragonService) {
        this.dishDragonService = dishDragonService;
    }

    @PostMapping("/add")
    public ResponseEntity<DishEntity> add(@RequestBody DishEntity dish) {

        // проверка на обязательные параметры
        if (dish.getId() != null && dish.getId() != 0) { // это означает, что id заполнено
            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (dish.getTitle() == null || dish.getTitle().trim().isEmpty()) {
            return new ResponseEntity("missed param: title MUST be not null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение price
        if (dish.getPrice() == null || dish.getPrice() != 0) {
            return new ResponseEntity("missed param: title MUST be not null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение weight
        if (dish.getWeight() == null || dish.getWeight() != 0) {
            return new ResponseEntity("missed param: title MUST be not null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение calories
        if (dish.getCalories() == null || dish.getCalories() != 0) {
            return new ResponseEntity("missed param: title MUST be not null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(dishDragonService.add(dish)); // возвращаем добавленный объект с заполненным ID
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
        try {
            dishDragonService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 без объектов (операция прошла успешно)
    }

//    @GetMapping("/all")
//    public List<CategoryEntity> findAllCategory(Long id) {
//        return categoryDragonService.findAll(id);
//    }
//
//    @GetMapping("/salads")
//    public List<CategoryEntity> findAllSalads(Long id) {
//        return categoryDragonService.findAllSalads(id);
//    }
//
//    @GetMapping("/snacks")
//    public List<CategoryEntity> findAllSnacks(Long id) {
//        return categoryDragonService.findAllSnacks(id);
//    }
//
//    @GetMapping("/soups")
//    public List<CategoryEntity> findAllSoups(Long id) {
//        return categoryDragonService.findAllSoups(id);
//    }
//
//    @GetMapping("/hot")
//    public List<CategoryEntity> findAllHotDishes(Long id) {
//        return categoryDragonService.findAllHotDishes(id);
//    }
//
//    @GetMapping("/side_dishes")
//    public List<CategoryEntity> findAllSideDishes(Long id) {
//        return categoryDragonService.findAllSideDishes(id);
//    }
//
//    @GetMapping("/desserts")
//    public List<CategoryEntity> findAllDesserts(Long id) {
//        return categoryDragonService.findAllDesserts(id);
//    }
//
//    @GetMapping("/drinks")
//    public List<CategoryEntity> findAllDrinks(Long id) {
//        return categoryDragonService.findAllDrinks(id);
//    }
//
//    @PostMapping("/add")
//    public List<BasketEntity> addAll(Long id, Long sum, String name) {
//        return categoryDragonService.addAll(id, sum, name);
//    }
////
////    @DeleteMapping("/delete/{id}/{price}")
////    public ResponseEntity<List<Basket>> deleteAll(@PathVariable("id") Long id, @PathVariable("price") Long sum) {
////        return categoryDragonService.deleteAll(id, sum);
////    }
//
//    @PostMapping("/search")
//    public List<DishEntity> findAllDishes(@RequestBody String name, Long price) {
//        return categoryDragonService.findAllDishes(name, price);
//    }
}
