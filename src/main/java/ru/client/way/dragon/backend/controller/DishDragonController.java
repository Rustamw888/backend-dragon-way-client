package ru.client.way.dragon.backend.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.client.way.dragon.backend.entity.CategoryEntity;
import ru.client.way.dragon.backend.entity.DishEntity;
import ru.client.way.dragon.backend.search.CategorySearchValues;
import ru.client.way.dragon.backend.search.DishSearchValues;
import ru.client.way.dragon.backend.service.CategoryDragonService;
import ru.client.way.dragon.backend.service.DishDragonService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/dish")
public class DishDragonController {

    public static final String ID_COLUMN = "id"; // имя столбца id

    // todo: не понял как без autowired прописывать оба конструктора в одном месте, просит создать со всеми полями.
//    возможно нужен RequiredArgsConstructor, но не уверен работает ли он тут как надо или нужно все таки autowired?
    // в данном случае убрал final с полей dishDragonService и categoryDragonService + прописал пустой конструктор.

    private DishDragonService dishDragonService;

    private CategoryDragonService categoryDragonService;

    public DishDragonController() {
    }

    public DishDragonController(DishDragonService dishDragonService) {
        this.dishDragonService = dishDragonService;
    }

    public DishDragonController(CategoryDragonService categoryDragonService) {
        this.categoryDragonService = categoryDragonService;
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

    // поиск по любым параметрам CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<List<CategoryEntity>> search(@RequestBody CategorySearchValues categorySearchValues) {

        // проверка на обязательные параметры
        if (categorySearchValues.getTitle() == null || categorySearchValues.getTitle().trim().isEmpty()) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        // поиск категорий пользователя по названию
        // todo: какой вариант лучше выбрать?
        List<CategoryEntity> list = categoryDragonService.findByDishTitle(categorySearchValues.getTitle());
//        List<CategoryEntity> list = categoryDragonService.findByTitleQueryUsing(categorySearchValues.getTitle());

        return ResponseEntity.ok(list);
    }

    // параметр id передаются не в BODY запроса, а в самом URL
    // todo: правильно ли вообще передавать список id в URL и как это делать правильно?
//    @PostMapping("/{ids}")
//    public ResponseEntity<CategoryEntity> findById(@RequestBody List<Long> ids) {
//
//        List<CategoryEntity> categories = null;
//
//        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
//        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
//        try {
//            categories = categoryDragonService.findAll(ids);
//        } catch (NoSuchElementException e) { // если объект не будет найден
//            e.printStackTrace();
//            return new ResponseEntity("id=" + ids + " not found", HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        return ResponseEntity.ok((CategoryEntity) categories);
//    }

    // получение объекта по id
    @PostMapping("/id")
    public ResponseEntity<DishEntity> findById(@RequestBody Long id) {

        DishEntity dish = null;

        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
        try {
            dish = dishDragonService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(dish);
    }

    // поиск по любым параметрам DishSearchValues
    @PostMapping("/search")
    public ResponseEntity<Page<DishEntity>> search(@RequestBody DishSearchValues dishSearchValues) throws Exception {

        // исключить NullPointerException
        String title = dishSearchValues.getTitle() != null ? dishSearchValues.getTitle() : null;

        // проверка на обязательные параметры
        if (title == null || title.trim().isEmpty()) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        Long categoryId = dishSearchValues.getCategoryId() != null ? dishSearchValues.getCategoryId() : null;

        String sortColumn = dishSearchValues.getSortColumn() != null ? dishSearchValues.getSortColumn() : null;
        String sortDirection = dishSearchValues.getSortDirection() != null ? dishSearchValues.getSortDirection() : null;

        Integer pageNumber = dishSearchValues.getPageNumber() != null ? dishSearchValues.getPageNumber() : null;
        Integer pageSize = dishSearchValues.getPageSize() != null ? dishSearchValues.getPageSize() : null;

        // выставить фильтрацию по цене
        Long priceFrom = null;
        Long priceTo = null;

        // если больше 0 и не null, то оставить значение
        if (dishSearchValues.getPriceFrom() != null && dishSearchValues.getPriceFrom() >= 0
                && dishSearchValues.getPriceTo() != null && dishSearchValues.getPriceTo() >= 0) {
            // и если начальная цена меньше, конечной
            if (dishSearchValues.getPriceFrom() <= dishSearchValues.getPriceTo()) {
                priceFrom = dishSearchValues.getPriceFrom(); // записываем начальную цену
                priceTo = dishSearchValues.getPriceTo(); // записываем конечную цену
            }
        } else {
            throw new Exception("invalid weight value");
        }

        // выставить фильтрацию по весу
        Long weightFrom = null;
        Long weightTo = null;

        // если больше 0 и не null, то оставить значение
        if (dishSearchValues.getWeightFrom() != null && dishSearchValues.getWeightFrom() >= 0
                && dishSearchValues.getWeightTo() != null && dishSearchValues.getWeightTo() >= 0) {
            // и если начальный вес меньше, конечного
            if (dishSearchValues.getWeightFrom() <= dishSearchValues.getWeightTo()) {
                weightFrom = dishSearchValues.getWeightFrom(); // записываем начальный вес
                weightTo = dishSearchValues.getWeightTo(); // записываем конечный вес
            }
        } else {
            throw new Exception("invalid weight value");
        }

        // выставить фильтрацию по калориям
        Long caloriesFrom = null;
        Long caloriesTo = null;

        // если больше 0 и не null, то оставить значение
        if (dishSearchValues.getCaloriesFrom() != null && dishSearchValues.getCaloriesFrom() >= 0
                && dishSearchValues.getCaloriesTo() != null && dishSearchValues.getCaloriesTo() >= 0) {
            // и если начальные калории меньше, конечных
            if (dishSearchValues.getCaloriesFrom() <= dishSearchValues.getCaloriesTo()) {
                caloriesFrom = dishSearchValues.getCaloriesFrom(); // записываем начальные калории
                caloriesTo = dishSearchValues.getCaloriesTo(); // записываем конечные калории
            }
        } else {
            throw new Exception("invalid weight value");
        }

        // направление сортировки
        Sort.Direction direction = sortDirection == null || sortDirection.trim().isEmpty() || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        /* Вторым полем для сортировки добавляем id, чтобы всегда сохранялся строгий порядок.
            Например, если у 2-х блюд одинаковое значение цены, то мы сортируем по этому полю.
            Порядок следования этих 2-х записей после выполнения запроса может каждый раз меняться, т.к. не указано второе поле сортировки.
            Поэтому и используем ID - тогда все записи с одинаковым значением цены будут следовать в одном порядке по ID.
         */

        // объект сортировки, который содержит столбец и направление
        Sort sort = Sort.by(direction, sortColumn, ID_COLUMN);

        // объект постраничности
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        // результат запроса с постраничным выводом
        Page<DishEntity> result =
                dishDragonService.findByParams(title, priceFrom, priceTo, weightFrom, weightTo,
                        caloriesFrom, caloriesTo, categoryId, pageRequest);

        // результат запроса
        return ResponseEntity.ok(result);

    }
}
