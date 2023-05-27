package model.tests;

import manager.GameEngine;
import model.hero.Mario;
import model.prize.FireFlower;
import org.junit.Test;
import static org.junit.Assert.*;

public class FireFlowerTest {
    @Test
    public void onTouchTest(){
        GameEngine engine = new GameEngine();
        Mario mario = new Mario(0, 0);
        FireFlower flower = new FireFlower(0, 0, null);
        flower.onTouch(mario, engine);
        assertSame(mario.getDimension().getWidth(), 48);
        assertSame(mario.getDimension().getHeight(), 96);
    }
}

