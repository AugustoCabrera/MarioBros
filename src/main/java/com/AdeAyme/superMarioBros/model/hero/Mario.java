package com.aDeAyme.superMarioBros.model.hero;
import com.aDeAyme.superMarioBros.controller.Camera;
import com.aDeAyme.superMarioBros.controller.Difficulty;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.view.Animation;
import com.aDeAyme.superMarioBros.model.GameObject;
import com.aDeAyme.superMarioBros.view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
//Objeto Mario. Aqui se hace una administracion de los puntos, vidas, monedas, dimensiones, formas, animacion e imagen

public class Mario extends GameObject {

    public static final int SMALL = 0, SUPER = 1, FIRE = 2;  //Son enteros que se utilizan para delimitar que tipo de columna estoy trabajando en los marios
    private int remainingLives;     //Vidas restantes de Mario en una partida
    private int coins;              //Monedas recolectadas en una partida
    private int points;             //Puntos adquiridos durante una partida
    private double invincibilityTimer;      //Timer sin uso
    private MarioFormAll marioForm;     //Forma actual de Mario
    private boolean toRight = true;     //Determina si Mario se mueve a la derecha o a la izquierda (ture o false respectivamente)
    private Difficulty difficulty;      //Dificultad actual de la partida

    public Mario(double x, double y , Difficulty difficulty){       //Establece la condicion inicial de Mario al comenzar la partida
        super(x, y, null);
        setDimension(48,48);

        remainingLives = 3;         //Tiene 3 vidas al comenzar el juego
        points = 0;
        coins = 0;
        invincibilityTimer = 0;

        ImageLoader ImageLoader = new ImageLoader();
        BufferedImage[] leftFrames = ImageLoader.getLeftFrames(SMALL);  //Imagen de Mario pequeño (MarioNormal)
        BufferedImage[] rightFrames = ImageLoader.getRightFrames(SMALL);

        Animation animation = new Animation(leftFrames, rightFrames);
        marioForm= new MarioNormal(animation, this);            //Empieza con la forma MarioNormal
        setStyle(marioForm.getCurrentStyle(toRight, false, false));
        this.difficulty = difficulty;
    }

    @Override
    public void draw(Graphics g){           //Dibuja a Mario en el mapa
        boolean movingInX = (getVelX() != 0);
        boolean movingInY = (getVelY() != 0);

        setStyle(marioForm.getCurrentStyle(toRight, movingInX, movingInY));

        super.draw(g);
    }

    public void jump(GameEngine engine) {       //Establece que Mario salte y reproduce el sonido del salto
        if(!isJumping() && !isFalling()){
            setJumping(true);
            setVelY(10);
            engine.playJump();
        }
    }

    public void move(boolean toRight, Camera camera) {      //Establece que Mario se mueva horizontalmente
        if(toRight){                        //Movimiento a la derecha con velocidad 5
            setVelX(5);
        }
        else if(camera.getX() < getX()){        //Movimiento a la izquierda con velocidad 5
            setVelX(-5);
        }

        this.toRight = toRight;
    }

    public boolean onTouchEnemy(GameEngine engine , Difficulty difficulty){ //Realiza el cambio de forma a partir del contacto con un enemigo
        if(marioForm instanceof MarioNormal){
            remainingLives--;
            engine.playMarioDies();     //Si Mario tiene forma MarioNormal, muere al contacto con un enemigo
            return true;
        }

        else{
            engine.shakeCamera();
            marioForm = marioForm.onTouchEnemy(engine.getImageLoader(), this);  //Si Mario tienen forma MarioSuper p MarioFire
            return false;                                                             //vuelve a Mario normal al contacto con un enemigo
        }
    }

    public Fireball fire(){     //Realiza el lanzamiento de bolas de fuego (Si Mario esta en su modo MarioFire)
        if(marioForm instanceof MarioFire){
               MarioFire MarioFire = (MarioFire) marioForm;
                 return MarioFire.fire(toRight, getX(), getY());
        }
        else{
                  return null;
    }
    }
    public void acquireCoin() {
        coins++;
    }       //Aumenta las monedas

    public void acquirePoints(int point){       //Aumenta los puntos

        points = points + point;

        if(points > 50){                     //Si Mario supera los 50 puntos, cada vez que gane puntos aumentara la dificultad (los enemigos se mueven mas rapido)
            difficulty.setDifficulty();
        }
    }

    public int getRemainingLives() {
        return remainingLives;
    }           //Devuelve las vidas restantes

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }  //Determina las vidas restantes

    public int getPoints() {
        return points;
    }           //Devuelve los puntos actuales

    public int getCoins() {
        return coins;
    }               //Devuelve las monedas actuales

    public MarioFormAll getMarioForm() {
        return marioForm;
    }   //Devuelve la forma actual de Mario

    public void setMarioForm(MarioFormAll marioForm) {
        this.marioForm = marioForm;
    }   //Determina la forma de Mario

    public boolean getToRight() {
        return toRight;
    }       //Determina si Mario se mueve a la izquierda o a la derecha

    public void resetLocation() {       //Reestablece la localizacion de Mario en el mapa
        setVelX(0);
        setVelY(0);
        setX(50);
        setJumping(false);
        setFalling(true);
    }

}
