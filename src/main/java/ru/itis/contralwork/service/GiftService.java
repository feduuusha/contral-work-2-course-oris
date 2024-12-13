package ru.itis.contralwork.service;

import ru.itis.contralwork.entity.Gift;

import java.util.List;
import java.util.Optional;

public interface GiftService {

    List<Gift> findAllGifts();

    Optional<Gift> findById(Long id);
}
