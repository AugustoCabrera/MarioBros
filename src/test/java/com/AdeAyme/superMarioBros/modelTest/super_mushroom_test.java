package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.controller.difficulty;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.prize.super_mushroom;
import org.junit.Test;
import static org.junit.Assert.*;

public class super_mushroom_test {
    @Test
    public void createSuperMushroomTest(){
        super_mushroom mushroom =new super_mushroom(0,0,null);
        assertEquals(mushroom.getDimension().getHeight(), 48, 0.0);
        assertEquals(mushroom.getDimension().getWidth(), 48, 0.0);
        assertEquals(mushroom.getPoint(), 125);
    }
    @Test
    public void onTouchTest(){
        game_engine engine = new game_engine("CoinTest");
        mario mario = new mario(0, 0, new difficulty());
        super_mushroom mushroom = new super_mushroom(0, 0, null);
        mushroom.onTouch(mario, engine);
        assertEquals(mario.getPoints(), 125);
        assertEquals(mario.getDimension().getWidth(), 48, 0.0);
        assertEquals(mario.getDimension().getHeight(), 96, 0.0);
    }
}
