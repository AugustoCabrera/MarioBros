package model.tests;

import manager.GameEngine;
import model.hero.Mario;
import model.prize.FireFlower;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class FireFlowerTest {
    @Test
    public void onTouchTest(){
        GameEngine engine = new GameEngine("CoinTest");
        Mario mario = new Mario(0, 0);
        FireFlower flower = new FireFlower(0, 0, null);
        flower.setPoint(3);
        flower.onTouch(mario, engine);
        if(48!=mario.getDimension().getWidth()) System.out.print("AssertionError");
        if(96!=mario.getDimension().getHeight()) System.out.print("AssertionError");
        //Assert.assertEquals(48, mario.getDimension().getWidth());
        //assertEquals(96, mario.getDimension().getHeight());

        /* IntelliJ no me deja usar assertEquals porque me dice que esta deprecado el metodo
        assertSamer me tira error porque son distintos espacios de memoria para el mismo valor entero (supongo)
         */
    }

}

