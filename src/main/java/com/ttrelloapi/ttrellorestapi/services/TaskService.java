package com.ttrelloapi.ttrellorestapi.services;

import com.ttrelloapi.ttrellorestapi.entities.CardTasks;
import com.ttrelloapi.ttrellorestapi.entities.Card;

import java.util.List;

public interface TaskService {

    CardTasks addOrSaveTask(CardTasks task);
    List<CardTasks> getAllTasks();
    CardTasks getTask(Long id);
    void deleteTask(Long id);
//    List<CardTasks> getAllTasksByCard(Card card);
//    void deleteByCard(Card card);
}
