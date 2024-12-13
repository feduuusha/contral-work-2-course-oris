package ru.itis.contralwork.dao;

import ru.itis.contralwork.entity.Gift;

import java.util.List;
import java.util.Optional;

public interface GiftRepository {
    List<Gift> findAllGifts();

    Optional<Gift> findById(Long id);
}
