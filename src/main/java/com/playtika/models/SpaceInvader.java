package com.playtika.models;

import javax.persistence.*;


@Entity(name="invaders")
@Table(name="invaders")
public class SpaceInvader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "x_coordinate")
    private int X;

    @Column(name = "y_coordinate")
    private int Y;

    public SpaceInvader(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public SpaceInvader() {

    }

    public SpaceInvader(SpaceInvader spaceInvader) {
        X = spaceInvader.getX();
        Y = spaceInvader.getY();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "SpaceInvader{" +
                "id=" + id +
                ", X=" + X +
                ", Y=" + Y +
                '}';
    }
}
