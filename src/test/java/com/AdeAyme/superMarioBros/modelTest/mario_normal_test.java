package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.hero.mario_normal;
import org.junit.Test;
import static org.junit.Assert.*;

public class mario_normal_test {

    @Test
    public void createMarioNormalTest(){
        mario mario = new mario(0, 0, null);
        mario_normal mario_normal = new mario_normal(null, mario);
        assertEquals(mario.getDimension().getWidth(),48,0.0);
        assertEquals(mario.getDimension().getHeight(), 48,0.0);
    }
    @Test
    public void onTouchEnemyTest(){
        mario mario = new mario(0, 0, null);
        mario_normal mario_normal = new mario_normal(null, mario);
        assertSame(mario_normal.onTouchEnemy(null, mario), null);
    }

}
