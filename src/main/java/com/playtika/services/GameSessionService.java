package com.playtika.services;

import com.playtika.models.GameSession;
import com.playtika.models.PlayerProfile;
import com.playtika.repositories.GameSessionRepository;
import com.playtika.repositories.PlayerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameSessionService {

    @Autowired
    GameSessionRepository gameSessionRepository;

    @Autowired
    PlayerProfileRepository playerProfileRepository;

    public List<GameSession> getGameSessions(){
        return gameSessionRepository.findAll();
    }

    public Optional<GameSession> getGameSessionById(long id){
        return gameSessionRepository.findById(id);
    }

    public boolean deleteGameSession(long id){
        if(!gameSessionRepository.existsById(id)){
            return false;
        }
        GameSession gameSession = gameSessionRepository.findById(id).get();
        PlayerProfile playerProfile = gameSession.getPlayerProfile();
        playerProfile.getGameSessions().remove(gameSession);
        playerProfileRepository.saveAndFlush(playerProfile);
        gameSessionRepository.deleteById(id);
        return true;
    }

}
