package ru.client.way.dragon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.client.way.dragon.backend.entity.CategoryEntity;

import java.util.List;

public interface CategoryDragonRepository extends JpaRepository<CategoryEntity, Long> {

    // поиск категорий пользователя (по названию)
    List<CategoryEntity> findByDishTitle(String text);

    // todo: не сильно понял эту тему
    // поиск значений по названию для конкретного пользователя
    @Query("SELECT c FROM CategoryEntity c where " +
            "(:title is null or :title='' " + // если передадим параметр title пустым, то выберутся все записи (сработает именно это условие)
            " or lower(c.title) like lower(concat('%', :title,'%'))) " + // если параметр title не пустой, то выполнится уже это условие
            " and c.dish.title=:title  " + // фильтрация для конкретного блюда
            " order by c.title asc") // сортировка по названию
    List<CategoryEntity> findByTitle(@Param("title") String title);
}
