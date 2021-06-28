package com.ttrelloapi.ttrellorestapi.services.Impl;

import com.ttrelloapi.ttrellorestapi.entities.Board;
import com.ttrelloapi.ttrellorestapi.entities.Users;
import com.ttrelloapi.ttrellorestapi.repo.BoardRepository;
import com.ttrelloapi.ttrellorestapi.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board addOrSaveBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> getAllBoards(Users user) {
        return boardRepository.findAllByUser(user);
    }

    @Override
    public Board getBoard(Long id) {
        return boardRepository.findById(id).get();
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public List<Board> getAllByName(String name) {
        return boardRepository.findAllByNameContaining(name);
    }
}
