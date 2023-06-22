package com.aDeAyme.superMarioBros.model.prize;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.game_object;
import com.aDeAyme.superMarioBros.model.hero.mario;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class boostItem extends game_object implements prize {

    private boolean revealed = false;
    private int point;

    public boostItem(double x, double y, BufferedImage style) {
        super(x, y, style);
        setDimension(48, 48);
    }

    public abstract void onTouch(mario mario, game_engine engine);

    @Override
    public int getPoint() {
        return point;
    }

    @Override
    public void updateLocation(){
        if(revealed){
            super.updateLocation();
        }
    }

    @Override
    public void draw(Graphics g){
        if(revealed){
            g.drawImage(getStyle(), (int)getX(), (int)getY(), null);
        }
    }

    @Override
    public void reveal(){
        setY(getY()-48);
        revealed = true;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
