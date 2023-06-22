package com.aDeAyme.superMarioBros.model.prize;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.GameObject;
import com.aDeAyme.superMarioBros.model.hero.Mario;

import java.awt.*;
import java.awt.image.BufferedImage;
//Clase abstracta BoostItem que establece comportamientos de los elementos de Mario puede tocar y le da recompensas

public abstract class BoostItem extends GameObject implements Prize {
    private boolean revealed = false;       //Determina si se ha revelado o no el premio
    private int point;                      //Determina los puntos que se ganan al tocar el premio

    public BoostItem(double x, double y, BufferedImage style) {
        super(x, y, style);
        setDimension(48, 48);
    }

    public abstract void onTouch(Mario mario, GameEngine engine);   //Establece que fue tocado por Mario

    @Override
    public int getPoint() {
        return point;
    }       //Devuelve los puntos que contiene

    @Override
    public void updateLocation(){                 //Acualiza su localizacion en el mapa segun si fue o no revelado
        if(revealed){
            super.updateLocation();
        }
    }

    @Override
    public void draw(Graphics g){               //Dibuja el elemento en el mapa
        if(revealed){
            g.drawImage(getStyle(), (int)getX(), (int)getY(), null);
        }
    }

    @Override
    public void reveal(){                       //Establece que fue revelado el premio
        setY(getY()-48);
        revealed = true;
    }

    public void setPoint(int point) {
        this.point = point;
    }       //Estabelce los puntos que contiene el elemento
}
