package com.aDeAyme.superMarioBros.model.prize;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.hero.Mario;

import java.awt.image.BufferedImage;
//Objeto hongo verde del mapa. Al tocarlo, le da una vida mas a Mario

public class OneUpMushroom extends BoostItem {
    public OneUpMushroom(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(200);          //Al tocarlo, aumenta 200 puntos a Mario
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {       //Establece que Mario tomo el hongo verde
        mario.acquirePoints(getPoint());
        mario.setRemainingLives(mario.getRemainingLives() + 1);     //Aumenta las vidas de Mario
        engine.playOneUp();
    }
}
