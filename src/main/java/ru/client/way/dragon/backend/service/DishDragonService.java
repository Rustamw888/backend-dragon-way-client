package ru.client.way.dragon.backend.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.client.way.dragon.backend.entity.DishEntity;
import ru.client.way.dragon.backend.repository.DishDragonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DishDragonService {

    private final DishDragonRepository repository;

    public DishDragonService(DishDragonRepository repository) {
        this.repository = repository;
    }

    public DishEntity add(DishEntity dish) {
        return repository.save(dish); // метод save обновляет или создает новый объект, если его не было
        // todo: наверное нужна колонка общей суммы
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public DishEntity findById(Long id) {
        return repository.findById(id).get(); // т.к. возвращается Optional - можно получить объект методом get()
    }

    @Cacheable(cacheNames = "dishes")
    public Page<DishEntity> findByParams(String title, Long priceFrom, Long priceTo, Long weightFrom,
            Long weightTo, Long caloriesFrom, Long caloriesTo, Long categoryId, PageRequest paging) {
        return repository.findByParams(title, priceFrom, priceTo, weightFrom, weightTo,
                caloriesFrom, caloriesTo, categoryId, paging);
    }

    @Cacheable(cacheNames = "dishes")
    public List<DishEntity> findAll(String email) {
        return repository.findByUserEmailOrderByTitleAsc(email);
    }
}
