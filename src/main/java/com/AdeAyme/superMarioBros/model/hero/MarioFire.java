package com.AdeAyme.superMarioBros.model.hero;

import com.AdeAyme.superMarioBros.view.Animation;
import com.AdeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;

public class MarioFire implements MarioFormAll {

    private BufferedImage fireballStyle;
    private Animation animation;

    public MarioFire(Animation animation , Mario mario) {
        mario.setDimension(48, 96);
        this.animation=animation;
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage fireball = imageLoader.loadImage("/sprite.png");
        fireballStyle = imageLoader.getSubImage(fireball, 3, 4, 24, 24);

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

    public MarioNormal onTouchEnemy(ImageLoader imageLoader , Mario mario){

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames= imageLoader.getRightFrames(MarioForm.SMALL);

        Animation newAnimation = new Animation(leftFrames, rightFrames);
        return new MarioNormal(newAnimation, mario);

    }


    public Fireball fire(boolean toRight, double x, double y) {

            return new Fireball(x, y + 48, fireballStyle, toRight);
    }


}
