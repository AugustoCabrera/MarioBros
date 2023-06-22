package com.aDeAyme.superMarioBros.controllerTest;
import com.aDeAyme.superMarioBros.controller.ButtonAction;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.controller.GameStatus;
import org.junit.Test;
import static org.junit.Assert.*;
public class gameEngineTest {
    @Test
    public void receiveInputTest(){
        GameEngine engine = new GameEngine("GameEngineTest");
        engine.setGameStatus(GameStatus.START_SCREEN);
        engine.receiveInput(ButtonAction.SELECT);
        assertEquals(engine.getGameStatus(), GameStatus.MAP_SELECTION);
    }
}
