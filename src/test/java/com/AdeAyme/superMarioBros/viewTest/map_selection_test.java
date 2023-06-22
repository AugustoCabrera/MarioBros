package com.aDeAyme.superMarioBros.viewTest;

import com.aDeAyme.superMarioBros.view.map_selection;
import org.junit.Test;

import static org.junit.Assert.*;
public class map_selection_test {
    @Test
    public void selectMapMouseTest(){
        map_selection map_selection = new map_selection();
       //assertEquals(mapSelection.selectMap(new Point(604,276)), "Map 1.png");
    }
    @Test
    public void selectMapIndexTest(){
        map_selection map_selection = new map_selection();
        assertEquals(map_selection.selectMap(0), "Map 1.png");
        assertEquals(map_selection.selectMap(1), "Map 2.png");
        assertNull(map_selection.selectMap(2));
    }
    @Test
    public void changeSelectedMapTest(){
        map_selection map_selection = new map_selection();
        assertEquals(map_selection.changeSelectedMap(0,false), 1);
        assertEquals(map_selection.changeSelectedMap(1,true), 0);
    }

}
