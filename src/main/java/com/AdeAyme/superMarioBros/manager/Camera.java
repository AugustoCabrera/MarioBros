package com.AdeAyme.superMarioBros.manager;

public class Camera {

    private double x, y;
    private int frameNumber;
    private boolean shaking;

    public Camera(){
        this.x = 0;
        this.y = 0;
        this.frameNumber = 25;
        this.shaking = false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void shakeCamera() {
        shaking = true; //Activo el Modo "Temblar"
        frameNumber = 60;
    }
    //metodo para mover la camara cuando se sale del Up Mushroom
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
