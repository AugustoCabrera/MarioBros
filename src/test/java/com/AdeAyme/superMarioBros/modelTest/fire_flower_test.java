package com.aDeAyme.superMarioBros.modelTest;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.prize.fire_flower;
import com.aDeAyme.superMarioBros.model.prize.one_up_mushroom;
import org.junit.Test;
import static org.junit.Assert.*;

public class fire_flower_test {
    @Test
    public void createFireFlowerTest(){
        one_up_mushroom upMushroom = new one_up_mushroom(0,0,null);
        assertEquals(upMushroom.getDimension().getHeight(), 48, 0.0);
        assertEquals(upMushroom.getDimension().getWidth(), 48, 0.0);
    }
    @Test
    public void onTouchTest(){
        game_engine engine = new game_engine("CoinTest"); //creo una instancia de juego
        mario mario = new mario(0, 0, null); // creo un mario que se va a jugar en esa instancia
        fire_flower flower = new fire_flower(0, 0, null); //creo una flor
        assertEquals(0, mario.getPoints());
        flower.setPoint(3);             // asigno que la flor de 3 puntos
        flower.onTouch(mario, engine);    //hago que mario agarre la flor
        assertEquals(48, mario.getDimension().getWidth(), 0.0); //verifico que el tamaño de mario aumento
        assertEquals(96, mario.getDimension().getHeight(), 0.0);
        assertEquals(3, mario.getPoints());  //verifico que mario obtuvo 3 puntos
    }
}

