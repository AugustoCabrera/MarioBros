package com.aDeAyme.superMarioBros.view;

import java.awt.*;
import java.awt.image.BufferedImage;

public class map_selection_item {

    private BufferedImage image;
    private String name;
    private Point location;
    private Dimension dimension;

    public map_selection_item(String map, Point location){
        this.location = location;
        this.name = map;

        image_loader loader = new image_loader();
        this.image = loader.loadImage("/maps/" + map);

        this.dimension = new Dimension();
    }


    public String getName() {
        return name;
    }

    public Point getLocation() {
        return location;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
