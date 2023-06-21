package com.AdeAyme.superMarioBros.controllerTest;
import com.AdeAyme.superMarioBros.controller.Camera;
import org.junit.Test;
import static org.junit.Assert.*;
public class CameraTest {
    @Test
    public void moveCamTest(){
        Camera camera = new Camera();
        camera.moveCam(20,20);
        assertEquals(20, camera.getX(),0.0);
        assertEquals(20, camera.getY(),0.0);
        camera.shakeCamera();
        camera.moveCam(20,20);
        assertEquals(24, camera.getX(),0.0);
    }
    @Test
    public void setAndGetYTest(){
        Camera camera = new Camera();
        camera.setY(50);
        assertEquals(50, camera.getY(),0.0);
    }
}
