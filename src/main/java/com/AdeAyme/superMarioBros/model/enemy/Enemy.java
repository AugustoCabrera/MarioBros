package com.aDeAyme.superMarioBros.model.enemy;

import com.aDeAyme.superMarioBros.model.game_object;

import java.awt.image.BufferedImage;


public abstract class enemy extends game_object {

    public enemy(double x, double y, BufferedImage style) {
        super(x, y, style);
        setFalling(false);
        setJumping(false);
    }
}
