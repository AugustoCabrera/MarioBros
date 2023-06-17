package com.AdeAyme.superMarioBros.model.prize;

import com.AdeAyme.superMarioBros.manager.GameEngine;
import com.AdeAyme.superMarioBros.model.hero.Mario;
import com.AdeAyme.superMarioBros.model.hero.MarioFire;
import com.AdeAyme.superMarioBros.model.hero.MarioForm;
import com.AdeAyme.superMarioBros.model.hero.MarioFormAll;
import com.AdeAyme.superMarioBros.view.Animation;
import com.AdeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;

public class FireFlower extends BoostItem implements MagicObject{

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

    //borre el isFire(): if(!mario.getMarioForm().isFire()){

    @Override
    public void setChangeMarioForm(Mario mario) {
            ImageLoader imageLoader = new ImageLoader();

            BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.FIRE);
            BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.FIRE);

            Animation animation = new Animation(leftFrames, rightFrames);
           // MarioForm newForm = new MarioForm(animation, true, true); // new MarioFire

            MarioFormAll newForm =(MarioFormAll) new MarioFire(animation, mario); // new MarioFire
            mario.setMarioForm(newForm);


    }

    @Override
    public void updateLocation(){}

}
