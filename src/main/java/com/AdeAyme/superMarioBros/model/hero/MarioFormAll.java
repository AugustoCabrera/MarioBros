package com.aDeAyme.superMarioBros.model.hero;
import com.aDeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;

//Interfaz de comportamientos del patron Strategy. La implementan todas las formas de Mario en el videojuego (MarioFire, MarioNormal y MarioSuper)
public interface MarioFormAll {
    public BufferedImage getCurrentStyle(boolean toRight, boolean movingInX, boolean movingInY);    //Determina la animacion segun como se mueva Mario
    public MarioFormAll onTouchEnemy(ImageLoader ImageLoader, Mario mario);     //Determina el cambio de forma segun la forma de Mario actual
}
