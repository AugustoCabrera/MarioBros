package com.aDeAyme.superMarioBros.model.prize;

import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.hero.MarioFire;
import com.aDeAyme.superMarioBros.model.hero.MarioFormAll;
import com.aDeAyme.superMarioBros.view.Animation;
import com.aDeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;

public class FireFlower extends BoostItem implements MagicObject {

    public FireFlower(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(150);
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {
        mario.acquirePoints(getPoint());
        setChangeMarioForm(mario);
        engine.playFireFlower();
    }

    @Override
    public void setChangeMarioForm(Mario mario) {
            ImageLoader ImageLoader = new ImageLoader();

            BufferedImage[] leftFrames = ImageLoader.getLeftFrames(Mario.FIRE);
            BufferedImage[] rightFrames = ImageLoader.getRightFrames(Mario.FIRE);

            Animation animation = new Animation(leftFrames, rightFrames);

            MarioFormAll newForm =new MarioFire(animation, mario);
            mario.setMarioForm(newForm);
    }

}
