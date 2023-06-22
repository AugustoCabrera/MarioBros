package com.aDeAyme.superMarioBros.model.hero;
import com.aDeAyme.superMarioBros.model.GameObject;
import java.awt.image.BufferedImage;
//Objecto bola de fuego en el mapa

public class Fireball extends GameObject {
    public Fireball(double x, double y, BufferedImage style, boolean toRight) {
        super(x, y, style);
        setDimension(24, 24);
        setFalling(false);              //No puede caer
        setJumping(false);              //No puede saltar
        setVelX(10);                    //Velocidad hacia la derecha

        if(!toRight)
            setVelX(-5);                //Velocidad hacia la izquierda
    }
}
