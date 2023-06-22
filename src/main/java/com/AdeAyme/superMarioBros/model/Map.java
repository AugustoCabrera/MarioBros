package com.aDeAyme.superMarioBros.model;
import com.aDeAyme.superMarioBros.model.brick.Brick;
import com.aDeAyme.superMarioBros.model.brick.OrdinaryBrick;
import com.aDeAyme.superMarioBros.model.enemy.Enemy;
import com.aDeAyme.superMarioBros.model.hero.Fireball;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.prize.BoostItem;
import com.aDeAyme.superMarioBros.model.prize.Coin;
import com.aDeAyme.superMarioBros.model.prize.Prize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
//Establece los elementos y comportamientos de los mapas en este videojuego

public class Map {
    private double remainingTime;   //Tiempo restante de jugabilidad
    private Mario mario;            //Objeto Mario del mapa
    private ArrayList<Brick> Bricks = new ArrayList<>();    //Lista de bloques del mapa
    private ArrayList<Enemy> enemies = new ArrayList<>();   //Lista de los enemigos del mapa
    private ArrayList<Brick> groundBricks = new ArrayList<>();      //Lista de los bloques "suelo" del mapa
    private ArrayList<Prize> revealedPrizes = new ArrayList<>();    //Lista de los premios revelados del mapa
    private ArrayList<Brick> revealedBricks = new ArrayList<>();    //Lista de los bloques sorpresa revelados del mapa
    private ArrayList<Fireball> Fireballs = new ArrayList<>();      //Lista de las bolas de fuego lanzadas en el mapa
    private EndFlag endPoint;       //Objeto bandera final del mapa
    private BufferedImage backgroundImage;      //Imagen fondo del mapa
    private double bottomBorder = 720 - 96;     //Borde inferior del mapa
    private String path;                        //Ubicacion del mapa en el proyecto

    public Map(double remainingTime, BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.remainingTime = remainingTime;
    }

    public Mario getMario() {
        return mario;
    }       //Devuelve a Mario

    public void setMario(Mario mario) {
        this.mario = mario;
    }   //Establece el Mario del mapa

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }   //Devuelve la lista de enemigos del mapa

    public ArrayList<Fireball> getFireballs() {
        return Fireballs;
    }   //Devuelve la lista de bolas de fuego del mapa

    public ArrayList<Prize> getRevealedPrizes() {
        return revealedPrizes;
    }   //Devuelve la lista de premios revelados del mapa

    public ArrayList<Brick> getAllBricks() {    //Devuelve la lista de todos los bloques del mapa
        ArrayList<Brick> allBricks = new ArrayList<>();
        allBricks.addAll(Bricks);
        allBricks.addAll(groundBricks);
        return allBricks;
    }

    public void addBrick(Brick brick) {
        this.Bricks.add(brick);
    }   //Devuelve la lista de los bloques del mapa

    public void addGroundBrick(Brick brick) {
        this.groundBricks.add(brick);
    }   //Devuelve la lista de los bloques "suelo" del mapa

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    } //Agrega un enemigo a la lista del mapa

    public void drawMap(Graphics2D g2){     //Dibuja el mapa con sus elementos
        drawBackground(g2);
        drawPrizes(g2);
        drawBricks(g2);
        drawEnemies(g2);
        drawFireballs(g2);
        drawMario(g2);
        endPoint.draw(g2);
    }

    private void drawFireballs(Graphics2D g2) {     //Dibuja las bolas de fuego cuando son disparadas
        for(Fireball fireball : Fireballs){
            fireball.draw(g2);
        }
    }

    private void drawPrizes(Graphics2D g2) {        //Dibuja los premios cuando son revelados
        for(Prize prize : revealedPrizes){
            if(prize instanceof Coin){
                ((Coin) prize).draw(g2);
            }
            else if(prize instanceof BoostItem){
                ((BoostItem) prize).draw(g2);
            }
        }
    }

    private void drawBackground(Graphics2D g2){
        g2.drawImage(backgroundImage, 0, 0, null);
    }   //Dibuja el fondo del mapa

    private void drawBricks(Graphics2D g2) {    //Dibuja los bloques del mapa
        for(Brick brick : Bricks){
            if(brick != null)
                brick.draw(g2);
        }

        for(Brick brick : groundBricks){        //Dibuja los bloques "suelo" del mapa
            brick.draw(g2);
        }
    }

    private void drawEnemies(Graphics2D g2) {   //Dibuja los enemigos del mapa
        for(Enemy enemy : enemies){
            if(enemy != null)
                enemy.draw(g2);
        }
    }

    private void drawMario(Graphics2D g2) {
        mario.draw(g2);
    }   //Dibuja a Mario

    public void updateLocations() {         //Actualiza la localizacion de los elementos del mapa
        mario.updateLocation();
        for(Enemy enemy : enemies){
            enemy.updateLocation();
        }

        for(Iterator<Prize> prizeIterator = revealedPrizes.iterator(); prizeIterator.hasNext();){
            Prize prize = prizeIterator.next();
            if(prize instanceof Coin){
                ((Coin) prize).updateLocation();
                if(((Coin) prize).getRevealBoundary() > ((Coin) prize).getY()){
                    prizeIterator.remove();
                }
            }
            else if(prize instanceof BoostItem){
                ((BoostItem) prize).updateLocation();
            }
        }

        for (Fireball fireball: Fireballs) {
            fireball.updateLocation();
        }

        for(Iterator<Brick> brickIterator = revealedBricks.iterator(); brickIterator.hasNext();){
            OrdinaryBrick brick = (OrdinaryBrick)brickIterator.next();
            brick.animate();
            if(brick.getFrames() < 0){
                Bricks.remove(brick);
                brickIterator.remove();
            }
        }

        endPoint.updateLocation();
    }

    public double getBottomBorder() {
        return bottomBorder;
    }   //Devuelve el borde inferior del mapa

    public void addRevealedPrize(Prize prize) {
        revealedPrizes.add(prize);
    }   //Agrega un premio revelado a la lista del mapa

    public void addFireball(Fireball fireball) {
        Fireballs.add(fireball);
    }   //Agrega una bola de fuego lanzada a la lista del mapa

    public void setEndPoint(EndFlag endPoint) {
        this.endPoint = endPoint;
    }   //Establece la bandera final del mapa

    public EndFlag getEndPoint() {
        return endPoint;
    }   //Devuelve la bandera finald el mapa

    public void addRevealedBrick(OrdinaryBrick OrdinaryBrick) {
        revealedBricks.add(OrdinaryBrick);
    }   //Agrega un bloque revelado a la lista del mapa

    public void removeFireball(Fireball object) {
        Fireballs.remove(object);
    } //Elimina una determinada bola de fuego del mapa

    public void removeEnemy(Enemy object) {
        enemies.remove(object);
    }   //Elimina un determinado enemigo del mapa

    public void removePrize(Prize object) {
        revealedPrizes.remove(object);
    }   //Elimina un determinado premio del mapa

    public String getPath() {
        return path;
    }   //Devuelve la localizacion de los mapas en el proyecto

    public void setPath(String path) {
        this.path = path;
    }   //Determina la localizacion de los mapas en el proyecto

    public void updateTime(double passed){
        remainingTime = remainingTime - passed;
    }   //Actualiza el tiempo restantes de jugabilidad

    public boolean isTimeOver(){
        return remainingTime <= 0;
    }   //Determina si el tiempo termino o no

    public double getRemainingTime() {
        return remainingTime;
    }   //Devuelve el tiempo restante de jugabilidad
}
