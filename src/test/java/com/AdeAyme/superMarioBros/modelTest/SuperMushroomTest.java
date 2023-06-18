package com.AdeAyme.superMarioBros.modelTest;
import com.AdeAyme.superMarioBros.manager.GameEngine;
import com.AdeAyme.superMarioBros.model.Difficulty;
import com.AdeAyme.superMarioBros.model.hero.Mario;
import com.AdeAyme.superMarioBros.model.prize.SuperMushroom;
import org.junit.Test;
import static org.junit.Assert.*;

public class SuperMushroomTest {
    @Test
    public void onTouchTest(){
        GameEngine engine = new GameEngine("CoinTest");
        Mario mario = new Mario(0, 0, new Difficulty());
        SuperMushroom mushroom = new SuperMushroom(0, 0, null);
        mushroom.onTouch(mario, engine);
        assertEquals(mario.getPoints(), 125);
        assertEquals(mario.getDimension().getWidth(), 48, 0.0);
        assertEquals(mario.getDimension().getHeight(), 96, 0.0);
    }
}
