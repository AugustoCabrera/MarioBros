package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.enemy.Goomba;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoombaTest {
    @Test
    public void updateTest(){ //Prueba del update del Observer
        Goomba goomba = new Goomba(0,0,null);
        goomba.update();
        double speed=goomba.getVelX();
        assertEquals(4, speed, 0.0);
    }

}
