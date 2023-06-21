package com.AdeAyme.superMarioBros.viewTest;

import com.AdeAyme.superMarioBros.view.MapSelection;
import com.AdeAyme.superMarioBros.view.MapSelectionItem;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
public class MapSelectionTest {
    @Test
    public void selectMapMouseTest(){
        MapSelection mapSelection = new MapSelection();
       //assertEquals(mapSelection.selectMap(new Point(604,276)), "Map 1.png");
    }
    @Test
    public void selectMapIndexTest(){
        MapSelection mapSelection = new MapSelection();
        assertEquals(mapSelection.selectMap(0), "Map 1.png");
        assertEquals(mapSelection.selectMap(1), "Map 2.png");
        assertNull(mapSelection.selectMap(2));
    }
    @Test
    public void changeSelectedMapTest(){
        MapSelection mapSelection = new MapSelection();
        assertEquals(mapSelection.changeSelectedMap(0,false), 1);
        assertEquals(mapSelection.changeSelectedMap(1,true), 0);
    }

}
