package model.prize;

import manager.GameEngine;
import model.hero.Mario;
import model.hero.MarioSuper;
import model.hero.MarioForm;
import model.hero.MarioFormAll;
import view.Animation;
import view.ImageLoader;

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
