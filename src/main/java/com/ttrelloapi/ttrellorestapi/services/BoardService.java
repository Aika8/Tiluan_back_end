package com.ttrelloapi.ttrellorestapi.services;

import com.ttrelloapi.ttrellorestapi.entities.Board;
import com.ttrelloapi.ttrellorestapi.entities.Users;

import java.util.List;

public interface BoardService {
    Board addOrSaveBoard(Board board);
    List<Board> getAllBoards(Users user);
    Board getBoard(Long id);
    void deleteBoard(Long id);
    List<Board> getAllByName(String name);
}
