package com.AdeAyme.superMarioBros.model.hero;

import com.AdeAyme.superMarioBros.view.Animation;
import com.AdeAyme.superMarioBros.view.ImageLoader;
import java.awt.image.BufferedImage;

public class MarioNormal implements MarioFormAll{

    private Animation animation;
    public MarioNormal(Animation animation, Mario mario){
        mario.setDimension(48,48);
        this.animation = animation;
    }

    public BufferedImage getCurrentStyle(boolean toRight, boolean movingInX, boolean movingInY){
        //bufferedImage es la imagen con TODOS los estilos de mario

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
    public MarioFormAll onTouchEnemy(ImageLoader imageLoader, Mario mario){ //PROBA UNITEST
         System.out.println("Error");
        return null;

    }

}
