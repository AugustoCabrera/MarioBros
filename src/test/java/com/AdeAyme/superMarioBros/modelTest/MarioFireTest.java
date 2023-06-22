package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.hero.MarioFire;
import com.aDeAyme.superMarioBros.model.hero.MarioNormal;
import com.aDeAyme.superMarioBros.view.ImageLoader;
import org.junit.Test;
import static org.junit.Assert.*;

public class MarioFireTest {
    @Test
    public void createMarioFireTest(){
        Mario mario = new Mario(0,0,null);
        MarioFire MarioFire =new MarioFire(null, mario);
        assertEquals(mario.getDimension().getWidth(),48,0.0);
        assertEquals(mario.getDimension().getHeight(), 96,0.0);
    }

    @Test
    public void onTouchEnemyTest(){
        Mario mario = new Mario(0,0,null);
        MarioFire MarioFire =new MarioFire(null, mario);
        assertTrue(MarioFire.onTouchEnemy(new ImageLoader(),mario) instanceof MarioNormal);
    }
}
