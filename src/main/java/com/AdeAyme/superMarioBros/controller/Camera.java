package com.aDeAyme.superMarioBros.controller;

//Realiza el seguimiento de Mario a lo largo del juego
public class Camera {

    private double x, y;     //Posiciones en eje cartesiano
    private int frameNumber; //Controla el número de fotogramas
    private boolean shaking; //Determina si la camara debe o no (true o false respectivamente) "sacudirse"

    public Camera(){
        this.x = 0;
        this.y = 0;
        this.frameNumber = 25;
        this.shaking = false;
    }

    public double getX() {
        return x;
    }           //Devuelve la posicion en el eje x

    public void setX(double x) {
        this.x = x;
    }   //Determina la posicion en el eje x

    public double getY() {
        return y;
    }           //Devuelve la posicion en el eje y

    public void setY(double y) {
        this.y = y;
    }   //Determina la posicion en el eje y

    //Activa el modo "sacudir"
    public void shakeCamera() {
        shaking = true;
        frameNumber = 60;
    }
    //Determina la dirección de movimiento horizontal durante la animacion de "sacudida" de la camara
    public void moveCam(double xAmount, double yAmount){
        if(shaking && frameNumber > 0){
            int direction = (frameNumber%2 == 0)? 1 : -1;
            x = x + 4 * direction;
            frameNumber--;
        }
        else{
            x = x + xAmount;
            y = y + yAmount;
        }

        if(frameNumber < 0)
            shaking = false;
    }
}
