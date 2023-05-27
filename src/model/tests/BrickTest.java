package model.Tests;
import static org.junit.Assert.*;
import manager.GameEngine;
import manager.MapManager;
import model.Map;
import model.brick.OrdinaryBrick;
import model.brick.SurpriseBrick;
import model.prize.Coin;
import model.prize.Prize;
import view.Animation;
import view.ImageLoader;
import model.Map;
import model.hero.Mario;
import java.awt.image.BufferedImage;

public abstract class BrickTest {
    
    public void revealTest(){
        GameEngine engine = new GameEngine();
        OrdinaryBrick ordinary=new OrdinaryBrick(0, 0, null);
        SurpriseBrick suprise=new SurpriseBrick(0, 0, null, new Coin(0, 0, null, 3));
        assertEquals(ordinary.reveal(engine), null);
        Prize prize= (Coin) suprise.reveal(engine);
        assertNotSame(prize, null);
        assertEquals(prize, 3);
    }
}
