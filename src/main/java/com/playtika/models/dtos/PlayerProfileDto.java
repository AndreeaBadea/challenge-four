package com.playtika.models.dtos;

import com.playtika.models.Gender;

public class PlayerProfileDto {

    private String userName;
    private Gender gender;
    private int age;

    public String getUserName() {
        return userName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

}
