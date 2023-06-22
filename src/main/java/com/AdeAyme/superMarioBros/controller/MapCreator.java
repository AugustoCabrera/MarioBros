package com.aDeAyme.superMarioBros.controller;
import com.aDeAyme.superMarioBros.model.EndFlag;
import com.aDeAyme.superMarioBros.model.brick.*;
import com.aDeAyme.superMarioBros.model.prize.*;
import com.aDeAyme.superMarioBros.view.ImageLoader;
import com.aDeAyme.superMarioBros.model.Map;
import com.aDeAyme.superMarioBros.model.enemy.Enemy;
import com.aDeAyme.superMarioBros.model.enemy.Goomba;
import com.aDeAyme.superMarioBros.model.enemy.KoopaTroopa;
import com.aDeAyme.superMarioBros.model.hero.Mario;

import java.awt.*;
import java.awt.image.BufferedImage;

//Dedicado a la creacion de mapa, cargando sus elementos graficos
class MapCreator {

    private ImageLoader ImageLoader;        //Cargador de imagenes
    private Difficulty difficulty;          //Dificultad actual del videojuego
    private BufferedImage backgroundImage;  //Imagen de fondo del videojuego
    private BufferedImage superMushroom, oneUpMushroom, fireFlower, coin;   //Imagen de los elementos mencionados del mapa
    private BufferedImage ordinaryBrick, surpriseBrick, groundBrick, pipe;  //Imagen de los elementos mencionados del mapa
    private BufferedImage goombaLeft, goombaRight, koopaLeft, koopaRight, endFlag;    //Imagen de los elementos mencionados del mapa


    MapCreator(ImageLoader ImageLoader, Difficulty difficulty) { //Establece los atributos de la clase

        this.ImageLoader = ImageLoader;
        BufferedImage sprite = ImageLoader.loadImage("/sprite.png");

        this.backgroundImage = ImageLoader.loadImage("/background.png");
        this.superMushroom = ImageLoader.getSubImage(sprite, 2, 5, 48, 48);
        this.oneUpMushroom= ImageLoader.getSubImage(sprite, 3, 5, 48, 48);
        this.fireFlower= ImageLoader.getSubImage(sprite, 4, 5, 48, 48);
        this.coin = ImageLoader.getSubImage(sprite, 1, 5, 48, 48);
        this.ordinaryBrick = ImageLoader.getSubImage(sprite, 1, 1, 48, 48);
        this.surpriseBrick = ImageLoader.getSubImage(sprite, 2, 1, 48, 48);
        this.groundBrick = ImageLoader.getSubImage(sprite, 2, 2, 48, 48);
        this.pipe = ImageLoader.getSubImage(sprite, 3, 1, 96, 96);
        this.goombaLeft = ImageLoader.getSubImage(sprite, 2, 4, 48, 48);
        this.goombaRight = ImageLoader.getSubImage(sprite, 5, 4, 48, 48);
        this.koopaLeft = ImageLoader.getSubImage(sprite, 1, 3, 48, 64);
        this.koopaRight = ImageLoader.getSubImage(sprite, 4, 3, 48, 64);
        this.endFlag = ImageLoader.getSubImage(sprite, 5, 1, 48, 48);
        this.difficulty= difficulty;        //Establece un difficulty el comun entre Mario y sus enemigos

    }

    Map createMap(String mapPath, double timeLimit) {   //Crea un mapa a partir de su path en el proyecto
        BufferedImage mapImage = ImageLoader.loadImage(mapPath);
        if (mapImage == null) {
            System.out.println("Given path is invalid...");
            return null;
        }
        Map createdMap = new Map(timeLimit, backgroundImage);
        String[] paths = mapPath.split("/");
        createdMap.setPath(paths[paths.length-1]);

        int pixelMultiplier = 48;
        //Relaciona un color determinado en el mapa con un objeto del mismo
        int mario = new Color(160, 160, 160).getRGB();
        int ordinaryBrick = new Color(0, 0, 255).getRGB();
        int surpriseBrick = new Color(255, 255, 0).getRGB();
        int groundBrick = new Color(255, 0, 0).getRGB();
        int pipe = new Color(0, 255, 0).getRGB();
        int goomba = new Color(0, 255, 255).getRGB();
        int koopa = new Color(255, 0, 255).getRGB();
        int end = new Color(160, 0, 160).getRGB();

        //Va creando objetos del mapa segun aparezca su color (relacionado con un entero) en el mapa cargado
        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {
                int currentPixel = mapImage.getRGB(x, y);
                int xLocation = x*pixelMultiplier;
                int yLocation = y*pixelMultiplier;

                if (currentPixel == ordinaryBrick) {
                    Brick brick = new OrdinaryBrick(xLocation, yLocation, this.ordinaryBrick);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == surpriseBrick) {
                    Prize prize = generateRandomPrize(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation, this.surpriseBrick, prize);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == pipe) {
                    Brick brick = new Pipe(xLocation, yLocation, this.pipe);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == groundBrick) {
                    Brick brick = new GroundBrick(xLocation, yLocation, this.groundBrick);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == goomba) {
                    Enemy enemy = new Goomba(xLocation, yLocation, this.goombaLeft);
                    ((Goomba)enemy).setRightImage(goombaRight);
                    this.difficulty.registerObserver((Observer) enemy);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == koopa) {
                    Enemy enemy = new KoopaTroopa(xLocation, yLocation, this.koopaLeft);
                    ((KoopaTroopa)enemy).setRightImage(koopaRight);
                    this.difficulty.registerObserver((Observer) enemy);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == mario) {
                    Mario marioObject = new Mario(xLocation, yLocation , difficulty);
                    createdMap.setMario(marioObject);
                }
                else if(currentPixel == end){
                    EndFlag endPoint= new EndFlag(xLocation+24, yLocation, endFlag);
                    createdMap.setEndPoint(endPoint);
                }
            }
        }
        return createdMap;
    }

    private Prize generateRandomPrize(double x, double y){      //Genera un premio random dentro de lada bloque de sorpresa
        Prize generated;
        int random = (int)(Math.random() * 12);

        if(random == 0){ //super mushroom
            generated = new SuperMushroom(x, y, this.superMushroom);
        }
        else if(random == 1){ //fire flower
            generated = new FireFlower(x, y, this.fireFlower);
        }
        else if(random == 2){ //one up mushroom
            generated = new OneUpMushroom(x, y, this.oneUpMushroom);
        }
        else{ //coin
            generated = new Coin(x, y, this.coin, 50);
        }
        return generated;
    }
}
