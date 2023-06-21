package com.AdeAyme.superMarioBros.modelTest;
import com.AdeAyme.superMarioBros.model.enemy.Goomba;
import com.AdeAyme.superMarioBros.model.enemy.KoopaTroopa;
import org.junit.Test;
import static org.junit.Assert.*;
public class KoopaTroopaTest {
    @Test
    public void updateTest(){ //Prueba del update del Observer
        KoopaTroopa koopaTroopa = new KoopaTroopa(0,0,null);
        koopaTroopa.update();
        double speed=koopaTroopa.getVelX();
        assertEquals(5, speed, 0.0);
    }
}
