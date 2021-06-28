package com.ttrelloapi.ttrellorestapi.services.Impl;

import com.ttrelloapi.ttrellorestapi.entities.CardTasks;
import com.ttrelloapi.ttrellorestapi.entities.Card;
import com.ttrelloapi.ttrellorestapi.repo.TaskRepository;
import com.ttrelloapi.ttrellorestapi.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public CardTasks addOrSaveTask(CardTasks task) {
        return taskRepository.save(task);
    }

    @Override
    public List<CardTasks> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public CardTasks getTask(Long id) {
        return taskRepository.getOne(id);
    }

//    @Override
//    public void deleteByCard(Card card) {
//        taskRepository.deleteAllByCard(card);
//    }
//
//    @Override
//    public List<CardTasks> getAllTasksByCard(Card card) {
//        return taskRepository.findAllByCard(card);
//    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
