package com.aDeAyme.superMarioBros.model.enemy;
import com.aDeAyme.superMarioBros.controller.Difficulty;
import com.aDeAyme.superMarioBros.controller.Observer;

import java.awt.*;
import java.awt.image.BufferedImage;
//Enemigo KoopaTroopa del mapa
public class KoopaTroopa extends Enemy implements Observer {
    private BufferedImage rightImage;   //Imagen de KoopaTroopa
    private Difficulty difficulty;      //Objeto dificulta del videojuego

    public KoopaTroopa(double x, double y, BufferedImage style) {
        super(x, y, style);
        setVelX(3);                     //Comienzan con una velocidad inicial de 3
    }

    @Override
    public void draw(Graphics g){       //Dibuja la imagen de KoopaTroopa en el mapa
        if(getVelX() > 0){
            g.drawImage(rightImage, (int)getX(), (int)getY(), null);
        }
        else
            super.draw(g);
    }

    @Override
    public void update() {              //Actualiza la velocidad de KoopaTroopa segun la dificultad del videojuego
        if(getVelX()<0){
            double speed = getVelX() - 1;   //Si KoopaTroopa se mueve hacia la izquierda, 'aumenta' la velocidad con -1
            setVelX(speed);

        }
        else{
            double speed = getVelX() + 1;   //Si KoopaTroopa se mueve hacia la derecha, aumenta la velocidad con 1
            setVelX(speed);
        }
    }

    public void setRightImage(BufferedImage rightImage) {
        this.rightImage = rightImage;
    }   //Determina la imagen de KoopaTroopa
}
