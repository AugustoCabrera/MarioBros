package model.tests;
import manager.GameEngine;
import model.hero.Mario;
import model.prize.SuperMushroom;
import org.junit.Test;
import static org.junit.Assert.*;

public class SuperMushroomTest {
    @Test
    public void onTouchTest(){
        GameEngine engine = new GameEngine("CoinTest");
        Mario mario = new Mario(0, 0);
        SuperMushroom mushroom = new SuperMushroom(0, 0, null);
        mushroom.onTouch(mario, engine);
        assertSame(mario.getPoints(), 125);
        if(48!=mario.getDimension().getWidth()) System.out.print("AssertionError");
        if(96!=mario.getDimension().getHeight()) System.out.print("AssertionError");
        //assertSame(mario.getDimension().getWidth(), 48);
        //assertSame(mario.getDimension().getHeight(), 96);
    }
}
