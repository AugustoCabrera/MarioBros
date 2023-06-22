package com.aDeAyme.superMarioBros.viewTest;
import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.view.map_selection;
import com.aDeAyme.superMarioBros.view.UI_manager;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
public class UI_manager_test {
    @Test
    public void creatUIManagerTest(){
        game_engine engine = new game_engine("UIManagerTest");
        UI_manager manager = new UI_manager(engine, 100,150 );
        assertEquals(manager.getPreferredSize().getWidth(),100,0.0);
        assertEquals(manager.getPreferredSize().getHeight(),150,0.0);
        assertEquals(manager.getMaximumSize().getWidth(),100,0.0);
        assertEquals(manager.getMaximumSize().getHeight(),150,0.0);
        assertEquals(manager.getMinimumSize().getWidth(),100,0.0);
        assertEquals(manager.getMinimumSize().getHeight(),150,0.0);
    }
    @Test
    public void selectMapViaMouseTest(){
        Point mouse = new Point(2,4);
        UI_manager manager =new UI_manager(new game_engine("UIManagerTest"),10,10);
        map_selection selection =  new map_selection();
        assertSame(manager.selectMapViaMouse(mouse), selection.selectMap(mouse));
    }
    @Test
    public void selectMapViaKeyboardTest(){
        UI_manager manager =new UI_manager(new game_engine("UIManagerTest"),10,10);
        map_selection selection =  new map_selection();
        assertSame(manager.selectMapViaKeyboard(1), selection.selectMap(1));
    }
    @Test
    public void changeSelectedMapTest(){
        UI_manager manager =new UI_manager(new game_engine("UIManagerTest"),10,10);
        map_selection selection =  new map_selection();
        assertSame(manager.changeSelectedMap(1,true), selection.changeSelectedMap(1, true));
    }
}
