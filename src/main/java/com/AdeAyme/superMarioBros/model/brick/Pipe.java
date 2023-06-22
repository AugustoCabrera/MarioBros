package com.aDeAyme.superMarioBros.model.brick;
import java.awt.image.BufferedImage;
//Tubos del mapa

public class Pipe extends Brick { //Se construyen como un bloque
    public Pipe(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(false);    //No puede romperse
        setEmpty(true);         //Esta vacio
        setDimension(96, 96);
    }
}
