package com.aDeAyme.superMarioBros.controller;

import com.aDeAyme.superMarioBros.model.end_flag;
import com.aDeAyme.superMarioBros.model.brick.*;
import com.aDeAyme.superMarioBros.model.prize.*;
import com.aDeAyme.superMarioBros.view.image_loader;
import com.aDeAyme.superMarioBros.model.map;
import com.aDeAyme.superMarioBros.model.enemy.enemy;
import com.aDeAyme.superMarioBros.model.enemy.goomba;
import com.aDeAyme.superMarioBros.model.enemy.koopa_troopa;
import com.aDeAyme.superMarioBros.model.hero.mario;

import java.awt.*;
import java.awt.image.BufferedImage;

class map_creator {

    private image_loader image_loader;
    private com.aDeAyme.superMarioBros.controller.difficulty difficulty;
    private BufferedImage backgroundImage;
    private BufferedImage superMushroom, oneUpMushroom, fireFlower, coin;
    private BufferedImage ordinaryBrick, surpriseBrick, groundBrick, pipe;
    private BufferedImage goombaLeft, goombaRight, koopaLeft, koopaRight, endFlag;


    map_creator(image_loader image_loader, com.aDeAyme.superMarioBros.controller.difficulty difficulty) {

        this.image_loader = image_loader;
        BufferedImage sprite = image_loader.loadImage("/sprite.png");

        this.backgroundImage = image_loader.loadImage("/background.png");
        this.superMushroom = image_loader.getSubImage(sprite, 2, 5, 48, 48);
        this.oneUpMushroom= image_loader.getSubImage(sprite, 3, 5, 48, 48);
        this.fireFlower= image_loader.getSubImage(sprite, 4, 5, 48, 48);
        this.coin = image_loader.getSubImage(sprite, 1, 5, 48, 48);
        this.ordinaryBrick = image_loader.getSubImage(sprite, 1, 1, 48, 48);
        this.surpriseBrick = image_loader.getSubImage(sprite, 2, 1, 48, 48);
        this.groundBrick = image_loader.getSubImage(sprite, 2, 2, 48, 48);
        this.pipe = image_loader.getSubImage(sprite, 3, 1, 96, 96);
        this.goombaLeft = image_loader.getSubImage(sprite, 2, 4, 48, 48);
        this.goombaRight = image_loader.getSubImage(sprite, 5, 4, 48, 48);
        this.koopaLeft = image_loader.getSubImage(sprite, 1, 3, 48, 64);
        this.koopaRight = image_loader.getSubImage(sprite, 4, 3, 48, 64);
        this.endFlag = image_loader.getSubImage(sprite, 5, 1, 48, 48);
        this.difficulty= difficulty;


    }

    map createMap(String mapPath, double timeLimit) {
        BufferedImage mapImage = image_loader.loadImage(mapPath);

        if (mapImage == null) {
            System.out.println("Given path is invalid...");
            return null;
        }

        map createdMap = new map(timeLimit, backgroundImage);
        String[] paths = mapPath.split("/");
        createdMap.setPath(paths[paths.length-1]);

        int pixelMultiplier = 48;

        int mario = new Color(160, 160, 160).getRGB();
        int ordinaryBrick = new Color(0, 0, 255).getRGB();
        int surpriseBrick = new Color(255, 255, 0).getRGB();
        int groundBrick = new Color(255, 0, 0).getRGB();
        int pipe = new Color(0, 255, 0).getRGB();
        int goomba = new Color(0, 255, 255).getRGB();
        int koopa = new Color(255, 0, 255).getRGB();
        int end = new Color(160, 0, 160).getRGB();

        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {

                int currentPixel = mapImage.getRGB(x, y);
                int xLocation = x*pixelMultiplier;
                int yLocation = y*pixelMultiplier;

                if (currentPixel == ordinaryBrick) {
                    brick brick = new ordinary_brick(xLocation, yLocation, this.ordinaryBrick);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == surpriseBrick) {
                    prize prize = generateRandomPrize(xLocation, yLocation);
                    brick brick = new surprise_brick(xLocation, yLocation, this.surpriseBrick, prize);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == pipe) {
                    brick brick = new pipe(xLocation, yLocation, this.pipe);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == groundBrick) {
                    brick brick = new ground_brick(xLocation, yLocation, this.groundBrick);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == goomba) {
                    enemy enemy = new goomba(xLocation, yLocation, this.goombaLeft);
                    ((com.aDeAyme.superMarioBros.model.enemy.goomba)enemy).setRightImage(goombaRight);
                    this.difficulty.registerObserver((observer) enemy);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == koopa) {
                    enemy enemy = new koopa_troopa(xLocation, yLocation, this.koopaLeft);
                    ((koopa_troopa)enemy).setRightImage(koopaRight);
                    this.difficulty.registerObserver((observer) enemy);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == mario) {
                    com.aDeAyme.superMarioBros.model.hero.mario marioObject = new mario(xLocation, yLocation , difficulty);
                    createdMap.setMario(marioObject);
                }
                else if(currentPixel == end){
                    end_flag endPoint= new end_flag(xLocation+24, yLocation, endFlag);
                    createdMap.setEndPoint(endPoint);
                }
            }
        }

        System.out.println("El mapa esta creado!! 🕺"); //Imprimo por consola
        return createdMap;
    }

    private prize generateRandomPrize(double x, double y){
        prize generated;
        int random = (int)(Math.random() * 12);

        if(random == 0){ //super mushroom
            generated = new super_mushroom(x, y, this.superMushroom);
        }
        else if(random == 1){ //fire flower
            generated = new fire_flower(x, y, this.fireFlower);
        }
        else if(random == 2){ //one up mushroom
            generated = new one_up_mushroom(x, y, this.oneUpMushroom);
        }
        else{ //coin
            generated = new coin(x, y, this.coin, 50);
        }

        return generated;
    }


}
