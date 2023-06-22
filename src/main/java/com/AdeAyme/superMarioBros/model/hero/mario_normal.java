package com.aDeAyme.superMarioBros.model.hero;

import com.aDeAyme.superMarioBros.view.image_loader;
import java.awt.image.BufferedImage;

public class mario_normal implements mario_form_all {

    private com.aDeAyme.superMarioBros.view.animation animation;
    public mario_normal(com.aDeAyme.superMarioBros.view.animation animation, mario mario){
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
    public mario_form_all onTouchEnemy(image_loader image_loader, mario mario){ //PROBA UNITEST
         System.out.println("Error");
        return null;

    }

}
