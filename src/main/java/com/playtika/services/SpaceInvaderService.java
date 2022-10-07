package com.playtika.services;

import com.playtika.models.GameSession;
import com.playtika.models.SpaceInvader;
import com.playtika.repositories.GameSessionRepository;
import com.playtika.repositories.SpaceInvaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class SpaceInvaderService {

    @Autowired
    SpaceInvaderRepository spaceInvaderRepository;

    @Autowired
    GameSessionRepository gameSessionRepository;

    private final int BOUND = 50;

    public SpaceInvader generateSpaceInvader(){
        Random random = new Random();
        int x = random.nextInt(BOUND);
        int y = random.nextInt(BOUND);
        return new SpaceInvader(x, y);
    }


    public List<SpaceInvader> generateSpaceInvaderList(int no){
        List<SpaceInvader> list = new ArrayList<>();
        for(int i = 0; i < no; i++) {
            Random random = new Random();
            int x = random.nextInt(BOUND);
            int y = random.nextInt(BOUND);
            list.add(new SpaceInvader(x, y));
        }
        return list;
    }

    @Async
    public CompletableFuture<List<SpaceInvader>> getGeneratedSpaceInvaderList(int no){
        return CompletableFuture.completedFuture(generateSpaceInvaderList(no));
    }


    @Async
    public CompletableFuture<SpaceInvader> getGeneratedSpaceInvader(){
        return CompletableFuture.completedFuture(generateSpaceInvader());
    }


    public List<SpaceInvader> generateSpaceInvaderForGameSession(long sessionId, int noSpaceInvaders) throws ExecutionException, InterruptedException {

      CompletableFuture<List<SpaceInvader>> future = getGeneratedSpaceInvaderList(noSpaceInvaders);

        Optional<GameSession> optionalGameSession = gameSessionRepository.findById(sessionId);
        if(optionalGameSession.isEmpty()){
            return null;
        }

        GameSession gameSession = optionalGameSession.get();

        List<SpaceInvader> spaceInvadersList = gameSession.getSpaceInvaders();
        spaceInvadersList.addAll(future.get());
        gameSession.setSpaceInvaders(spaceInvadersList);
        gameSessionRepository.saveAndFlush(gameSession);

        return future.get();
    }


}
