package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.enemy.koopa_troopa;
import org.junit.Test;
import static org.junit.Assert.*;
public class koopa_troopa_test {
    @Test
    public void updateTest(){ //Prueba del update del Observer
        koopa_troopa koopa_troopa = new koopa_troopa(0,0,null);
        koopa_troopa.update();
        double speed= koopa_troopa.getVelX();
        assertEquals(4, speed, 0.0);
    }
}
