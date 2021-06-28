package com.ttrelloapi.ttrellorestapi.repo;

import com.ttrelloapi.ttrellorestapi.entities.CardTasks;
import com.ttrelloapi.ttrellorestapi.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<CardTasks, Long> {
//    List<CardTasks> findAllByCard(Card card);
//    void deleteAllByCard(Card card);
}
