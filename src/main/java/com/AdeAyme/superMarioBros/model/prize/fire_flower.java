package com.aDeAyme.superMarioBros.model.prize;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.hero.mario_fire;
import com.aDeAyme.superMarioBros.model.hero.mario_form_all;
import com.aDeAyme.superMarioBros.view.animation;
import com.aDeAyme.superMarioBros.view.image_loader;

import java.awt.image.BufferedImage;

public class fire_flower extends boostItem implements magic_object {

    public fire_flower(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(150);
    }

    @Override
    public void onTouch(mario mario, game_engine engine) {
        mario.acquirePoints(getPoint());
        setChangeMarioForm(mario);
        engine.playFireFlower();
    }

    //borre el isFire(): if(!mario.getMarioForm().isFire()){

    @Override
    public void setChangeMarioForm(mario mario) {
            image_loader image_loader = new image_loader();

            BufferedImage[] leftFrames = image_loader.getLeftFrames(com.aDeAyme.superMarioBros.model.hero.mario.FIRE);
            BufferedImage[] rightFrames = image_loader.getRightFrames(com.aDeAyme.superMarioBros.model.hero.mario.FIRE);

            animation animation = new animation(leftFrames, rightFrames);
           // MarioForm newForm = new MarioForm(animation, true, true); // new MarioFire

            mario_form_all newForm =(mario_form_all) new mario_fire(animation, mario); // new MarioFire
            mario.setMarioForm(newForm);


    }

}
