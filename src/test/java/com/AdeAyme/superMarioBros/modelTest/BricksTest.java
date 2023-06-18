package com.AdeAyme.superMarioBros.modelTest;

import com.AdeAyme.superMarioBros.manager.GameEngine;
import com.AdeAyme.superMarioBros.model.brick.OrdinaryBrick;
import com.AdeAyme.superMarioBros.model.brick.SurpriseBrick;
import com.AdeAyme.superMarioBros.model.prize.Coin;
import com.AdeAyme.superMarioBros.model.prize.Prize;
import org.junit.Test;

import static org.junit.Assert.*;

public class BricksTest {
    @Test
    public void revealTest(){
        GameEngine engine = new GameEngine("BrickTest");
        //Mario mario = engine.getMapManager().getMario();
        OrdinaryBrick ordinary=new OrdinaryBrick(0, 0, null); //Creo brick ordinario
        SurpriseBrick surprise=new SurpriseBrick(0, 0, null, (Prize) new Coin(0, 0, null, 3));// Creo brick sorpresa
            Object prizeOrdinary = ordinary.reveal(engine); // como es un bloque ordinario no tiene sorpresa
            assertNull(prizeOrdinary); //verifico si me da alguna sorpresa, cosa
        Prize prizeS= surprise.reveal(engine); // Obtengo la sorpresa en el bloque sorpresa
        assertNotSame(prizeS, null); // verifico que no sea null
        assertEquals(prizeS.getPoint(), 3); // verifico que valga lo que definido
    }

}
