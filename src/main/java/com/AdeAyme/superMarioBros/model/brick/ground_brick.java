package com.aDeAyme.superMarioBros.model.brick;

import java.awt.image.BufferedImage;

public class ground_brick extends brick {

    public ground_brick(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(false);
        setEmpty(true);
    }

}
