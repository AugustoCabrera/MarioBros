package com.aDeAyme.superMarioBros.model;
import java.awt.*;
import java.awt.image.BufferedImage;
//Clase abstracta que establece el comportamiento de los objetos del videojuego

public abstract class GameObject {
    private double x, y;    //Posiciones en el eje cartesiano

    private double velX, velY;      //Velocidades en el eje cartesiano

    private Dimension dimension;    //Dimensiones del objeto (Altura y ancho)

    private BufferedImage style;    //Imagen del objeto

    private double gravityAcc;      //Accion gravitatoria en el videojuego

    private boolean falling, jumping;   //Determina si el objeto esta o no saltando o cayendo

    public GameObject(double x, double y, BufferedImage style){
        setLocation(x, y);
        setStyle(style);

        if(style != null){
            setDimension(style.getWidth(), style.getHeight());
        }
        setVelX(0);
        setVelY(0);
        setGravityAcc(0.38);
        jumping = false;
        falling = true;
    }

    public void draw(Graphics g) {              //Dibuja el objeto en el mapa
        BufferedImage style = getStyle();

        if(style != null){
            g.drawImage(style, (int)x, (int)y, null);
        }
    }

    public void updateLocation() {              //Actualiza la localizacion del objeto en el mapa
        if(jumping && velY <= 0){
            jumping = false;
            falling = true;
        }
        else if(jumping){
            velY = velY - gravityAcc;
            y = y - velY;
        }
        if(falling){
            y = y + velY;
            velY = velY + gravityAcc;
        }

        x = x + velX;
    }

    public void setLocation(double x, double y) {   //Establece la localizacion del objeto en el mapa
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }       //Devuelve la localizacion horizontal del objeto en el mapa

    public void setX(double x) {
        this.x = x;
    } //Establece la localizacion horizontal del objeto en el mapa

    public double getY() {
        return y;
    }       //Devuelve la localizacion vertical del objeto en el mapa

    public void setY(double y) {
        this.y = y;
    }   //Establece la localizacion vertical del objeto en el mapa

    public Dimension getDimension(){
        return dimension;
    }   //Devuelve las dimensiones del objeto en el mapa

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }   //Establece las dimensiones del objeto en el mapa mediante el pase de un objeto Dimension

    public void setDimension(int width, int height){ this.dimension =  new Dimension(width, height); }  //Establece las dimensiones del objeto en el mapa mediante el pase del ancho y alto del objeto

    public BufferedImage getStyle() {
        return style;
    }//Devuelve la imagen del objeto

    public void setStyle(BufferedImage style) {
        this.style = style;
    }   //Establece la imagen del objeto

    public double getVelX() {
        return velX;
    }       //Devuelve la velocidad horizontal del objeto
    public void setVelX(double velX) {
        this.velX = velX;
    }   //Establece la velocidad horizontal del objeto
    public double getVelY() {
        return velY;
    }   //Devuelve la velocidad vertical del objeto
    public void setVelY(double velY) {
        this.velY = velY;
    }   //Establece la velocidad vertical del objeto
    public double getGravityAcc() {
        return gravityAcc;
    }   //Devuelve la accion gravitatoria del videojuego

    public void setGravityAcc(double gravityAcc) {
        this.gravityAcc = gravityAcc;
    } //Establce la accion gravitatoria del videojuego

    public Rectangle getTopBounds(){    //Devuelve los limites superiores de contacto del objeto
        return new Rectangle((int)x+dimension.width/6, (int)y, 2*dimension.width/3, dimension.height/2);
    }

    public Rectangle getBottomBounds(){     //Devuelve los limites inferiores de contacto del objeto
        return new Rectangle((int)x+dimension.width/6, (int)y + dimension.height/2, 2*dimension.width/3, dimension.height/2);
    }

    public Rectangle getLeftBounds(){       //Devuelve los limites izquierdos de contacto del objeto
        return new Rectangle((int)x, (int)y + dimension.height/4, dimension.width/4, dimension.height/2);
    }

    public Rectangle getRightBounds(){      //Devuelve los limites derechos de contacto del objeto
        return new Rectangle((int)x + 3*dimension.width/4, (int)y + dimension.height/4, dimension.width/4, dimension.height/2);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, dimension.width, dimension.height);
    } //Devuelve los limites de contacto con el objeto

    public boolean isFalling() {
        return falling;
    }   //Determina si el objeto esta o no cayendo

    public void setFalling(boolean falling) {
        this.falling = falling;
    }   //Establece si el objeto esta o no cayendo

    public boolean isJumping() {
        return jumping;
    }   //Determina si el objeto esta o no saltando

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }   //Establce si el objeto esta o no saltando
}
