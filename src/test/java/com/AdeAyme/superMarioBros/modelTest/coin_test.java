package com.aDeAyme.superMarioBros.modelTest;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.prize.coin;
import com.aDeAyme.superMarioBros.model.hero.mario;
import org.junit.Test;

import static org.junit.Assert.*;

public class coin_test {
    @Test
    public void createCoinTest(){
        coin coin =new coin(0,0,null,0);
        assertEquals(coin.getVelX(),0,0.0);
        assertEquals(coin.getVelY(),0,0.0);
        assertTrue(coin.isFalling());
        assertFalse(coin.isJumping());
    }
    @Test
    public void onTouchTest(){
        game_engine engine=new game_engine("CoinTest");
        mario mario = new mario(0, 0, null);
        coin coin = new coin(0, 0, null, 50);
        //acquired en false
        coin.onTouch(mario, engine);
        assertEquals(mario.getPoints(), 50);
        assertEquals(mario.getCoins(), 1);
        //acquired en true (no cambie nada)
        coin.onTouch(mario, engine);
        assertEquals(mario.getPoints(), 50);
        assertEquals(mario.getCoins(), 1);
    }
    @Test
    public void revealTest(){
        double pos = 10;
        double pos_mod= 5;
        coin coin = new coin(0, pos, null, 0); //comienza en posicion y=10
        coin.reveal();
        coin.updateLocation(); //actualiza y de manera que decrementa en 5
        assertEquals(coin.getY(), pos_mod, 0.0);
    }

    @Test
    public void getRevealBoundaryTest(){
        coin coin = new coin(0,0,null,0);
        assertEquals(-42, coin.getRevealBoundary(),0.0); // getY()=0 y getDimension().height()=42
    }

    @Test
    public void updateLocationTest(){
        coin coin = new coin(0,0,null,0);
        //reveal en false
        coin.updateLocation();
        assertEquals(0, coin.getY(),0.0);
        //reveal en true
        coin.reveal();
        coin.updateLocation();
        assertEquals(-5, coin.getY(), 0.0);
    }
}
