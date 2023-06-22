package com.aDeAyme.superMarioBros.model.prize;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.GameObject;
import com.aDeAyme.superMarioBros.model.hero.Mario;

import java.awt.*;
import java.awt.image.BufferedImage;
//Objeto moneda del mapa

public class Coin extends GameObject implements Prize {
    private int point;      //Punto que contiene cada moneda
    private boolean revealed, acquired = false;     //Establece si fueron o no revelado los puntos y si han sumado a Mario
    private int revealBoundary; //Rectángulo o una forma que define el área dentro de la cual el Mario puede detectar, recoger o interactuar con la moneda

    public Coin(double x, double y, BufferedImage style, int point){
        super(x, y, style);
        this.point = point;
        revealed = false;
        setDimension(30, 42);
        revealBoundary = (int)getY() - getDimension().height;
    }

    @Override
    public int getPoint() {
        return point;
    }       //Devuelve los puntos que contiene la moneda

    @Override
    public void reveal() {
        revealed = true;
    }       //Establece que la moneda ya fue revelada (tomada)

    @Override
    public void onTouch(Mario mario, GameEngine engine) {       //Establece que Mario tomo la moneda
        if(!acquired){
            acquired = true;
            mario.acquirePoints(point);         //Incrementa los puntos de Mario
            mario.acquireCoin();
            engine.playCoin();
        }
    }

    @Override
    public void updateLocation(){       //Actualiza la localizacion de la moneda en el mapa segun si fue o no tomada
        if(revealed){
            setY(getY() - 5);
        }
    }

    @Override
    public void draw(Graphics g){       //Dibuja la moneda en el mapa
        if(revealed){
            g.drawImage(getStyle(), (int)getX(), (int)getY(), null);
        }
    }

    public int getRevealBoundary() {
        return revealBoundary;
    }       //Devuelve los limites de contacto con la moneda
}
