package ru.client.way.dragon.backend.repository;

import ru.client.way.dragon.backend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.client.way.dragon.backend.entity.DishEntity;

public interface DishDragonRepository extends JpaRepository<DishEntity, Long> {

//    List<CategoryEntity> findAllByIdOrderByTitleDesc(Long id);
//
//    List<BasketEntity> addAllByIdOrderByTitleDesc(Long id, Long sum, String name);
//
//    List<BasketEntity> deleteAllByIdOrderByTitleDesc(Long id, Long sum);
//
//    List<DishEntity> findAllDishesByIdOrderByTitleDesc(String name, Long price);
}
