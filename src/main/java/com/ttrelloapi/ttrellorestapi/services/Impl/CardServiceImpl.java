package com.ttrelloapi.ttrellorestapi.services.Impl;

import com.ttrelloapi.ttrellorestapi.entities.Board;
import com.ttrelloapi.ttrellorestapi.entities.Card;
import com.ttrelloapi.ttrellorestapi.repo.CardRepository;
import com.ttrelloapi.ttrellorestapi.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;


    @Override
    public Card addOrSaveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCard(Long id) {
        return cardRepository.findById(id).get();
    }

    @Override
    public List<Card> getAllTasksByBoard(Board board) {
        return cardRepository.findAllByBoard(board);
    }

    @Override
    public void deleteByBoard(Board board) {
            cardRepository.deleteAllByBoard(board);
    }

    @Override
    public void deleteCard(Long id) { cardRepository.deleteById(id);
    }
}
