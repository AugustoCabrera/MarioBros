package com.AdeAyme.superMarioBros.modelTest;
import com.AdeAyme.superMarioBros.model.hero.Fireball;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
public class FireballTest {
    @Test
    public void createFireballTest(){
        Fireball fireball =new Fireball(0,0,null,true); //posicion en x e y, imagen y si va a la derecha (parametros)
        assertEquals(fireball.getDimension().getHeight(), 24, 0.0);
        assertEquals(fireball.getDimension().getWidth(), 24, 0.0);
        assertFalse(fireball.isFalling());
        assertFalse(fireball.isJumping());
        assertEquals(fireball.getVelX(), 10,0.0);
    }
}
