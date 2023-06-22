package com.aDeAyme.superMarioBros.view;
import java.awt.*;
import java.awt.image.BufferedImage;
//Item de mapa para la seleccion

public class MapSelectionItem {

    private BufferedImage image;    //Imagen del mapa
    private String name;            //Nombre del mapa
    private Point location;         //Localizacion de la flecha del mouse
    private Dimension dimension;    //Dimensiones del item

    public MapSelectionItem(String map, Point location){
        this.location = location;
        this.name = map;

        ImageLoader loader = new ImageLoader();
        this.image = loader.loadImage("/maps/" + map);

        this.dimension = new Dimension();
    }


    public String getName() {
        return name;
    }   //Devuelve el nombre del mapa
    public Point getLocation() {
        return location;
    }   //Devuelve la localizacion del mouse en la pantalla
    public Dimension getDimension() {
        return dimension;
    }   //Devuelve la dimension del item
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }   //Establece la dimension del item
    public void setLocation(Point location) {
        this.location = location;
    }   //Establece la localizacion del mouse en la pantalla
}
