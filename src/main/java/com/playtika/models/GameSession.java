package com.playtika.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="sessions")
@Table(name="sessions")
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "difficulty_level")
    private int difficultyLevel;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @JsonIgnore
    private PlayerProfile playerProfile;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SpaceInvader> spaceInvaders;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public List<SpaceInvader> getSpaceInvaders() {
        return spaceInvaders;
    }

    public void setSpaceInvaders(List<SpaceInvader> spaceInvaders) {
        this.spaceInvaders = spaceInvaders;
    }
}
