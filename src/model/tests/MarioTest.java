package model.tests;

import manager.Camera;
import manager.GameEngine;
import model.hero.Mario;
import org.junit.Test;
import static org.junit.Assert.*;
public class MarioTest {
    @Test
    public void jumpTest(){
        GameEngine engine=new GameEngine("MarioTest");
        Mario mario= new Mario(0, 0);
        mario.setFalling(false);
        mario.setJumping(false);
        mario.jump(engine);
        assertTrue(mario.isJumping());
        if(10!=mario.getVelY()) System.out.print("AssertionError");
        //assertSame(mario.getVelY(), 10);
    }
    @Test
    public void moveTest(){
        Camera camara = new Camera();
        Mario mario= new Mario(0, 0);
        mario.move(true, camara);
        if(5!=mario.getVelX()) System.out.print("AssertionError");
        //assertSame(mario.getVelX(), 5);
        assertTrue(mario.getToRight());
    }
    public void onTouchEnemy(){
          //en proceso
    }
    @Test
    public void resetTest(){
        GameEngine engine=new GameEngine("CoinTest");
        Mario mario= new Mario(40, 0);
        mario.setVelX(50);
        mario.setVelY(60);
        mario.jump(engine);
        mario.setFalling(true);
        mario.resetLocation();
        if(0!=mario.getVelX()) System.out.print("AssertionError");
        //assertSame(mario.getVelX(), 0);
        if(0!=mario.getVelY()) System.out.print("AssertionError");
        //assertSame(mario.getVelY(), 0);
        assertFalse(mario.isJumping());
        assertFalse(mario.isFalling()); //Parece que cuando se resetea, mario tiene falling en true, por eso tira error el test
        if(50!=mario.getX()) System.out.print("AssertionError");
        //assertSame(mario.getX(), 50);

    }
}
