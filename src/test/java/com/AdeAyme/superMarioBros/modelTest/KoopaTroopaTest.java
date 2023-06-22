package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.enemy.KoopaTroopa;
import org.junit.Test;
import static org.junit.Assert.*;
public class KoopaTroopaTest {
    @Test
    public void updateTest(){ //Prueba del update del Observer
        KoopaTroopa koopa_troopa = new KoopaTroopa(0,0,null);
        koopa_troopa.update();
        double speed= koopa_troopa.getVelX();
        assertEquals(4, speed, 0.0);
    }
}
