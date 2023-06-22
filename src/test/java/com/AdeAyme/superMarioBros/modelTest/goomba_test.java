package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.enemy.goomba;
import org.junit.Test;

import static org.junit.Assert.*;

public class goomba_test {
    @Test
    public void updateTest(){ //Prueba del update del Observer
        goomba goomba = new goomba(0,0,null);
        goomba.update();
        double speed=goomba.getVelX();
        assertEquals(4, speed, 0.0);
    }

}
