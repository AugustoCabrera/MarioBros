package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.prize.coin;
import com.aDeAyme.superMarioBros.model.prize.fire_flower;
import org.junit.Test;
import static org.junit.Assert.*;

public class boostItem_test {
    //Utilizo algunos test de Coin y se crean objetos FireFlowr debido a que extienden de BoostItem
    @Test
    public void createBoostItemTest(){
        coin_test coin = new coin_test();
        coin.createCoinTest();
    }
    @Test
    public void onTouchTest(){
        coin_test coin = new coin_test();
        coin.onTouchTest();
    }
    @Test
    public void getPointTest(){
        coin coin = new coin(0,0, null, 20);
        assertEquals(20, coin.getPoint(),0.0);
    }
    @Test
    public void updateLocationTest(){
        fire_flower flower = new fire_flower(7,0, null);
        flower.setVelX(2);
        flower.updateLocation();
        assertEquals(7, flower.getX(),0.0);
        flower.reveal();
        flower.updateLocation();
        assertEquals(9, flower.getX(),0.0);
    }
    @Test
    public void reveal(){
        fire_flower flower = new fire_flower(2,0, null);
        flower.reveal();
        assertEquals(-48, flower.getY(),0.0);
        flower.setVelX(2);
        flower.updateLocation();
        assertEquals(4, flower.getX(),0.0);
    }
}
