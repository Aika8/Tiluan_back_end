package com.ttrelloapi.ttrellorestapi.repo;

import com.ttrelloapi.ttrellorestapi.entities.Board;
import com.ttrelloapi.ttrellorestapi.entities.Card;
import com.ttrelloapi.ttrellorestapi.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByNameContaining(String search);
    List<Board> findAllByUser(Users user);
}
