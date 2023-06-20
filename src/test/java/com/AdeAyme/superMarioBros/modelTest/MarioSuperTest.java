package com.AdeAyme.superMarioBros.modelTest;
import com.AdeAyme.superMarioBros.model.hero.Mario;
import com.AdeAyme.superMarioBros.model.hero.MarioNormal;
import com.AdeAyme.superMarioBros.model.hero.MarioSuper;
import com.AdeAyme.superMarioBros.view.ImageLoader;
import org.junit.Test;
import static org.junit.Assert.*;

public class MarioSuperTest {
    @Test
    public void createMarioSuperTest(){
        Mario mario = new Mario(0, 0, null);
        MarioSuper marioSuper= new MarioSuper(null, mario);
        assertEquals(mario.getDimension().getWidth(),48,0.0);
        assertEquals(mario.getDimension().getHeight(), 96,0.0);
    }

    @Test
    public void onTouchEnemyTest(){
        Mario mario = new Mario(0, 0, null);
        MarioSuper marioSuper= new MarioSuper(null, mario);
        assertTrue(marioSuper.onTouchEnemy(new ImageLoader(), mario) instanceof MarioNormal);
    }
}
