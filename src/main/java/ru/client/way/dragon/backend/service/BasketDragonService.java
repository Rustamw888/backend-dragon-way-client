package ru.client.way.dragon.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.client.way.dragon.backend.repository.BasketDragonRepository;

@Service
@Transactional
public class BasketDragonService {

    private final BasketDragonRepository repository;

    public BasketDragonService(BasketDragonRepository repository) {
        this.repository = repository;
    }

    public void clearBasket() {
        repository.deleteAll();
    }

}
