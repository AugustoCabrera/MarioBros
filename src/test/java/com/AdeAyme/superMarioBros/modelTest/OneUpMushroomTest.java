package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.controller.Difficulty;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.prize.OneUpMushroom;
import org.junit.Test;
import static org.junit.Assert.*;
public class OneUpMushroomTest {
     @Test
    public void createOneUpMushroomTest(){
         OneUpMushroom upMushroom = new OneUpMushroom(0,0,null);
         assertEquals(upMushroom.getDimension().getHeight(), 48, 0.0);
         assertEquals(upMushroom.getDimension().getWidth(), 48, 0.0);
         assertEquals(upMushroom.getPoint(), 200, 0.0);
     }
      @Test
    public void onTouchTest(){
          Mario mario = new Mario(0,0, new Difficulty());
          OneUpMushroom upMushroom = new OneUpMushroom(0,0, null);
          int initialLives =mario.getRemainingLives();
          upMushroom.onTouch(mario, new GameEngine("OneUpMushroomTest"));
          assertEquals(mario.getPoints(), upMushroom.getPoint());
          assertEquals(mario.getRemainingLives(), initialLives+1);
      }
}
