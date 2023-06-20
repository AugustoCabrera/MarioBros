package com.AdeAyme.superMarioBros.modelTest;
import com.AdeAyme.superMarioBros.model.hero.Mario;
import com.AdeAyme.superMarioBros.model.hero.MarioNormal;
import org.junit.Test;
import static org.junit.Assert.*;

public class MarioNormalTest {

    @Test
    public void createMarioNormalTest(){
        Mario mario = new Mario(0, 0, null);
        MarioNormal marioNormal= new MarioNormal(null, mario);
        assertEquals(mario.getDimension().getWidth(),48,0.0);
        assertEquals(mario.getDimension().getHeight(), 48,0.0);
    }
    @Test
    public void onTouchEnemyTest(){
        Mario mario = new Mario(0, 0, null);
        MarioNormal marioNormal= new MarioNormal(null, mario);
        assertSame(marioNormal.onTouchEnemy(null, mario), null);
    }

}
