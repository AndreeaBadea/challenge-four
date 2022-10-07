package com.playtika.services;

import com.playtika.models.PlayerProfile;
import com.playtika.models.dtos.PlayerProfileDto;
import com.playtika.repositories.PlayerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerProfileService {

    @Autowired
    PlayerProfileRepository playerProfileRepository;

    public List<PlayerProfile> getPlayerProfiles(){
        return playerProfileRepository.findAll();
    }


    public Optional<PlayerProfile> getPlayerProfileById(long id){
        return playerProfileRepository.findById(id);
    }

    public List<PlayerProfile> getAllPlayerProfilesSortedByName(){
        return playerProfileRepository.findAllByOrderByUserNameAsc();
    }

    public List<PlayerProfile> getAllWithNoPlayedGamesBetween(int minValue, int maxValue){
        return playerProfileRepository.findAllByNumberOfPlayedGamesBetween(minValue, maxValue);
    }

    public List<PlayerProfile> getAllPlayersSortedByNoPlayedGamesDesc(){
        return playerProfileRepository.findAllByNumberOfPlayedGamesDesc();
    }

    public PlayerProfile addPlayerProfile(PlayerProfile playerProfile){
        return playerProfileRepository.saveAndFlush(playerProfile);
    }

    public boolean deletePlayerProfile(long id){
        if(!playerProfileRepository.existsById(id)){
            return false;
        }
        playerProfileRepository.deleteById(id);
        return true;
    }

    public PlayerProfile patchPlayerProfile(long id, PlayerProfileDto playerProfileDto){
        Optional<PlayerProfile> optionalPlayerProfile = playerProfileRepository.findById(id);
        if(optionalPlayerProfile.isEmpty()){
            return null;
        }
        PlayerProfile playerProfile = optionalPlayerProfile.get();

        playerProfile.setAge(playerProfileDto.getAge());
        playerProfile.setGender(playerProfileDto.getGender());
        playerProfile.setUserName(playerProfileDto.getUserName());

        return playerProfileRepository.saveAndFlush(playerProfile);
    }

    private boolean setNewsletterForPlayer(long id, boolean value){
        Optional<PlayerProfile> playerOptional = playerProfileRepository.findById(id);
        if(playerOptional.isEmpty()){
            return false;
        }
        PlayerProfile playerProfile = playerOptional.get();
        playerProfile.setIsNewsletterSubscriber(value);
        playerProfileRepository.saveAndFlush(playerProfile);
        return true;
    }

    public boolean registerNewsletterForPlayer(long id) {
        return setNewsletterForPlayer(id, true);
    }

    public boolean unregisterNewsletterForPlayer(long id) {
        return setNewsletterForPlayer(id, false);
    }



}
