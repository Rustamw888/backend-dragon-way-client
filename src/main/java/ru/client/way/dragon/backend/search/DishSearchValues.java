package ru.client.way.dragon.backend.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// возможные значения, по которым можно искать блюда + значения сортировки
public class DishSearchValues {

    // поля поиска (все типы - объектные, не примитивные. Чтобы можно было передать null)
    private String title;

    // для задания периода по цене
    private Long priceFrom;
    private Long priceTo;

    // для задания периода по весу
    private Long weightFrom;
    private Long weightTo;

    // для задания периода по калориям
    private Long caloriesFrom;
    private Long caloriesTo;

    private Long categoryId;

    // постраничность
    private Integer pageNumber;
    private Integer pageSize;

    // сортировка
    private String sortColumn;
    private String sortDirection;

    // такие же названия должны быть у объекта на frontend

}
