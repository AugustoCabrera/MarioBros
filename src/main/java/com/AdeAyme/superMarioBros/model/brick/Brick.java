package com.AdeAyme.superMarioBros.model.brick;

import com.AdeAyme.superMarioBros.manager.GameEngine;
import com.AdeAyme.superMarioBros.model.GameObject;
import com.AdeAyme.superMarioBros.model.Map;
import com.AdeAyme.superMarioBros.model.prize.Prize;

import java.awt.image.BufferedImage;

public abstract class Brick extends GameObject{

    private boolean breakable;

    private boolean empty;

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
