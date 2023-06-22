package com.aDeAyme.superMarioBros.model.prize;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.hero.mario;

import java.awt.image.BufferedImage;

public class one_up_mushroom extends boostItem {

    public one_up_mushroom(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(200);
    }

    @Override
    public void onTouch(mario mario, game_engine engine) {
        mario.acquirePoints(getPoint());
        mario.setRemainingLives(mario.getRemainingLives() + 1);
        engine.playOneUp();
    }
}
