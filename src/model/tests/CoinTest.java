package model.testModel;

import manager.GameEngine;
import model.prize.Coin;
import model.hero.Mario;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoinTest {
    @Test
    public void onTouchTest(){
        GameEngine engine=new GameEngine("CoinTest");
        Mario mario = new Mario(0, 0, null);
        Coin coin = new Coin(0, 0, null, 50);
        coin.onTouch(mario, engine);
        assertEquals(mario.getPoints(), 50);
        assertEquals(mario.getCoins(), 1);
    }
    @Test
    public void revealTest(){
        double pos = 10;
        double pos_mod= 5;
        Coin coin = new Coin(0, pos, null, 0); //comienza en posicion y=10
        coin.reveal();
        coin.updateLocation(); //actualiza y de manera que decrementa en 5
        assertEquals(coin.getY(), pos_mod, 0.0);
    }
}
