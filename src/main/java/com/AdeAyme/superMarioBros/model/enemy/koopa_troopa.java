package com.aDeAyme.superMarioBros.model.enemy;

import com.aDeAyme.superMarioBros.controller.observer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class koopa_troopa extends enemy implements observer {

    private BufferedImage rightImage;
    private com.aDeAyme.superMarioBros.controller.difficulty difficulty;

    public koopa_troopa(double x, double y, BufferedImage style) {
        super(x, y, style);
        setVelX(3);
    }

    @Override
    public void draw(Graphics g){
        if(getVelX() > 0){
            g.drawImage(rightImage, (int)getX(), (int)getY(), null);
        }
        else
            super.draw(g);
    }

    @Override
    public void update() {
        if(getVelX()<0){
            double speed = getVelX() - 1;
            setVelX(speed);

        }
        else{
            double speed = getVelX() + 1;
            setVelX(speed);
        }
    }

    public void setRightImage(BufferedImage rightImage) {
        this.rightImage = rightImage;
    }
}
