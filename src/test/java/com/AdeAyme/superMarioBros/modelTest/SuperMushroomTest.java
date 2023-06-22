package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.controller.Difficulty;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.prize.SuperMushroom;
import org.junit.Test;
import static org.junit.Assert.*;

public class SuperMushroomTest {
    @Test
    public void createSuperMushroomTest(){
        SuperMushroom mushroom =new SuperMushroom(0,0,null);
        assertEquals(mushroom.getDimension().getHeight(), 48, 0.0);
        assertEquals(mushroom.getDimension().getWidth(), 48, 0.0);
        assertEquals(mushroom.getPoint(), 125);
    }
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
