package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.hero.mario_normal;
import com.aDeAyme.superMarioBros.model.hero.mario_super;
import com.aDeAyme.superMarioBros.view.image_loader;
import org.junit.Test;
import static org.junit.Assert.*;

public class mario_super_test {
    @Test
    public void createMarioSuperTest(){
        mario mario = new mario(0, 0, null);
        mario_super mario_super = new mario_super(null, mario);
        assertEquals(mario.getDimension().getWidth(),48,0.0);
        assertEquals(mario.getDimension().getHeight(), 96,0.0);
    }

    @Test
    public void onTouchEnemyTest(){
        mario mario = new mario(0, 0, null);
        mario_super mario_super = new mario_super(null, mario);
        assertTrue(mario_super.onTouchEnemy(new image_loader(), mario) instanceof mario_normal);
    }
}
