package com.playtika.controllers;


import com.playtika.config.GameSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/info")
public class GameSettingsController {

    @Autowired
    private GameSettings gameSettings;


    @GetMapping
    public Map<String, Object> getInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("version", gameSettings.getVersion());
        map.put("description", gameSettings.getDescription());
        map.put("devName", gameSettings.getDevName());
        map.put("maximumNumberOfPlayers", gameSettings.getMaxNoPlayers());
        map.put("lastRun", gameSettings.getLastRunTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return map;
    }

    @GetMapping("/version")
    public Map<String, Object> getVersion(){
        Map<String, Object> map = new HashMap<>();
        map.put("version", gameSettings.getVersion());
        return map;
    }



}
