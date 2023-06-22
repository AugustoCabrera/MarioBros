package com.aDeAyme.superMarioBros.modelTest;

import com.aDeAyme.superMarioBros.controller.camera;
import com.aDeAyme.superMarioBros.controller.difficulty;
import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.hero.mario_normal;
import com.aDeAyme.superMarioBros.model.prize.fire_flower;
import com.aDeAyme.superMarioBros.model.prize.super_mushroom;
import org.junit.Test;
import static org.junit.Assert.*;
public class mario_test {
    @Test
    public void createMarioTest(){
        mario mario = new mario(0,0,new difficulty());
        assertEquals(mario.getVelX(),0,0.0);
        assertEquals(mario.getVelY(),0,0.0);
        assertTrue(mario.isFalling());
        assertFalse(mario.isJumping());
        assertEquals(mario.getRemainingLives(), 3,0.0);
        assertEquals(mario.getCoins(), 0,0.0);
        assertEquals(mario.getPoints(), 0,0.0);
        assertTrue(mario.getMarioForm() instanceof mario_normal);
    }
    @Test
    public void jumpTest(){
        game_engine engine=new game_engine("MarioTest");
        mario mario= new mario(0, 0, null);
        mario.setFalling(false);
        mario.setJumping(false);
        mario.jump(engine);
        assertTrue(mario.isJumping());
        assertEquals(mario.getVelY(), 10, 0.0);
    }
    @Test
    public void moveTest(){
        camera camara = new camera();
        mario mario= new mario(0, 0, null);
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
        game_engine engine=new game_engine("MarioTest");
        difficulty difficulty= new difficulty();
        mario mario= new mario(0, 0, difficulty);
        //test para un marioNormal
        mario.onTouchEnemy(engine, difficulty);
        assertEquals(mario.getRemainingLives(), 2, 0.0);
        //test para un marioFire
        fire_flower flower = new fire_flower(0,0,null);
        flower.onTouch(mario, engine);
        mario.onTouchEnemy(engine, difficulty);
        assertTrue(mario.getMarioForm() instanceof mario_normal);
        //test para un marioSuper
        super_mushroom mushroom = new super_mushroom(0,0,null);
        mushroom.onTouch(mario, engine);
        mario.onTouchEnemy(engine, difficulty);
        assertTrue(mario.getMarioForm() instanceof mario_normal);
    }
    @Test
    public void resetTest(){
        game_engine engine=new game_engine("MarioTest");
        mario mario= new mario(40, 0, null);
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
        mario mario = new mario(0,0,new difficulty());
        assertNull(mario.fire());
        fire_flower flower = new fire_flower(0,0,null);
        flower.onTouch(mario, new game_engine("MarioTest"));
        assertEquals(48,mario.fire().getY(),0.0);
    }
}
