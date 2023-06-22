package com.aDeAyme.superMarioBros.view;
import java.awt.*;
import java.util.ArrayList;
//Selector de mapas del videojuego
public class MapSelection {
    private final ArrayList<String> maps = new ArrayList<>();   //Lista de mapas
    private final MapSelectionItem[] MapSelectionItems;         //Items de mapas

    public MapSelection(){
        getMaps();
        this.MapSelectionItems = createItems(this.maps);
    }

    public void draw(Graphics g){       //Dibuja la pantalla de seleccion de mapas
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 1280, 720);

        if(MapSelectionItems == null){
            System.out.println(1);
            return;
        }

        String title = "Select a Map";
        int x_location = (1280 - g.getFontMetrics().stringWidth(title))/2;
        g.setColor(Color.YELLOW);
        g.drawString(title, x_location, 150);

        for(MapSelectionItem item : MapSelectionItems){
            g.setColor(Color.WHITE);
            int width = g.getFontMetrics().stringWidth(item.getName().split("[.]")[0]);
            int height = g.getFontMetrics().getHeight();
            item.setDimension( new Dimension(width, height));
            item.setLocation( new Point((1280-width)/2, item.getLocation().y));
            g.drawString(item.getName().split("[.]")[0], item.getLocation().x, item.getLocation().y);
        }
    }

    private void getMaps(){         //Devuelve los mapas
        //TODO: read from file
        maps.add("Map 1.png");
        maps.add("Map 2.png");
    }

    private MapSelectionItem[] createItems(ArrayList<String> maps){     //Crea los items de los mapas
        if(maps == null)
            return null;

        int defaultGridSize = 100;
        MapSelectionItem[] items = new MapSelectionItem[maps.size()];
        for (int i = 0; i < items.length; i++) {
            Point location = new Point(0, (i+1)*defaultGridSize+200);
            items[i] = new MapSelectionItem(maps.get(i), location);
        }

        return items;
    }

    public String selectMap(Point mouseLocation) {      //Elige un mapa de la lista de items mediante el mouse
        for(MapSelectionItem item : MapSelectionItems) {
            Dimension dimension = item.getDimension();
            Point location = item.getLocation();
            boolean inX = location.x <= mouseLocation.x && location.x + dimension.width >= mouseLocation.x;
            boolean inY = location.y >= mouseLocation.y && location.y - dimension.height <= mouseLocation.y;
            if(inX && inY){
                return item.getName();
            }
        }
        return null;
    }

    public String selectMap(int index){             //Elige un mapa de la lista de items mediante el uso del teclado
        if(index < MapSelectionItems.length && index > -1)
            return MapSelectionItems[index].getName();
        return null;
    }

    public int changeSelectedMap(int index, boolean up) {   //Cambia la seleccion del mapa
        if(up){
            if(index <= 0)
                return MapSelectionItems.length - 1;
            else
                return index - 1;
        }
        else{
            if(index >= MapSelectionItems.length - 1)
                return 0;
            else
                return index + 1;
        }
    }
}
