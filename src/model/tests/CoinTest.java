package model.testModel;

import manager.GameEngine;
import model.prize.Coin;
import model.hero.Mario;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoinTest {
    @Test
    public void onTouchTest(){
        GameEngine engine=new GameEngine();
        Mario mario = new Mario(0, 0);
        Coin coin = new Coin(0, 0, null, 50);
        coin.onTouch(mario, engine);
        assertEquals(mario.getPoints(), 50);
        assertEquals(mario.getCoins(), 1);
    }
    @Test
    public void revealTest(){
        Coin coin = new Coin(0, 10, null, 0); //comienza en posicion y=10
        coin.reveal();
        coin.updateLocation(); //actualiza y de manera que decrementa en 5
        assertSame(coin.getY(), 5);
    }
}
