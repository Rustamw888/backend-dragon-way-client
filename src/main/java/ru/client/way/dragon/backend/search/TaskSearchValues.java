package ru.client.way.dragon.backend.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// возможные значения, по которым можно искать задачи + значения сортировки
public class TaskSearchValues {

    // поля поиска (все типы - объектные, не примитивные. Чтобы можно было передать null)
    private String title;
    private Integer completed;
    private Long priorityId;
    private Long categoryId;
    private String email;

    private Date dateFrom; // для задания периода по датам
    private Date dateTo;

    // постраничность
    private Integer pageNumber;
    private Integer pageSize;

    // сортировка
    private String sortColumn;
    private String sortDirection;

    // такие же названия должны быть у объекта на frontend

}
//"title": "з",
//"completed": 0,
//"priorityId": "30111",
//"categoryId": "",
//"email": "",
//"dateFrom": "",
//"dateTo": "",
//"pageNumber": "",
//"pageSize": "",
//"sortColumn": "",
//"sortDirection": "",

//{    "title":"з",
//        "priorityId":30111,
//        "categoryId":60199,
//        "completed":0,
//        "dateFrom":"2021-08-03",
//        "dateTo":"2021-08-10",
//        "email": "email13@gmail.com",
//        "sortColumn": "title",
//        "sortDirection": "desc",
//        "pageNumber": 0,
//        "pageSize": 5
//}