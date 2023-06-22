package com.aDeAyme.superMarioBros.model;
import java.awt.image.BufferedImage;
//Objeto bandera final

public class EndFlag extends GameObject {
    private boolean touched = false;        //Determina si la bandera fue tocada o no
    public EndFlag(double x, double y, BufferedImage style) {
        super(x, y, style);
    }

    @Override
    public void updateLocation() {      //Actualiza su localizacion segun fue tocada o no
        if(touched){
            if(getY() + getDimension().getHeight() >= 576){
                setFalling(false);
                setVelY(0);
                setY(576 - getDimension().getHeight());
            }
            super.updateLocation();
        }
    }

    public boolean isTouched() {
        return touched;
    }       //Determina si la bandera fue tocada o no

    public void setTouched(boolean touched) {
        this.touched = touched;
    } //Establece si la bandera fue tocada
}
