package com.aDeAyme.superMarioBros.model.brick;
import java.awt.image.BufferedImage;
//Bloque tipo suelo del mapa

public class GroundBrick extends Brick {
    public GroundBrick(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(false);    //No es rompible
        setEmpty(true);         //Esta vacio
    }
}
