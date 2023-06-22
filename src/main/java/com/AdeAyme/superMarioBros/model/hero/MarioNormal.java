package com.aDeAyme.superMarioBros.model.hero;
import com.aDeAyme.superMarioBros.view.Animation;
import com.aDeAyme.superMarioBros.view.ImageLoader;
import java.awt.image.BufferedImage;
//Forma de Mario normal (pequeño). Se establece cuando Mario comienza una partida o toca un enemigo

public class MarioNormal implements MarioFormAll {
    private Animation animation;    //Animacion de Mario en el mapa
    public MarioNormal(Animation animation, Mario mario){        //Establece la vista de MarioNormal
        mario.setDimension(48,48);
        this.animation = animation;
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
    public MarioFormAll onTouchEnemy(ImageLoader ImageLoader, Mario mario){ //Como al contacto con un enemigo un Mario con forma normal muere
         System.out.println("Error");                                       //esta muerte se maneja en onTouch() de la clase Mario
        return null;
    }
}
