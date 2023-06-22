package com.aDeAyme.superMarioBros.model.hero;

import com.aDeAyme.superMarioBros.controller.camera;
import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.view.animation;
import com.aDeAyme.superMarioBros.model.game_object;
import com.aDeAyme.superMarioBros.view.image_loader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class mario extends game_object {

    public static final int SMALL = 0, SUPER = 1, FIRE = 2;  //son enteros que se utilizan para delimitar que tipo de columna estoy trabajando en los marios
    private int remainingLives;
    private int coins;
    private int points;
    private double invincibilityTimer;
    private mario_form_all marioForm;
    private boolean toRight = true;
    private com.aDeAyme.superMarioBros.controller.difficulty difficulty;
    public mario(double x, double y , com.aDeAyme.superMarioBros.controller.difficulty difficulty){
        super(x, y, null);
        setDimension(48,48);

        remainingLives = 3;
        points = 0;
        coins = 0;
        invincibilityTimer = 0;

        image_loader image_loader = new image_loader();
        BufferedImage[] leftFrames = image_loader.getLeftFrames(SMALL);
        BufferedImage[] rightFrames = image_loader.getRightFrames(SMALL);

        animation animation = new animation(leftFrames, rightFrames);
        marioForm= new mario_normal(animation, this);
        setStyle(marioForm.getCurrentStyle(toRight, false, false));
        this.difficulty = difficulty;
    }

    @Override
    public void draw(Graphics g){
        boolean movingInX = (getVelX() != 0);
        boolean movingInY = (getVelY() != 0);

        setStyle(marioForm.getCurrentStyle(toRight, movingInX, movingInY));

        super.draw(g);
    }

    public void jump(game_engine engine) {
        if(!isJumping() && !isFalling()){
            setJumping(true);
            setVelY(10);
            engine.playJump();
        }
    }

    public void move(boolean toRight, camera camera) {
        if(toRight){
            setVelX(5);
        }
        else if(camera.getX() < getX()){
            setVelX(-5);
        }

        this.toRight = toRight;
    }

    public boolean onTouchEnemy(game_engine engine , com.aDeAyme.superMarioBros.controller.difficulty difficulty){

        if(marioForm instanceof mario_normal)
        {
            remainingLives--;
            engine.playMarioDies();
            return true;
        }

        else{
            engine.shakeCamera();
            marioForm = marioForm.onTouchEnemy(engine.getImageLoader(), this);
            return false;
        }
    }

    public fireball fire(){

        if(marioForm instanceof mario_fire){

               mario_fire mario_fire = (mario_fire) marioForm;

                 return mario_fire.fire(toRight, getX(), getY());
        }
        else{
                  return null;
    }
    }


    public void acquireCoin() {
        coins++;
    }

    public void acquirePoints(int point){

        points = points + point;

        if(points > 50){
            difficulty.setDifficulty();
        }


    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    public int getPoints() {
        return points;
    }

    public int getCoins() {
        return coins;
    }

    public mario_form_all getMarioForm() {
        return marioForm;
    }

    public void setMarioForm(mario_form_all marioForm) {
        this.marioForm = marioForm;
    }

    public boolean getToRight() {
        return toRight;
    }

    public void resetLocation() {
        setVelX(0);
        setVelY(0);
        setX(50);
        setJumping(false);
        setFalling(true);
    }

}
