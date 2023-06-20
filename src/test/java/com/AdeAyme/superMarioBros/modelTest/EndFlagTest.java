package com.AdeAyme.superMarioBros.modelTest;
import com.AdeAyme.superMarioBros.model.EndFlag;
import org.junit.Test;
import static org.junit.Assert.*;
public class EndFlagTest {

    @Test
    public void createEndFlagTest(){
        EndFlag endFlag =new EndFlag(0,0,null);
        assertEquals(endFlag.getVelX(),0,0.0);
        assertEquals(endFlag.getVelY(),0,0.0);
        assertTrue(endFlag.isFalling());
        assertFalse(endFlag.isJumping());
    }
    @Test
    public void updateLocationTest(){

        EndFlag endFlag =new EndFlag(0,0,null);
        endFlag.setDimension(48,48);
        endFlag.updateLocation();
        assertTrue(endFlag.isFalling());

        endFlag.setTouched(true);
        assertTrue(endFlag.isTouched());
        endFlag.updateLocation();
        assertTrue(endFlag.isFalling());

        endFlag.setY(600);
        endFlag.updateLocation();
        assertFalse(endFlag.isFalling());
        assertEquals(endFlag.getY(), 576 - endFlag.getDimension().getHeight(), 0.0);
    }
}
