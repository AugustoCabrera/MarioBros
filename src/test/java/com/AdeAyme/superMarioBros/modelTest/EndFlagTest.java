package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.EndFlag;
import org.junit.Test;
import static org.junit.Assert.*;
public class EndFlagTest {

    @Test
    public void createEndFlagTest(){
        EndFlag EndFlag =new EndFlag(0,0,null);
        assertEquals(EndFlag.getVelX(),0,0.0);
        assertEquals(EndFlag.getVelY(),0,0.0);
        assertTrue(EndFlag.isFalling());
        assertFalse(EndFlag.isJumping());
    }
    @Test
    public void updateLocationTest(){

        EndFlag EndFlag =new EndFlag(0,0,null);
        EndFlag.setDimension(48,48);
        EndFlag.updateLocation();
        assertTrue(EndFlag.isFalling());

        EndFlag.setTouched(true);
        assertTrue(EndFlag.isTouched());
        EndFlag.updateLocation();
        assertTrue(EndFlag.isFalling());

        EndFlag.setY(600);
        EndFlag.updateLocation();
        assertFalse(EndFlag.isFalling());
        assertEquals(EndFlag.getY(), 576 - EndFlag.getDimension().getHeight(), 0.0);
    }
}
