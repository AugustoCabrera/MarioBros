package com.AdeAyme.superMarioBros.model.enemy;

import com.AdeAyme.superMarioBros.model.Difficulty;
import com.AdeAyme.superMarioBros.model.Observer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class KoopaTroopa extends Enemy implements Observer {

    private BufferedImage rightImage;
    private Difficulty difficulty;

    public KoopaTroopa(double x, double y, BufferedImage style) {
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

        double speed = getVelX() + 3;

        setVelX(speed);
    }

    public void setRightImage(BufferedImage rightImage) {
        this.rightImage = rightImage;
    }
}
