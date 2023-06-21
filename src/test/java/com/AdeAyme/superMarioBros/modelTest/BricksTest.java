package com.AdeAyme.superMarioBros.modelTest;

import com.AdeAyme.superMarioBros.controller.Difficulty;
import com.AdeAyme.superMarioBros.controller.GameEngine;
import com.AdeAyme.superMarioBros.model.brick.GroundBrick;
import com.AdeAyme.superMarioBros.model.brick.OrdinaryBrick;
import com.AdeAyme.superMarioBros.model.brick.SurpriseBrick;
import com.AdeAyme.superMarioBros.model.hero.Mario;
import com.AdeAyme.superMarioBros.model.prize.Coin;
import com.AdeAyme.superMarioBros.model.prize.Prize;
import com.AdeAyme.superMarioBros.model.prize.SuperMushroom;
import org.junit.Test;

import static org.junit.Assert.*;

public class BricksTest {
    @Test
    public void revealTest(){
        GameEngine engine = new GameEngine("BrickTest");
        //reveal de OrdinaryBrick
        OrdinaryBrick ordinary=new OrdinaryBrick(0, 0, null); //Creo brick ordinario
        Object prizeOrdinary = ordinary.reveal(engine); // como es un bloque ordinario no tiene sorpresa
        assertNull(prizeOrdinary); //verifico si me da alguna sorpresa (para Mario Normal)
        SuperMushroom mushroom =new SuperMushroom(0,0,null);
        mushroom.onTouch(engine.getMapManager().getMario(), engine);
        assertNull(ordinary.reveal(engine)); //para Mario Super
        assertTrue(ordinary.isBreakable());
        assertEquals(-27, ordinary.getX(), 0.0);
        assertEquals(-27, ordinary.getY(), 0.0);

        //reveal de SurpriseBrick
        SurpriseBrick surprise=new SurpriseBrick(0, 0, null, (Prize) new Coin(0, 0, null, 3));// Creo brick sorpresa
        Prize prizeS= surprise.reveal(engine); // Obtengo la sorpresa en el bloque sorpresa
        assertNotSame(prizeS, null); // verifico que no sea null
        assertEquals(prizeS.getPoint(), 3); // verifico que valga lo que definido

        //reveal de GroundBrick
        GroundBrick ground=new GroundBrick(0,0,null); //creo un brick piso
        assertNull(ground.reveal(engine)); //verifico que no revele nada
    }
    @Test
    public void getPrizeTest(){
        SurpriseBrick surprise=new SurpriseBrick(0, 0, null, (Prize) new Coin(0, 0, null, 3));
        assertEquals(3, surprise.getPrize().getPoint(),0.0);
        OrdinaryBrick ordinary=new OrdinaryBrick(0, 0, null); //Creo brick ordinario
        assertNull(ordinary.getPrize());
        GroundBrick ground=new GroundBrick(0,0,null);
        assertNull(ground.getPrize());
    }
    @Test
    public void isBreakableTest(){
        OrdinaryBrick ordinary=new OrdinaryBrick(0, 0, null); //Creo brick ordinario
        assertTrue(ordinary.isBreakable());
        SurpriseBrick surprise=new SurpriseBrick(0, 0, null, (Prize) new Coin(0, 0, null, 3));// Creo brick sorpresa
        assertFalse(surprise.isBreakable());
        GroundBrick ground=new GroundBrick(0,0,null);
        assertFalse(ground.isBreakable());
    }
    @Test
    public void isEmptyTest(){
        OrdinaryBrick ordinary=new OrdinaryBrick(0, 0, null); //Creo brick ordinario
        assertTrue(ordinary.isEmpty());
        SurpriseBrick surprise=new SurpriseBrick(0, 0, null, (Prize) new Coin(0, 0, null, 3));// Creo brick sorpresa
        assertFalse(surprise.isEmpty());
        GroundBrick ground=new GroundBrick(0,0,null);
        assertTrue(ground.isEmpty());
    }

    @Test
    public void getFramesTest(){
        OrdinaryBrick ordinary=new OrdinaryBrick(0, 0, null); //Creo brick ordinario
        assertEquals(4,ordinary.getFrames(),0.0);
    }
    @Test
    public void animateTest(){
        OrdinaryBrick ordinary=new OrdinaryBrick(0, 0, null); //Creo brick ordinario
        ordinary.animate();
        assertEquals(4, ordinary.getFrames(),0.0);
    }


}
