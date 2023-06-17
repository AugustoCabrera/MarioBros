package com.AdeAyme.superMarioBros.model.hero;

import com.AdeAyme.superMarioBros.view.Animation;
import com.AdeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;

public class MarioSuper implements MarioFormAll{

private Animation animation;

    public MarioSuper (Animation animation, Mario mario) {
            this.animation = animation;
            mario.setDimension(48, 96);
    }

    public BufferedImage getCurrentStyle(boolean toRight, boolean movingInX, boolean movingInY){
        //bufferedImage es la imagen con TODOS los estilos de mario
        BufferedImage style;

        if(movingInY && toRight){
            style = animation.getRightFrames()[0];
        }
        else if(movingInY){
            style = animation.getLeftFrames()[0];
        }
        else if(movingInX){
            style = animation.animate(5, toRight);
        }
        else {
            if(toRight){
                style = animation.getRightFrames()[1];
            }
            else
                style = animation.getLeftFrames()[1];
        }

        return style;

    }

    public MarioNormal onTouchEnemy(ImageLoader imageLoader, Mario mario){


        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames= imageLoader.getRightFrames(MarioForm.SMALL);

        Animation newAnimation = new Animation(leftFrames, rightFrames);

        return new MarioNormal(newAnimation, mario);

    }
}
