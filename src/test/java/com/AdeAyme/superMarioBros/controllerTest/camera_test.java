package com.aDeAyme.superMarioBros.controllerTest;
import com.aDeAyme.superMarioBros.controller.camera;
import org.junit.Test;
import static org.junit.Assert.*;
public class camera_test {
    @Test
    public void moveCamTest(){
        camera camera = new camera();
        camera.moveCam(20,20);
        assertEquals(20, camera.getX(),0.0);
        assertEquals(20, camera.getY(),0.0);
        camera.shakeCamera();
        camera.moveCam(20,20);
        assertEquals(24, camera.getX(),0.0);
    }
    @Test
    public void setAndGetYTest(){
        camera camera = new camera();
        camera.setY(50);
        assertEquals(50, camera.getY(),0.0);
    }
}
