package model.tests;

import manager.Camera;
import manager.GameEngine;
import model.hero.Mario;
import org.junit.Test;
import static org.junit.Assert.*;
public class MarioTest {
    @Test
    public void jumpTest(){
        GameEngine engine=new GameEngine();
        Mario mario= new Mario(0, 0);
        mario.jump(engine);
        assertTrue(mario.isJumping());
        assertSame(mario.getVelY(), 10);
    }
    @Test
    public void moveTest(){
        Camera camara = new Camera();
        Mario mario= new Mario(0, 0);
        mario.move(true, camara);
        assertSame(mario.getVelX(), 5);
        assertTrue(mario.getToRight());
    }
    public void onTouchEnemy(){

    }
    @Test
    public void resetTest(){
        GameEngine engine=new GameEngine();
        Mario mario= new Mario(40, 0);
        mario.setVelX(50);
        mario.setVelY(60);
        mario.jump(engine);
        mario.setFalling(true);
        mario.resetLocation();
        assertSame(mario.getVelX(), 0);
        assertSame(mario.getVelY(), 0);
        assertFalse(mario.isJumping());
        assertFalse(mario.isFalling());
        assertSame(mario.getX(), 50);

    }
}
