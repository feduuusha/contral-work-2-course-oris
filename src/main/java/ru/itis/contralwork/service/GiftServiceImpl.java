package ru.itis.contralwork.service;

import lombok.RequiredArgsConstructor;
import ru.itis.contralwork.dao.GiftRepository;
import ru.itis.contralwork.entity.Gift;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;

    @Override
    public List<Gift> findAllGifts() {
        return giftRepository.findAllGifts();
    }

    @Override
    public Optional<Gift> findById(Long id) {
        return giftRepository.findById(id);
    }
}
