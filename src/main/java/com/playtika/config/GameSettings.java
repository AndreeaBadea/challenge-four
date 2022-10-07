package com.playtika.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Component
@PropertySource("classpath:space.properties")
@RequestMapping("api/info/version")
public class GameSettings implements InfoContributor {

    @Value("${service.version}")
    private String version;

    @Value("${service.description}")
    private String description;

    @Value("${service.dev.name}")
    private String devName;

    @Value("${service.players.maxNumber}")
    private long maxNoPlayers;

    private LocalDateTime lastRunTime;


    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getDevName() {
        return devName;
    }

    public long getMaxNoPlayers() {
        return maxNoPlayers;
    }

    public LocalDateTime getLastRunTime() {
        return lastRunTime;
    }


    @Override
    public void contribute(Info.Builder builder) {
        HashMap<String, Object> infoMap = new HashMap<>();
        infoMap.put("version", version);
        infoMap.put("description", description);
        infoMap.put("dev-name", devName);
        infoMap.put("max-num-players", maxNoPlayers);
        infoMap.put("last-run", lastRunTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @ReadOperation
    public Map<String, Object> showVersion(){
        Map<String, Object> versionMap = new HashMap<>();
        versionMap.put("version", version);
        return versionMap;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void handlerAppReadyEvent(){
        lastRunTime = LocalDateTime.now();
    }


}
