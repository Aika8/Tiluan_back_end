package com.ttrelloapi.ttrellorestapi.services;

import com.ttrelloapi.ttrellorestapi.entities.Board;
import com.ttrelloapi.ttrellorestapi.entities.Card;

import java.util.List;

public interface CardService {

    Card addOrSaveCard(Card card);
    List<Card> getAllCards();
    Card getCard(Long id);
    void deleteCard(Long id);
    List<Card> getAllTasksByBoard(Board board);
    void deleteByBoard(Board board);
}
