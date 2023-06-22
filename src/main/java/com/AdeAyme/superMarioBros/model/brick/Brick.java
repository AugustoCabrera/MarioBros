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
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Prize reveal(GameEngine engine){ return null;}

    public Prize getPrize() {
        return null;
    }
}
