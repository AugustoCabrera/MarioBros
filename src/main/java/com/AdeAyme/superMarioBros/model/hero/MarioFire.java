package com.aDeAyme.superMarioBros.model.hero;
import com.aDeAyme.superMarioBros.view.Animation;
import com.aDeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;

//Forma de Mario en fuego. Se establece cuando Mario toca una flor de fuego en la partida
public class MarioFire implements MarioFormAll {

    private BufferedImage fireballStyle;    //Imagen de la bola de fuego
    private Animation animation;            //Animacion de MarioFire

    public MarioFire(Animation animation , Mario mario) {       //Establece la vista de MarioFire
        mario.setDimension(48, 96);
        this.animation=animation;
        ImageLoader ImageLoader = new ImageLoader();
        BufferedImage fireball = ImageLoader.loadImage("/sprite.png");
        fireballStyle = ImageLoader.getSubImage(fireball, 3, 4, 24, 24);
    }

    public BufferedImage getCurrentStyle(boolean toRight, boolean movingInX, boolean movingInY){ //Cambia la vista de la animacion segun como se mueva en el mapa
        BufferedImage style;

        //con los metodos getRigthFrames obtengo las imagenes de la columna 1 (o sea movimiento a la derecha)
        //con los metodos getLeftFrames obtengo las imagenes de la columna 0 (o sea movimiento a la izquierda)

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

    public MarioNormal onTouchEnemy(ImageLoader ImageLoader, Mario mario){ //Cambia a MarioNormal cuando toca a un enemigo

        BufferedImage[] leftFrames = ImageLoader.getLeftFrames(Mario.SMALL);
        BufferedImage[] rightFrames= ImageLoader.getRightFrames(Mario.SMALL);

        Animation newAnimation = new Animation(leftFrames, rightFrames);
        return new MarioNormal(newAnimation, mario);
    }

    public Fireball fire(boolean toRight, double x, double y) {             //Dispara bolas de fuego
            return new Fireball(x, y + 48, fireballStyle, toRight);
    }
}
