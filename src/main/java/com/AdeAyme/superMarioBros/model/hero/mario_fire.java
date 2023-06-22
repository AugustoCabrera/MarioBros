package com.aDeAyme.superMarioBros.model.hero;

import com.aDeAyme.superMarioBros.view.animation;
import com.aDeAyme.superMarioBros.view.image_loader;

import java.awt.image.BufferedImage;

public class mario_fire implements mario_form_all {

    private BufferedImage fireballStyle;
    private com.aDeAyme.superMarioBros.view.animation animation;

    public mario_fire(com.aDeAyme.superMarioBros.view.animation animation , mario mario) {
        mario.setDimension(48, 96);
        this.animation=animation;
        image_loader image_loader = new image_loader();
        BufferedImage fireball = image_loader.loadImage("/sprite.png");
        fireballStyle = image_loader.getSubImage(fireball, 3, 4, 24, 24);
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

    public mario_normal onTouchEnemy(image_loader image_loader, mario mario){

        BufferedImage[] leftFrames = image_loader.getLeftFrames(com.aDeAyme.superMarioBros.model.hero.mario.SMALL);
        BufferedImage[] rightFrames= image_loader.getRightFrames(com.aDeAyme.superMarioBros.model.hero.mario.SMALL);

        com.aDeAyme.superMarioBros.view.animation newAnimation = new animation(leftFrames, rightFrames);
        return new mario_normal(newAnimation, mario);

    }


    public fireball fire(boolean toRight, double x, double y) {
            return new fireball(x, y + 48, fireballStyle, toRight);
    }


}
