package com.AdeAyme.superMarioBros.controllerTest;
import com.AdeAyme.superMarioBros.controller.ButtonAction;
import com.AdeAyme.superMarioBros.controller.GameEngine;
import com.AdeAyme.superMarioBros.controller.GameStatus;
import com.AdeAyme.superMarioBros.view.MapSelection;
import org.junit.Test;
import static org.junit.Assert.*;
public class GameEngineTest {
    @Test
    public void receiveInputTest(){
        GameEngine engine = new GameEngine("GameEngineTest");
        engine.setGameStatus(GameStatus.START_SCREEN);
        engine.receiveInput(ButtonAction.SELECT);
        assertEquals(engine.getGameStatus(), GameStatus.MAP_SELECTION);
    }
}
