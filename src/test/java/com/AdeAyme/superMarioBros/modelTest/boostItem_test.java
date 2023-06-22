package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.model.prize.Coin;
import com.aDeAyme.superMarioBros.model.prize.FireFlower;
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
        Coin coin = new Coin(0,0, null, 20);
        assertEquals(20, coin.getPoint(),0.0);
    }
    @Test
    public void updateLocationTest(){
        FireFlower flower = new FireFlower(7,0, null);
        flower.setVelX(2);
        flower.updateLocation();
        assertEquals(7, flower.getX(),0.0);
        flower.reveal();
        flower.updateLocation();
        assertEquals(9, flower.getX(),0.0);
    }
    @Test
    public void reveal(){
        FireFlower flower = new FireFlower(2,0, null);
        flower.reveal();
        assertEquals(-48, flower.getY(),0.0);
        flower.setVelX(2);
        flower.updateLocation();
        assertEquals(4, flower.getX(),0.0);
    }
}
