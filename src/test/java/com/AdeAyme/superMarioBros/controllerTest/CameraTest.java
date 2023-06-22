package com.aDeAyme.superMarioBros.controllerTest;
import com.aDeAyme.superMarioBros.controller.Camera;
import org.junit.Test;
import static org.junit.Assert.*;
//Test de la clase Camera
public class CameraTest {
    @Test
    public void moveCamTest(){          //Testea el movimiento de la camara
        Camera camera = new Camera();
        camera.moveCam(20,20);
        assertEquals(20, camera.getX(),0.0);
        assertEquals(20, camera.getY(),0.0);
        camera.shakeCamera();
        camera.moveCam(20,20);
        assertEquals(24, camera.getX(),0.0);
    }
    @Test
    public void setAndGetYTest(){          //Testea el seteo y la devolucion de
        Camera camera = new Camera();
        camera.setY(50);
        assertEquals(50, camera.getY(),0.0);
    }
}
