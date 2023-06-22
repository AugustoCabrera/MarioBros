package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.hero.MarioNormal;
import com.aDeAyme.superMarioBros.model.hero.MarioSuper;
import com.aDeAyme.superMarioBros.view.ImageLoader;
import org.junit.Test;
import static org.junit.Assert.*;

public class MarioSuperTest {
    @Test
    public void createMarioSuperTest(){
        Mario mario = new Mario(0, 0, null);
        MarioSuper MarioSuper = new MarioSuper(null, mario);
        assertEquals(mario.getDimension().getWidth(),48,0.0);
        assertEquals(mario.getDimension().getHeight(), 96,0.0);
    }

    @Test
    public void onTouchEnemyTest(){
        Mario mario = new Mario(0, 0, null);
        MarioSuper MarioSuper = new MarioSuper(null, mario);
        assertTrue(MarioSuper.onTouchEnemy(new ImageLoader(), mario) instanceof MarioNormal);
    }
}
