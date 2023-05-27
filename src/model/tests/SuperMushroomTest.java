package model.tests;
import manager.GameEngine;
import model.hero.Mario;
import model.prize.SuperMushroom;
import org.junit.Test;
import static org.junit.Assert.*;

public class SuperMushroomTest {
    @Test
    public void onTouchTest(){
        GameEngine engine = new GameEngine();
        Mario mario = new Mario(0, 0);
        SuperMushroom mushroom = new SuperMushroom(0, 0, null);
        mushroom.onTouch(mario, engine);
        assertSame(mario.getPoints(), 125);
        assertSame(mario.getDimension().getWidth(), 48);
        assertSame(mario.getDimension().getHeight(), 96);
    }
}
