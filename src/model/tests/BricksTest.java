package model.tests;

import manager.GameEngine;
import manager.MapManager;
import model.brick.OrdinaryBrick;
import model.brick.SurpriseBrick;
import model.hero.Mario;
import model.prize.Coin;
import model.prize.Prize;
import org.junit.Test;

import static org.junit.Assert.*;

public class BricksTest {
    @Test
    public void revealTest(){
        GameEngine engine = new GameEngine();
        Mario mario = new Mario(0,0);
        MapManager manager= new MapManager();
        OrdinaryBrick ordinary=new OrdinaryBrick(0, 0, null);
        SurpriseBrick surprise=new SurpriseBrick(0, 0, null, (Prize) new Coin(0, 0, null, 3));
        assertNull(ordinary.reveal(engine));
        Prize prize= surprise.reveal(engine);
        assertNotSame(prize, null);
        assertSame(prize.getPoint(), 3);
    }

}
