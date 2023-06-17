package com.AdeAyme.superMarioBros.model.prize;

import com.AdeAyme.superMarioBros.manager.GameEngine;
import com.AdeAyme.superMarioBros.model.hero.Mario;
import com.AdeAyme.superMarioBros.model.hero.MarioSuper;
import com.AdeAyme.superMarioBros.model.hero.MarioForm;
import com.AdeAyme.superMarioBros.model.hero.MarioFormAll;
import com.AdeAyme.superMarioBros.view.Animation;
import com.AdeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;

public class SuperMushroom extends BoostItem implements MagicObject{

    public SuperMushroom(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(125);
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {

        mario.acquirePoints(getPoint());
        setChangeMarioForm(mario);
        engine.playSuperMushroom();
        }

    //borre el isFire(): if(!mario.getMarioForm().isFire()){

    @Override
    public void setChangeMarioForm(Mario mario) {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SUPER);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SUPER);

        Animation animation = new Animation(leftFrames, rightFrames);
        // MarioForm newForm = new MarioForm(animation, true, true); // new MarioFire

        MarioFormAll newForm =(MarioFormAll) new MarioSuper(animation, mario); // new MarioFire
        mario.setMarioForm(newForm);


    }
}
