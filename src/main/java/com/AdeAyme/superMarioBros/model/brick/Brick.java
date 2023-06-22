package com.aDeAyme.superMarioBros.model.brick;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.game_object;
import com.aDeAyme.superMarioBros.model.prize.prize;

import java.awt.image.BufferedImage;

public abstract class brick extends game_object {

    private boolean breakable;

    private boolean empty;

    public brick(double x, double y, BufferedImage style){
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

    public prize reveal(game_engine engine){ return null;}

    public prize getPrize() {
        return null;
    }
}
