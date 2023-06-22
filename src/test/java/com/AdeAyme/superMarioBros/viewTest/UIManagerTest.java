package com.aDeAyme.superMarioBros.viewTest;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.view.MapSelection;
import com.aDeAyme.superMarioBros.view.UIManager;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
public class UIManagerTest {
    @Test
    public void creatUIManagerTest(){
        GameEngine engine = new GameEngine("UIManagerTest");
        UIManager manager = new UIManager(engine, 100,150 );
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
        UIManager manager =new UIManager(new GameEngine("UIManagerTest"),10,10);
        MapSelection selection =  new MapSelection();
        assertSame(manager.selectMapViaMouse(mouse), selection.selectMap(mouse));
    }
    @Test
    public void selectMapViaKeyboardTest(){
        UIManager manager =new UIManager(new GameEngine("UIManagerTest"),10,10);
        MapSelection selection =  new MapSelection();
        assertSame(manager.selectMapViaKeyboard(1), selection.selectMap(1));
    }
    @Test
    public void changeSelectedMapTest(){
        UIManager manager =new UIManager(new GameEngine("UIManagerTest"),10,10);
        MapSelection selection =  new MapSelection();
        assertSame(manager.changeSelectedMap(1,true), selection.changeSelectedMap(1, true));
    }
}
