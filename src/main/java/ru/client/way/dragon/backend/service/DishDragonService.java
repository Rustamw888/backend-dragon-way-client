package ru.client.way.dragon.backend.service;

import ru.client.way.dragon.backend.entity.DishEntity;
import ru.client.way.dragon.backend.repository.DishDragonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DishDragonService {

    private final DishDragonRepository repository;

    public DishDragonService(DishDragonRepository repository) {
        this.repository = repository;
    }

    public DishEntity add(DishEntity dish) {
        return repository.save(dish); // метод save обновляет или создает новый объект, если его не было
        // наверное нужна колонка общей суммы
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

//    public List<CategoryEntity> findAll(Long id) {
//        return repository.findAllByIdOrderByTitleDesc(id);
//    }
//
//    public List<CategoryEntity> findAllSalads(Long id) {
//        return repository.findAllByIdOrderByTitleDesc(id);
//    }
//
//    public List<CategoryEntity> findAllSnacks(Long id) {
//        return repository.findAllByIdOrderByTitleDesc(id);
//    }
//
//    public List<CategoryEntity> findAllSoups(Long id) {
//        return repository.findAllByIdOrderByTitleDesc(id);
//    }
//
//    public List<CategoryEntity> findAllHotDishes(Long id) {
//        return repository.findAllByIdOrderByTitleDesc(id);
//    }
//
//    public List<CategoryEntity> findAllSideDishes(Long id) {
//        return repository.findAllByIdOrderByTitleDesc(id);
//    }
//
//    public List<CategoryEntity> findAllDesserts(Long id) {
//        return repository.findAllByIdOrderByTitleDesc(id);
//    }
//
//    public List<CategoryEntity> findAllDrinks(Long id) {
//        return repository.findAllByIdOrderByTitleDesc(id);
//    }
//
//    public List<BasketEntity> addAll(Long id, Long sum, String name) {
//        return repository.addAllByIdOrderByTitleDesc(id, sum, name);
//    }
//
//    public List<BasketEntity> deleteAll(Long id, Long sum) {
//        return repository.deleteAllByIdOrderByTitleDesc(id, sum);
//    }
//
//    public List<DishEntity> findAllDishes(String name, Long price) {
//        return repository.findAllDishesByIdOrderByTitleDesc(name, price);
//    }

}
