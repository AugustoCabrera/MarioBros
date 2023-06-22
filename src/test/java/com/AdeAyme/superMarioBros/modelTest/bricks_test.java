package com.aDeAyme.superMarioBros.modelTest;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.brick.ground_brick;
import com.aDeAyme.superMarioBros.model.brick.ordinary_brick;
import com.aDeAyme.superMarioBros.model.brick.surprise_brick;
import com.aDeAyme.superMarioBros.model.prize.coin;
import com.aDeAyme.superMarioBros.model.prize.prize;
import com.aDeAyme.superMarioBros.model.prize.super_mushroom;
import org.junit.Test;

import static org.junit.Assert.*;

public class bricks_test {
    @Test
    public void revealTest(){
        game_engine engine = new game_engine("BrickTest");
        //reveal de OrdinaryBrick
        ordinary_brick ordinary=new ordinary_brick(0, 0, null); //Creo brick ordinario
        Object prizeOrdinary = ordinary.reveal(engine); // como es un bloque ordinario no tiene sorpresa
        assertNull(prizeOrdinary); //verifico si me da alguna sorpresa (para Mario Normal)
        super_mushroom mushroom =new super_mushroom(0,0,null);
        mushroom.onTouch(engine.getMapManager().getMario(), engine);
        assertNull(ordinary.reveal(engine)); //para Mario Super
        assertTrue(ordinary.isBreakable());
        assertEquals(-27, ordinary.getX(), 0.0);
        assertEquals(-27, ordinary.getY(), 0.0);

        //reveal de SurpriseBrick
        surprise_brick surprise=new surprise_brick(0, 0, null, (prize) new coin(0, 0, null, 3));// Creo brick sorpresa
        prize prizeS= surprise.reveal(engine); // Obtengo la sorpresa en el bloque sorpresa
        assertNotSame(prizeS, null); // verifico que no sea null
        assertEquals(prizeS.getPoint(), 3); // verifico que valga lo que definido

        //reveal de GroundBrick
        ground_brick ground=new ground_brick(0,0,null); //creo un brick piso
        assertNull(ground.reveal(engine)); //verifico que no revele nada
    }
    @Test
    public void getPrizeTest(){
        surprise_brick surprise=new surprise_brick(0, 0, null, (prize) new coin(0, 0, null, 3));
        assertEquals(3, surprise.getPrize().getPoint(),0.0);
        ordinary_brick ordinary=new ordinary_brick(0, 0, null); //Creo brick ordinario
        assertNull(ordinary.getPrize());
        ground_brick ground=new ground_brick(0,0,null);
        assertNull(ground.getPrize());
    }
    @Test
    public void isBreakableTest(){
        ordinary_brick ordinary=new ordinary_brick(0, 0, null); //Creo brick ordinario
        assertTrue(ordinary.isBreakable());
        surprise_brick surprise=new surprise_brick(0, 0, null, (prize) new coin(0, 0, null, 3));// Creo brick sorpresa
        assertFalse(surprise.isBreakable());
        ground_brick ground=new ground_brick(0,0,null);
        assertFalse(ground.isBreakable());
    }
    @Test
    public void isEmptyTest(){
        ordinary_brick ordinary=new ordinary_brick(0, 0, null); //Creo brick ordinario
        assertTrue(ordinary.isEmpty());
        surprise_brick surprise=new surprise_brick(0, 0, null, (prize) new coin(0, 0, null, 3));// Creo brick sorpresa
        assertFalse(surprise.isEmpty());
        ground_brick ground=new ground_brick(0,0,null);
        assertTrue(ground.isEmpty());
    }

    @Test
    public void getFramesTest(){
        ordinary_brick ordinary=new ordinary_brick(0, 0, null); //Creo brick ordinario
        assertEquals(4,ordinary.getFrames(),0.0);
    }
    @Test
    public void animateTest(){
        ordinary_brick ordinary=new ordinary_brick(0, 0, null); //Creo brick ordinario
        ordinary.animate();
        assertEquals(4, ordinary.getFrames(),0.0);
    }


}
