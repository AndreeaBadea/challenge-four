package com.playtika.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(name="players")
@Table(name="players")
public class PlayerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;


    private int age;


    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;


    @Column(name = "is_subscriber")
    private boolean isNewsletterSubscriber;

    @OneToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL, mappedBy = "playerProfile")
    private List<GameSession> gameSessions = new ArrayList<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isNewsletterSubscriber() {
        return isNewsletterSubscriber;
    }

    public void setIsNewsletterSubscriber(boolean isNewsletterSubscriber) {
        this.isNewsletterSubscriber = isNewsletterSubscriber;
    }

    public List<GameSession> getGameSessions() {
        return gameSessions;
    }

    public void setGameSessions(List<GameSession> gameSessions) {
        this.gameSessions = gameSessions;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
