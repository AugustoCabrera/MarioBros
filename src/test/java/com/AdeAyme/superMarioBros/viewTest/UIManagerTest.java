package com.AdeAyme.superMarioBros.viewTest;
import com.AdeAyme.superMarioBros.controller.GameEngine;
import com.AdeAyme.superMarioBros.view.UIManager;
import org.junit.Test;
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
}
