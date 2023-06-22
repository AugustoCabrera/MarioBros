package com.aDeAyme.superMarioBros.model.enemy;
import com.aDeAyme.superMarioBros.model.GameObject;

import java.awt.image.BufferedImage;
//Clase abstracta de enemigo. Goomba y KoopaTroopa extiende de esta clase.

public abstract class Enemy extends GameObject {
    public Enemy(double x, double y, BufferedImage style) {
        super(x, y, style);
        setFalling(false);  //No pueden caer
        setJumping(false);  //No pueden saltar
    }
}
