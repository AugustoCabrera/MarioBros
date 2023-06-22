package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.controller.Difficulty;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
public class GameObjectTest {
    //Utilizo algunos test de Mario y se crean objetos FireFlowr debido a que extienden de BoostItem
    @Test
    public void createGameObjectTest(){
       MarioTest mario=new MarioTest();
       mario.createMarioTest();
    }
    @Test
    public void updateLocationTest(){
        Mario mario = new Mario(0,0, new Difficulty());
        mario.setJumping(true);
        mario.setFalling(false);
        int initialVel=1;
        mario.setVelY(initialVel);
        //jumping==true
        mario.updateLocation();
        assertEquals(initialVel-0.38, mario.getVelY(), 0.0);
        assertEquals(-mario.getVelY(), mario.getY(), 0.0);
        double position=mario.getY();
        mario.setVelY(-initialVel);
        //jumping==true && velY <= 0
        mario.updateLocation();
        assertTrue(mario.isFalling());
        assertFalse(mario.isJumping());
        assertEquals(0, mario.getVelX(),0.0);
    }
    @Test
    public void setDimensionTest(){
        Mario mario = new Mario(0,0,new Difficulty());
        Dimension dim = new Dimension(60,60);
        mario.setDimension(dim);
        assertEquals(60, mario.getDimension().getHeight(),0.0);
        assertEquals(60, mario.getDimension().getWidth(),0.0);
    }
    @Test
    public void getGravityAccTest(){
        Mario mario = new Mario(0,0,new Difficulty());
        assertEquals(0.38, mario.getGravityAcc(),0.0);
    }
}
