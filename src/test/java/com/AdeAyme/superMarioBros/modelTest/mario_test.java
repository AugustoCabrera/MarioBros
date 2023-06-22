package com.aDeAyme.superMarioBros.modelTest;

import com.aDeAyme.superMarioBros.controller.Camera;
import com.aDeAyme.superMarioBros.controller.Difficulty;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.hero.MarioNormal;
import com.aDeAyme.superMarioBros.model.prize.FireFlower;
import com.aDeAyme.superMarioBros.model.prize.SuperMushroom;
import org.junit.Test;
import static org.junit.Assert.*;
public class mario_test {
    @Test
    public void createMarioTest(){
        Mario mario = new Mario(0,0,new Difficulty());
        assertEquals(mario.getVelX(),0,0.0);
        assertEquals(mario.getVelY(),0,0.0);
        assertTrue(mario.isFalling());
        assertFalse(mario.isJumping());
        assertEquals(mario.getRemainingLives(), 3,0.0);
        assertEquals(mario.getCoins(), 0,0.0);
        assertEquals(mario.getPoints(), 0,0.0);
        assertTrue(mario.getMarioForm() instanceof MarioNormal);
    }
    @Test
    public void jumpTest(){
        GameEngine engine=new GameEngine("MarioTest");
        Mario mario= new Mario(0, 0, null);
        mario.setFalling(false);
        mario.setJumping(false);
        mario.jump(engine);
        assertTrue(mario.isJumping());
        assertEquals(mario.getVelY(), 10, 0.0);
    }
    @Test
    public void moveTest(){
        Camera camara = new Camera();
        Mario mario= new Mario(0, 0, null);
        mario.move(true, camara);
        assertEquals(5, mario.getVelX(), 0.0);
        assertTrue(mario.getToRight());
        camara.setX(1);
        mario.setX(2);
        mario.move(false, camara);
        assertEquals(-5, mario.getVelX(), 0.0);
        assertFalse(mario.getToRight());
    }
    @Test
    public void onTouchEnemyTest(){
        GameEngine engine=new GameEngine("MarioTest");
        Difficulty difficulty= new Difficulty();
        Mario mario= new Mario(0, 0, difficulty);
        //test para un marioNormal
        mario.onTouchEnemy(engine, difficulty);
        assertEquals(mario.getRemainingLives(), 2, 0.0);
        //test para un marioFire
        FireFlower flower = new FireFlower(0,0,null);
        flower.onTouch(mario, engine);
        mario.onTouchEnemy(engine, difficulty);
        assertTrue(mario.getMarioForm() instanceof MarioNormal);
        //test para un marioSuper
        SuperMushroom mushroom = new SuperMushroom(0,0,null);
        mushroom.onTouch(mario, engine);
        mario.onTouchEnemy(engine, difficulty);
        assertTrue(mario.getMarioForm() instanceof MarioNormal);
    }
    @Test
    public void resetTest(){
        GameEngine engine=new GameEngine("MarioTest");
        Mario mario= new Mario(40, 0, null);
        mario.setVelX(50);
        mario.setVelY(60);
        mario.jump(engine);
        mario.setFalling(true);
        mario.resetLocation();
        assertEquals(mario.getVelX(), 0, 0.0);
        assertEquals(mario.getVelY(), 0, 0.0);
        assertFalse(mario.isJumping());
        assertTrue(mario.isFalling());
        assertEquals(mario.getX(), 50, 0.0);
    }

    @Test
    public void fireTest(){
        Mario mario = new Mario(0,0,new Difficulty());
        assertNull(mario.fire());
        FireFlower flower = new FireFlower(0,0,null);
        flower.onTouch(mario, new GameEngine("MarioTest"));
        assertEquals(48,mario.fire().getY(),0.0);
    }
}
