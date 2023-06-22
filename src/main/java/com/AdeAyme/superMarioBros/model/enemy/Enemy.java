package com.aDeAyme.superMarioBros.model.enemy;

import com.aDeAyme.superMarioBros.model.GameObject;

import java.awt.image.BufferedImage;


public abstract class Enemy extends GameObject {

    public Enemy(double x, double y, BufferedImage style) {
        super(x, y, style);
        setFalling(false);
        setJumping(false);
    }
}
