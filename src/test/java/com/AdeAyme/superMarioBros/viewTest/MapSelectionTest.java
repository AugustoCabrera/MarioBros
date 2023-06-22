package com.aDeAyme.superMarioBros.viewTest;

import com.aDeAyme.superMarioBros.view.MapSelection;
import org.junit.Test;

import static org.junit.Assert.*;
public class MapSelectionTest {
    @Test
    public void selectMapMouseTest(){
        MapSelection MapSelection = new MapSelection();
       //assertEquals(mapSelection.selectMap(new Point(604,276)), "Map 1.png");
    }
    @Test
    public void selectMapIndexTest(){
        MapSelection MapSelection = new MapSelection();
        assertEquals(MapSelection.selectMap(0), "Map 1.png");
        assertEquals(MapSelection.selectMap(1), "Map 2.png");
        assertNull(MapSelection.selectMap(2));
    }
    @Test
    public void changeSelectedMapTest(){
        MapSelection MapSelection = new MapSelection();
        assertEquals(MapSelection.changeSelectedMap(0,false), 1);
        assertEquals(MapSelection.changeSelectedMap(1,true), 0);
    }

}
