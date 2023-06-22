package com.aDeAyme.superMarioBros.model.prize;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.hero.mario_super;
import com.aDeAyme.superMarioBros.model.hero.mario_form_all;
import com.aDeAyme.superMarioBros.view.animation;
import com.aDeAyme.superMarioBros.view.image_loader;

import java.awt.image.BufferedImage;

public class super_mushroom extends boostItem implements magic_object {

    public super_mushroom(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(125);
    }

    @Override
    public void onTouch(mario mario, game_engine engine) {

        mario.acquirePoints(getPoint());
        setChangeMarioForm(mario);
        engine.playSuperMushroom();
        }

    //borre el isFire(): if(!mario.getMarioForm().isFire()){

    @Override
    public void setChangeMarioForm(mario mario) {
        image_loader image_loader = new image_loader();

        BufferedImage[] leftFrames = image_loader.getLeftFrames(com.aDeAyme.superMarioBros.model.hero.mario.SUPER);
        BufferedImage[] rightFrames = image_loader.getRightFrames(com.aDeAyme.superMarioBros.model.hero.mario.SUPER);

        animation animation = new animation(leftFrames, rightFrames);

        mario_form_all newForm = new mario_super(animation, mario); // new MarioFire
        mario.setMarioForm(newForm);

    }
}
