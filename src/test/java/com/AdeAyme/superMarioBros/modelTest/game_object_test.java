package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.controller.difficulty;
import com.aDeAyme.superMarioBros.model.hero.mario;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
public class game_object_test {
    //Utilizo algunos test de Mario y se crean objetos FireFlowr debido a que extienden de BoostItem
    @Test
    public void createGameObjectTest(){
       mario_test mario=new mario_test();
       mario.createMarioTest();
    }
    @Test
    public void updateLocationTest(){
        mario mario = new mario(0,0, new difficulty());
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
        mario mario = new mario(0,0,new difficulty());
        Dimension dim = new Dimension(60,60);
        mario.setDimension(dim);
        assertEquals(60, mario.getDimension().getHeight(),0.0);
        assertEquals(60, mario.getDimension().getWidth(),0.0);
    }
    @Test
    public void getGravityAccTest(){
        mario mario = new mario(0,0,new difficulty());
        assertEquals(0.38, mario.getGravityAcc(),0.0);
    }
}
