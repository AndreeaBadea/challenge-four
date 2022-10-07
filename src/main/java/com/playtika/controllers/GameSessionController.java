package com.playtika.controllers;

import com.playtika.models.GameSession;
import com.playtika.models.SpaceInvader;
import com.playtika.services.GameSessionService;
import com.playtika.services.SpaceInvaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sessions")
public class GameSessionController {

    @Autowired
    GameSessionService gameSessionService;

    @Autowired
    SpaceInvaderService spaceInvaderService;

    private final int MIN_NUMBER_SPACE_INVADERS = 20;

    @GetMapping
    public ResponseEntity<List<GameSession>> getPlayers(){
        List<GameSession>  gameSessionList = gameSessionService.getGameSessions();
        if(gameSessionList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gameSessionList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameSession> getPlayerProfileById(@PathVariable int id){
        Optional<GameSession> optionalGameSession = gameSessionService.getGameSessionById(id);
        if(optionalGameSession.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalGameSession.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGameSession(@PathVariable long id){
        boolean response = gameSessionService.deleteGameSession(id);
        if (!response){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Game session successfully deleted!", HttpStatus.OK);
    }

    @GetMapping("/invaders/generate")
    public ResponseEntity<?> generateSpaceInvaders(@RequestParam(defaultValue = "20") int number) {
        if(number < MIN_NUMBER_SPACE_INVADERS){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
       List<CompletableFuture<SpaceInvader>> spaceInvaders = new ArrayList<>();
        for(int i = 0; i < number; i++) {
            spaceInvaders.add(spaceInvaderService.getGeneratedSpaceInvader());
        }
        return new ResponseEntity(spaceInvaders.stream().map(invader -> {
           try {
               return invader.get();
           } catch (InterruptedException | ExecutionException e) {
              e.printStackTrace();
           }
            return null;
       }).collect(Collectors.toList()), HttpStatus.OK);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> exceptionHandler(Exception e){
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

}
