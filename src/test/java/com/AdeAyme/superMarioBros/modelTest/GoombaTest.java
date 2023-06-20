package com.AdeAyme.superMarioBros.modelTest;
import com.AdeAyme.superMarioBros.model.enemy.Goomba;
import org.junit.Test;
import static org.junit.Assert.*;

public class GoombaTest {
    @Test
    public void updateTest(){ //Prueba del update del Observer
        Goomba goomba = new Goomba(0,0,null);
        goomba.update();
        double speed=goomba.getVelX();
        assertEquals(6, speed, 0.0);
    }
}
