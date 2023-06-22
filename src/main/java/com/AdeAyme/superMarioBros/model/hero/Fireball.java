package com.aDeAyme.superMarioBros.model.hero;


import com.aDeAyme.superMarioBros.model.game_object;

import java.awt.image.BufferedImage;

public class fireball extends game_object {

    public fireball(double x, double y, BufferedImage style, boolean toRight) {
        super(x, y, style);
        setDimension(24, 24);
        setFalling(false);
        setJumping(false);
        setVelX(10);

        if(!toRight)
            setVelX(-5);
    }
}
