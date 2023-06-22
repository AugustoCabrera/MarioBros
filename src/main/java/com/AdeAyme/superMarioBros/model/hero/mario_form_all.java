package com.aDeAyme.superMarioBros.model.hero;

import com.aDeAyme.superMarioBros.view.image_loader;

import java.awt.image.BufferedImage;

public interface mario_form_all {

    //agregar BallFire solo en IsFire

    public BufferedImage getCurrentStyle(boolean toRight, boolean movingInX, boolean movingInY);
    public mario_form_all onTouchEnemy(image_loader image_loader, mario mario);

  //  public




}
