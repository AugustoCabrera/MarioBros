package com.aDeAyme.superMarioBros.viewTest;
import com.aDeAyme.superMarioBros.view.map_selection_item;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
public class map_selection_item_test {
    @Test
    public void createMapSelectionItemTest(){
        Point point =new Point(30,40);
        map_selection_item item = new map_selection_item("Map 1.png", point);
        assertEquals("Map 1.png", item.getName());
        assertEquals(point, item.getLocation());
    }
    @Test
    public void setDimensionTest(){
        Dimension dim = new Dimension(50,60);
        map_selection_item item = new map_selection_item("Map 1.png", new Point());
        item.setDimension(dim);
        assertEquals(50, item.getDimension().getWidth(),0.0);
        assertEquals(60, item.getDimension().getHeight(),0.0);
    }
    @Test
    public void setNameTest(){
        Point point = new Point();
        map_selection_item item = new map_selection_item("Map 1.png", null);
        item.setLocation(point);
        assertSame(point, item.getLocation());
    }
}
