package com.aDeAyme.superMarioBros.controllerTest;
import com.aDeAyme.superMarioBros.controller.button_action;
import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.controller.game_status;
import org.junit.Test;
import static org.junit.Assert.*;
public class game_engine_test {
    @Test
    public void receiveInputTest(){
        game_engine engine = new game_engine("GameEngineTest");
        engine.setGameStatus(game_status.START_SCREEN);
        engine.receiveInput(button_action.SELECT);
        assertEquals(engine.getGameStatus(), game_status.MAP_SELECTION);
    }
}
