package com.aDeAyme.superMarioBros.model.prize;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.hero.MarioSuper;
import com.aDeAyme.superMarioBros.model.hero.MarioFormAll;
import com.aDeAyme.superMarioBros.view.Animation;
import com.aDeAyme.superMarioBros.view.ImageLoader;
import java.awt.image.BufferedImage;
//Objeto hongo rojo del mapa. Al tocarlo, cambia la forma de Mario (de MarioNormal a MarioSuper)
public class SuperMushroom extends BoostItem implements MagicObject {
    public SuperMushroom(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(125);      //Aumenta en 125 los puntos de Mario al tocarlo
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {   //Establece que el hongo fue tomado por Mario
        mario.acquirePoints(getPoint());
        setChangeMarioForm(mario);
        engine.playSuperMushroom();
        }

    @Override
    public void setChangeMarioForm(Mario mario) {       //Establece el cambio de la forma de Mario
        ImageLoader ImageLoader = new ImageLoader();

        BufferedImage[] leftFrames = ImageLoader.getLeftFrames(Mario.SUPER);
        BufferedImage[] rightFrames = ImageLoader.getRightFrames(Mario.SUPER);

        Animation animation = new Animation(leftFrames, rightFrames);

        MarioFormAll newForm = new MarioSuper(animation, mario);
        mario.setMarioForm(newForm);
    }
}
