package ru.client.way.dragon.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.client.way.dragon.backend.service.BasketDragonService;

@RestController
@RequestMapping("/basket")
public class BasketDragonController {

    private final BasketDragonService basketDragonService;

    public BasketDragonController(BasketDragonService basketDragonService) {
        this.basketDragonService = basketDragonService;
    }

    @DeleteMapping("/clear")
    public ResponseEntity clear() {

        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
        try {
            basketDragonService.clearBasket();
            // todo: не знаю какой лучше exception тут применить
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Deleting all entities managed by the repository failed", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 без объектов (операция прошла успешно)
    }

}
