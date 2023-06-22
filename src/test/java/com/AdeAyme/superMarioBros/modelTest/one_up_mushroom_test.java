package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.controller.difficulty;
import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.prize.one_up_mushroom;
import org.junit.Test;
import static org.junit.Assert.*;
public class one_up_mushroom_test {
     @Test
    public void createOneUpMushroomTest(){
         one_up_mushroom upMushroom = new one_up_mushroom(0,0,null);
         assertEquals(upMushroom.getDimension().getHeight(), 48, 0.0);
         assertEquals(upMushroom.getDimension().getWidth(), 48, 0.0);
         assertEquals(upMushroom.getPoint(), 200, 0.0);
     }
      @Test
    public void onTouchTest(){
          mario mario = new mario(0,0, new difficulty());
          one_up_mushroom upMushroom = new one_up_mushroom(0,0, null);
          int initialLives =mario.getRemainingLives();
          upMushroom.onTouch(mario, new game_engine("OneUpMushroomTest"));
          assertEquals(mario.getPoints(), upMushroom.getPoint());
          assertEquals(mario.getRemainingLives(), initialLives+1);
      }
}
