package com.aDeAyme.superMarioBros.model.brick;

import java.awt.image.BufferedImage;

public class pipe extends brick {

    public pipe(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(false);
        setEmpty(true);
        setDimension(96, 96);
    }
}
