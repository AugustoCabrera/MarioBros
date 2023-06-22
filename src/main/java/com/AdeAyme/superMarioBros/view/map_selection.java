package com.aDeAyme.superMarioBros.view;

import java.awt.*;
import java.util.ArrayList;

public class map_selection {

    private final ArrayList<String> maps = new ArrayList<>();
    private final map_selection_item[] map_selection_items;

    public map_selection(){
        getMaps();
        this.map_selection_items = createItems(this.maps);
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 1280, 720);

        if(map_selection_items == null){
            System.out.println(1);
            return;
        }

        String title = "Select a Map";
        int x_location = (1280 - g.getFontMetrics().stringWidth(title))/2;
        g.setColor(Color.YELLOW);
        g.drawString(title, x_location, 150);

        for(map_selection_item item : map_selection_items){
            g.setColor(Color.WHITE);
            int width = g.getFontMetrics().stringWidth(item.getName().split("[.]")[0]);
            int height = g.getFontMetrics().getHeight();
            item.setDimension( new Dimension(width, height));
            item.setLocation( new Point((1280-width)/2, item.getLocation().y));
            g.drawString(item.getName().split("[.]")[0], item.getLocation().x, item.getLocation().y);
        }
    }

    private void getMaps(){
        //TODO: read from file
        maps.add("Map 1.png");
        maps.add("Map 2.png");
    }

    private map_selection_item[] createItems(ArrayList<String> maps){
        if(maps == null)
            return null;

        int defaultGridSize = 100;
        map_selection_item[] items = new map_selection_item[maps.size()];
        for (int i = 0; i < items.length; i++) {
            Point location = new Point(0, (i+1)*defaultGridSize+200);
            items[i] = new map_selection_item(maps.get(i), location);
        }

        return items;
    }

    public String selectMap(Point mouseLocation) {
        for(map_selection_item item : map_selection_items) {
            Dimension dimension = item.getDimension();
            Point location = item.getLocation();
            //System.out.println("x:"+mouseLocation.x+"    y:"+mouseLocation.y);
            boolean inX = location.x <= mouseLocation.x && location.x + dimension.width >= mouseLocation.x;
            boolean inY = location.y >= mouseLocation.y && location.y - dimension.height <= mouseLocation.y;
            if(inX && inY){
                return item.getName();
            }
        }
        return null;
    }

    public String selectMap(int index){
        if(index < map_selection_items.length && index > -1)
            return map_selection_items[index].getName();
        return null;
    }

    public int changeSelectedMap(int index, boolean up) {
        if(up){
            if(index <= 0)
                return map_selection_items.length - 1;
            else
                return index - 1;
        }
        else{
            if(index >= map_selection_items.length - 1)
                return 0;
            else
                return index + 1;
        }
    }
}
