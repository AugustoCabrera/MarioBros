package com.aDeAyme.superMarioBros.model.hero;
import com.aDeAyme.superMarioBros.view.Animation;
import com.aDeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;

//Forma de Mario super. Se establece cuando Mario toca un hongo rojo
public class MarioSuper implements MarioFormAll {
private Animation animation;        //Animacion de MarioSuper

    public MarioSuper(Animation animation, Mario mario) {       //Establece la vista de MarioSuper
            this.animation = animation;
            mario.setDimension(48, 96);
    }

    public BufferedImage getCurrentStyle(boolean toRight, boolean movingInX, boolean movingInY){    //Cambia la vista de la animacion segun como se mueva en el mapa

        //con los metodos getRigthFrames obtengo las imagenes de la columna 1 (o sea movimiento a la derecha)
        //con los metodos getLeftFrames obtengo las imagenes de la columna 0 (o sea movimiento a la izquierda)

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

    public MarioNormal onTouchEnemy(ImageLoader ImageLoader, Mario mario){      //Cambia a MarioNormal cuando toca a un enemigo

        BufferedImage[] leftFrames = ImageLoader.getLeftFrames(Mario.SMALL);
        BufferedImage[] rightFrames= ImageLoader.getRightFrames(Mario.SMALL);

        Animation newAnimation = new Animation(leftFrames, rightFrames);

        return new MarioNormal(newAnimation, mario);
    }
}
