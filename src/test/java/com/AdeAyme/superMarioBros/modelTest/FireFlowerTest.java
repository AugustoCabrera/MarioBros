package com.AdeAyme.superMarioBros.modelTest;

import com.AdeAyme.superMarioBros.controller.GameEngine;
import com.AdeAyme.superMarioBros.model.hero.Mario;
import com.AdeAyme.superMarioBros.model.prize.FireFlower;
import com.AdeAyme.superMarioBros.model.prize.OneUpMushroom;
import org.junit.Test;
import static org.junit.Assert.*;

public class FireFlowerTest {
    @Test
    public void createFireFlowerTest(){
        OneUpMushroom upMushroom = new OneUpMushroom(0,0,null);
        assertEquals(upMushroom.getDimension().getHeight(), 48, 0.0);
        assertEquals(upMushroom.getDimension().getWidth(), 48, 0.0);
    }
    @Test
    public void onTouchTest(){
        GameEngine engine = new GameEngine("CoinTest"); //creo una instancia de juego
        Mario mario = new Mario(0, 0, null); // creo un mario que se va a jugar en esa instancia
        FireFlower flower = new FireFlower(0, 0, null); //creo una flor
        assertEquals(0, mario.getPoints());
        flower.setPoint(3);             // asigno que la flor de 3 puntos
        flower.onTouch(mario, engine);    //hago que mario agarre la flor
        assertEquals(48, mario.getDimension().getWidth(), 0.0); //verifico que el tamaño de mario aumento
        assertEquals(96, mario.getDimension().getHeight(), 0.0);
        assertEquals(2, mario.getPoints());  //verifico que mario obtuvo 3 puntos
    }
}

