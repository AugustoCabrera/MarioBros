package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.hero.mario_fire;
import com.aDeAyme.superMarioBros.model.hero.mario_normal;
import com.aDeAyme.superMarioBros.view.image_loader;
import org.junit.Test;
import static org.junit.Assert.*;

public class mario_fire_test {
    @Test
    public void createMarioFireTest(){
        mario mario = new mario(0,0,null);
        mario_fire mario_fire =new mario_fire(null, mario);
        assertEquals(mario.getDimension().getWidth(),48,0.0);
        assertEquals(mario.getDimension().getHeight(), 96,0.0);
    }

    @Test
    public void onTouchEnemyTest(){
        mario mario = new mario(0,0,null);
        mario_fire mario_fire =new mario_fire(null, mario);
        assertTrue(mario_fire.onTouchEnemy(new image_loader(),mario) instanceof mario_normal);
    }
}
