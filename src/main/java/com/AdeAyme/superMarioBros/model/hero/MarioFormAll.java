package com.AdeAyme.superMarioBros.model.hero;

import com.AdeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;

public interface MarioFormAll {

    //agregar BallFire solo en IsFire

    public BufferedImage getCurrentStyle(boolean toRight, boolean movingInX, boolean movingInY);
    public MarioFormAll onTouchEnemy(ImageLoader imageLoader , Mario mario);

  //  public




}
