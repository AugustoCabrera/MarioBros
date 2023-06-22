package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.end_flag;
import org.junit.Test;
import static org.junit.Assert.*;
public class end_flag_test {

    @Test
    public void createEndFlagTest(){
        end_flag end_flag =new end_flag(0,0,null);
        assertEquals(end_flag.getVelX(),0,0.0);
        assertEquals(end_flag.getVelY(),0,0.0);
        assertTrue(end_flag.isFalling());
        assertFalse(end_flag.isJumping());
    }
    @Test
    public void updateLocationTest(){

        end_flag end_flag =new end_flag(0,0,null);
        end_flag.setDimension(48,48);
        end_flag.updateLocation();
        assertTrue(end_flag.isFalling());

        end_flag.setTouched(true);
        assertTrue(end_flag.isTouched());
        end_flag.updateLocation();
        assertTrue(end_flag.isFalling());

        end_flag.setY(600);
        end_flag.updateLocation();
        assertFalse(end_flag.isFalling());
        assertEquals(end_flag.getY(), 576 - end_flag.getDimension().getHeight(), 0.0);
    }
}
