package ru.client.way.dragon.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.client.way.dragon.backend.entity.DishEntity;

import java.util.List;

public interface DishDragonRepository extends JpaRepository<DishEntity, Long> {

    @Query("SELECT d FROM DishEntity d where " +
            "(:title is null or :title='' or lower(d.title) like lower(concat('%', :title,'%'))) and" +

            "(" +
            "(cast(:priceFrom as timestamp) is null or t.price>=:priceFrom) and " +
            "(cast(:priceTo as timestamp) is null or t.price<=:priceTo)" +
            "(cast(:weightFrom as timestamp) is null or t.weight>=:weightFrom) and " +
            "(cast(:weightTo as timestamp) is null or t.weight<=:weightTo)" +
            "(cast(:caloriesFrom as timestamp) is null or t.calories>=:caloriesFrom) and " +
            "(cast(:caloriesTo as timestamp) is null or t.calories<=:caloriesTo)" +
            ") and " +
            "(:categoryId is null or d.category.id=:categoryId) and " +
            "(t.user.email=:email)" // показывать задачи только определенного пользователя, а не все
    )
        // искать по всем переданным параметрам (пустые параметры учитываться не будут)
    Page<DishEntity> findByParams(
            @Param("title") String title,
            @Param("priceFrom") Long priceFrom,
            @Param("priceTo") Long priceTo,
            @Param("weightFrom") Long weightFrom,
            @Param("weightTo") Long weightTo,
            @Param("caloriesFrom") Long caloriesFrom,
            @Param("caloriesTo") Long caloriesTo,
            @Param("categoryId") Long categoryId,
            Pageable pageable
    );

    // поиск всех блюд конкретного пользователя
    List<DishEntity> findByUserEmailOrderByTitleAsc(String email);
}
