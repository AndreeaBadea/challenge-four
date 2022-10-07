package com.playtika.controllers;

import com.playtika.models.PlayerProfile;
import com.playtika.models.dtos.PlayerProfileDto;
import com.playtika.services.PlayerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerProfileController {

    @Autowired
    PlayerProfileService playerProfileService;

    private final int MIN_AGE = 18;


    @GetMapping
    public ResponseEntity<List<PlayerProfile>> getPlayers(){
        List<PlayerProfile>  playerProfileList = playerProfileService.getPlayerProfiles();
        if(playerProfileList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerProfileList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerProfile> getPlayerProfileById(@PathVariable int id){
        Optional<PlayerProfile> optionalPlayerProfile = playerProfileService.getPlayerProfileById(id);
        if(optionalPlayerProfile.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(optionalPlayerProfile.get(), HttpStatus.OK);
    }

    @GetMapping("/sorted")
    public ResponseEntity<?> getProfilePlayersSortedByName(){
        List<PlayerProfile> players = playerProfileService.getAllPlayerProfilesSortedByName();
        if (players.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/halloffame")
    public ResponseEntity<?> hallOfFame(){
        List<PlayerProfile> players = playerProfileService.getAllPlayersSortedByNoPlayedGamesDesc();
        if (players.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }


    @GetMapping(params = {"min", "max"})
    public ResponseEntity<?> getPlayerProfilesPlayedBetween(@RequestParam int min, @RequestParam int max){
        List<PlayerProfile> players = playerProfileService.getAllWithNoPlayedGamesBetween(min, max);
        if (players.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<PlayerProfile> registerPlayer(@RequestBody PlayerProfile playerProfile){
        if(playerProfile.getAge() < MIN_AGE){
            return new ResponseEntity<>( HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(playerProfileService.addPlayerProfile(playerProfile), HttpStatus.OK);
    }

    @PatchMapping("/{id}/patch")
    public ResponseEntity<?> patchPlayer(@PathVariable long id, @RequestBody PlayerProfileDto playerProfileDto){
        if (playerProfileDto.getAge() < 18){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        PlayerProfile response = playerProfileService.patchPlayerProfile(id, playerProfileDto);
        if (response == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/unregister/{id}")
    public ResponseEntity<?> unregisterPlayer(@PathVariable long id){
        boolean response = playerProfileService.deletePlayerProfile(id);
        if (!response){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Player successfully unregistered!", HttpStatus.OK);
    }

    @PatchMapping("/{id}/newsletter/register")
    public ResponseEntity<?> registerNewsletterForPlayer(@PathVariable long id){
        boolean response = playerProfileService.registerNewsletterForPlayer(id);
        if (!response){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Subscribed to newsletter!", HttpStatus.OK);
    }


    @PatchMapping("{id}/newsletter/unregister")
    public ResponseEntity<?> unregisterNewsletterForPlayer(@PathVariable long id){
        boolean response = playerProfileService.unregisterNewsletterForPlayer(id);
        if (!response){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Unsubscribed from newsletter!", HttpStatus.OK);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> exceptionHandler(Exception e){
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }





}
