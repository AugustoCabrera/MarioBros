package com.AdeAyme.superMarioBros.modelTest;

import com.AdeAyme.superMarioBros.manager.Camera;
import com.AdeAyme.superMarioBros.manager.GameEngine;
import com.AdeAyme.superMarioBros.model.hero.Mario;
import org.junit.Test;
import static org.junit.Assert.*;
public class MarioTest {
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
        assertEquals(mario.getVelX(), 5, 0.0);
        assertTrue(mario.getToRight());
    }
    public void onTouchEnemy(){
          //en proceso
    }
    @Test
    public void resetTest(){
        GameEngine engine=new GameEngine("CoinTest");
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
}
