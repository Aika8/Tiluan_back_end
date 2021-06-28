package com.ttrelloapi.ttrellorestapi.controllers;


import com.ttrelloapi.ttrellorestapi.entities.Board;
import com.ttrelloapi.ttrellorestapi.entities.CardTasks;
import com.ttrelloapi.ttrellorestapi.entities.Card;
import com.ttrelloapi.ttrellorestapi.entities.Users;
import com.ttrelloapi.ttrellorestapi.services.BoardService;
import com.ttrelloapi.ttrellorestapi.services.CardService;
import com.ttrelloapi.ttrellorestapi.services.TaskService;
import com.ttrelloapi.ttrellorestapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api")
public class HomeController {


    @Autowired
    private CardService cardService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/allCards")
    public List<Card> getAllCards(){
        System.out.println(cardService.getAllCards());
        return cardService.getAllCards();
    }

    @GetMapping(value = "/allBoards")
    public List<Board> getAllBoards(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        System.out.println(currentPrincipalName);

        return boardService.getAllBoards((Users) userService.loadUserByUsername(currentPrincipalName));
    }


    @GetMapping(value = "/card")
    public Card getCard(@RequestParam(name = "id") Long id){
        System.out.println(id);
        return cardService.getCard(id);
    }

    @GetMapping(value = "/board")
    public Board getBoard(@RequestParam(name = "id") Long id){
        System.out.println(id);
        return boardService.getBoard(id);
    }

    @GetMapping(value = "/allCardsByBoard")
    public List<Card> getAllTasksByCard(@RequestParam(name = "id") Long id){
        System.out.println(id);
        Board board = boardService.getBoard(id);
        System.out.println(board);
        return cardService.getAllTasksByBoard(board);
    }

    @PostMapping(value = "/addCard")
    public Card addCard(@RequestBody Card card){
        return cardService.addOrSaveCard(card);
    }

    @PostMapping(value = "/addBoard")
    public Board addBoard(@RequestBody Board board){
        System.out.println(board);
        return boardService.addOrSaveBoard(board);
    }

    @PostMapping(value = "/addTask")
    public CardTasks addTask(@RequestBody CardTasks task){
        return taskService.addOrSaveTask(task);
    }

    @PostMapping(value = "/deleteCard")
    public void deleteCard(@RequestParam(name = "id") Long id){
        Card card = cardService.getCard(id);
//        List<CardTasks> tasks = taskService.getAllTasksByCard(card);
//        for(CardTasks task : tasks){
//            taskService.deleteTask(task.getId());
//        }
        cardService.deleteCard(id);
    }

    @PostMapping(value = "/deleteBoard")
    public void deleteBoard(@RequestParam(name = "id") Long id){
        boardService.deleteBoard(id);
    }

    @PostMapping(value = "/deleteTask")
    public void deleteTask(@RequestParam(name = "id") Long id){
        taskService.deleteTask(id);
    }

    @GetMapping(value = "/allBoardsBySearch")
    public List<Board> allBoardsBySearch(@RequestParam(name = "search") String search){
        return boardService.getAllByName(search);
    }


    /**user return with password; should create TO class and pass to it's constructor just name and email**/
    @GetMapping(value = "/user")
    public UserDetails getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        System.out.println(currentPrincipalName);

        return userService.loadUserByUsername(currentPrincipalName);
    }

    @PostMapping(value="/addUser")
    public Users addUser(@RequestBody Users user){
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.addOrSaveTask(user);
    }

    @GetMapping(value="/checkPassword")
    public Boolean checkPassword(@RequestParam(name = "password") String password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDetails user = userService.loadUserByUsername(currentPrincipalName);

        System.out.println(password);
        System.out.println(user.getPassword());
        return passwordEncoder.matches(password, user.getPassword());
    }

}
