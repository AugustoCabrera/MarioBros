package com.aDeAyme.superMarioBros.model.brick;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.GameObject;
import com.aDeAyme.superMarioBros.model.prize.Prize;

import java.awt.image.BufferedImage;

//Clase abstracta de los bloques existentes en el mapa
public abstract class Brick extends GameObject {
    private boolean breakable;      //Determina si se puede romper o no
    private boolean empty;      //Determina si esta vacio o no
    public Brick(double x, double y, BufferedImage style){
        super(x, y, style);
        setDimension(48, 48);
    }

    public boolean isBreakable() {
        return breakable;
    } //Determina si un bloque se puede romper o no

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }   //Establece si el bloque puede romperse o no

    public boolean isEmpty() {
        return empty;
    }   //Determina si un bloque esta vacio o no

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }   //Establece si el bloque esta vacio o no

    public Prize reveal(GameEngine engine){ return null;}   //Revela el premio de un bloque (se sobreescribe para el bloque sorpresa)

    public Prize getPrize() {
        return null;
    }   //Devuelve el premio de un bloque (se sobreescribe para el bloque sorpresa)
}
