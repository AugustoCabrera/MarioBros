package com.AdeAyme.superMarioBros.model.hero;

import com.AdeAyme.superMarioBros.manager.Camera;
import com.AdeAyme.superMarioBros.manager.GameEngine;
import com.AdeAyme.superMarioBros.model.Difficulty;
import com.AdeAyme.superMarioBros.view.Animation;
import com.AdeAyme.superMarioBros.model.GameObject;
import com.AdeAyme.superMarioBros.view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mario extends GameObject{

    private int remainingLives;
    private int coins;
    private int points;
    private double invincibilityTimer;
    private MarioFormAll marioForm;
    private boolean toRight = true;
    private Difficulty difficulty;
    public Mario(double x, double y , Difficulty difficulty){
        super(x, y, null);
        setDimension(48,48);

        remainingLives = 3;
        points = 0;
        coins = 0;
        invincibilityTimer = 0;

        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);

        Animation animation = new Animation(leftFrames, rightFrames);
        marioForm= new MarioNormal(animation, this);
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

    public void jump(GameEngine engine) {
        if(!isJumping() && !isFalling()){
            setJumping(true);
            setVelY(10);
            engine.playJump();
        }
    }

    public void move(boolean toRight, Camera camera) {
        if(toRight){
            setVelX(5);
        }
        else if(camera.getX() < getX()){
            setVelX(-5);
        }

        this.toRight = toRight;
    }

    public boolean onTouchEnemy(GameEngine engine , Difficulty difficulty){

        if(marioForm instanceof MarioNormal)
        {
            remainingLives--;
            engine.playMarioDies();
            return true;
        }

        else{
            engine.shakeCamera();
            marioForm = (MarioFormAll) marioForm.onTouchEnemy(engine.getImageLoader(), this);
            return false;
        }
    }

    public Fireball fire(){

        if(marioForm instanceof  MarioFire){

               MarioFire marioFire= (MarioFire) marioForm;

                 return marioFire.fire(toRight, getX(), getY());
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

    public MarioFormAll getMarioForm() {
        return marioForm;
    }

    public void setMarioForm(MarioFormAll marioForm) {
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
