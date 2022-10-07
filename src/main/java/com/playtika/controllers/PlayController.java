package com.playtika.controllers;

import com.playtika.models.SpaceInvader;
import com.playtika.services.SpaceInvaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/play")
public class PlayController {

    @Autowired
    private SpaceInvaderService spaceInvaderService;


    @PostMapping("/{sessionId}/invaders/{noSpaceInvaders}")
    public ResponseEntity<?> play(@PathVariable long sessionId, @PathVariable int noSpaceInvaders) throws ExecutionException, InterruptedException {
        List<SpaceInvader> spaceInvaders = spaceInvaderService.generateSpaceInvaderForGameSession(sessionId, noSpaceInvaders);
        if(spaceInvaders == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(spaceInvaders, HttpStatus.OK);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> exceptionHandler(Exception e){
        System.out.println(e.getMessage());
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
