package com.aDeAyme.superMarioBros.model;

import com.aDeAyme.superMarioBros.model.brick.brick;
import com.aDeAyme.superMarioBros.model.brick.ordinary_brick;
import com.aDeAyme.superMarioBros.model.enemy.enemy;
import com.aDeAyme.superMarioBros.model.hero.fireball;
import com.aDeAyme.superMarioBros.model.prize.boostItem;
import com.aDeAyme.superMarioBros.model.prize.coin;
import com.aDeAyme.superMarioBros.model.prize.prize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class map {

    private double remainingTime;
    private com.aDeAyme.superMarioBros.model.hero.mario mario;
    private ArrayList<brick> bricks = new ArrayList<>();
    private ArrayList<enemy> enemies = new ArrayList<>();
    private ArrayList<brick> groundBricks = new ArrayList<>();
    private ArrayList<prize> revealedPrizes = new ArrayList<>();
    private ArrayList<brick> revealedBricks = new ArrayList<>();
    private ArrayList<fireball> fireballs = new ArrayList<>();
    private end_flag endPoint;
    private BufferedImage backgroundImage;
    private double bottomBorder = 720 - 96;
    private String path;



    public map(double remainingTime, BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.remainingTime = remainingTime;
    }


    public com.aDeAyme.superMarioBros.model.hero.mario getMario() {
        return mario;
    }

    public void setMario(com.aDeAyme.superMarioBros.model.hero.mario mario) {
        this.mario = mario;
    }

    public ArrayList<enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<fireball> getFireballs() {
        return fireballs;
    }

    public ArrayList<prize> getRevealedPrizes() {
        return revealedPrizes;
    }

    public ArrayList<brick> getAllBricks() {
        ArrayList<brick> allBricks = new ArrayList<>();

        allBricks.addAll(bricks);
        allBricks.addAll(groundBricks);

        return allBricks;
    }

    public void addBrick(brick brick) {
        this.bricks.add(brick);
    }

    public void addGroundBrick(brick brick) {
        this.groundBricks.add(brick);
    }

    public void addEnemy(enemy enemy) {
        this.enemies.add(enemy);
    }

    public void drawMap(Graphics2D g2){
        drawBackground(g2);
        drawPrizes(g2);
        drawBricks(g2);
        drawEnemies(g2);
        drawFireballs(g2);
        drawMario(g2);
        endPoint.draw(g2);
    }

    private void drawFireballs(Graphics2D g2) {
        for(com.aDeAyme.superMarioBros.model.hero.fireball fireball : fireballs){
            fireball.draw(g2);
        }
    }

    private void drawPrizes(Graphics2D g2) {
        for(com.aDeAyme.superMarioBros.model.prize.prize prize : revealedPrizes){
            if(prize instanceof coin){
                ((coin) prize).draw(g2);
            }
            else if(prize instanceof boostItem){
                ((boostItem) prize).draw(g2);
            }
        }
    }

    private void drawBackground(Graphics2D g2){
        g2.drawImage(backgroundImage, 0, 0, null);
    }

    private void drawBricks(Graphics2D g2) {
        for(com.aDeAyme.superMarioBros.model.brick.brick brick : bricks){
            if(brick != null)
                brick.draw(g2);
        }

        for(com.aDeAyme.superMarioBros.model.brick.brick brick : groundBricks){
            brick.draw(g2);
        }
    }

    private void drawEnemies(Graphics2D g2) {
        for(com.aDeAyme.superMarioBros.model.enemy.enemy enemy : enemies){
            if(enemy != null)
                enemy.draw(g2);
        }
    }

    private void drawMario(Graphics2D g2) {
        mario.draw(g2);
    }

    public void updateLocations() {
        mario.updateLocation();
        for(com.aDeAyme.superMarioBros.model.enemy.enemy enemy : enemies){
            enemy.updateLocation();
        }

        for(Iterator<prize> prizeIterator = revealedPrizes.iterator(); prizeIterator.hasNext();){
            prize prize = prizeIterator.next();
            if(prize instanceof coin){
                ((coin) prize).updateLocation();
                if(((coin) prize).getRevealBoundary() > ((coin) prize).getY()){
                    prizeIterator.remove();
                }
            }
            else if(prize instanceof boostItem){
                ((boostItem) prize).updateLocation();
            }
        }

        for (com.aDeAyme.superMarioBros.model.hero.fireball fireball: fireballs) {
            fireball.updateLocation();
        }

        for(Iterator<brick> brickIterator = revealedBricks.iterator(); brickIterator.hasNext();){
            ordinary_brick brick = (ordinary_brick)brickIterator.next();
            brick.animate();
            if(brick.getFrames() < 0){
                bricks.remove(brick);
                brickIterator.remove();
            }
        }

        endPoint.updateLocation();
    }

    public double getBottomBorder() {
        return bottomBorder;
    }

    public void addRevealedPrize(prize prize) {
        revealedPrizes.add(prize);
    }

    public void addFireball(fireball fireball) {
        fireballs.add(fireball);
    }

    public void setEndPoint(end_flag endPoint) {
        this.endPoint = endPoint;
    }

    public end_flag getEndPoint() {
        return endPoint;
    }

    public void addRevealedBrick(ordinary_brick ordinary_brick) {
        revealedBricks.add(ordinary_brick);
    }

    public void removeFireball(fireball object) {
        fireballs.remove(object);
    }

    public void removeEnemy(enemy object) {
        enemies.remove(object);
    }

    public void removePrize(prize object) {
        revealedPrizes.remove(object);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void updateTime(double passed){
        remainingTime = remainingTime - passed;
    }

    public boolean isTimeOver(){
        return remainingTime <= 0;
    }

    public double getRemainingTime() {
        return remainingTime;
    }
}
