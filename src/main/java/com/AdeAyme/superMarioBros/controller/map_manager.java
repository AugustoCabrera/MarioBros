package com.aDeAyme.superMarioBros.controller;

import com.aDeAyme.superMarioBros.model.game_object;
import com.aDeAyme.superMarioBros.model.brick.brick;
import com.aDeAyme.superMarioBros.model.brick.ordinary_brick;
import com.aDeAyme.superMarioBros.model.enemy.enemy;
import com.aDeAyme.superMarioBros.model.hero.fireball;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.prize.boostItem;
import com.aDeAyme.superMarioBros.model.prize.coin;
import com.aDeAyme.superMarioBros.model.prize.prize;
import com.aDeAyme.superMarioBros.view.image_loader;

import java.awt.*;
import java.util.ArrayList;

public class map_manager {

    private com.aDeAyme.superMarioBros.model.map map;
    private com.aDeAyme.superMarioBros.controller.difficulty difficulty;

    public map_manager() {

        difficulty = new difficulty();
    }

    public void updateLocations() {
        if (map == null)
            return;

        map.updateLocations();
    }

    public void resetCurrentMap(game_engine engine) {
        mario mario = getMario();
        mario.resetLocation();
        engine.resetCamera();
        createMap(engine.getImageLoader(), map.getPath());
        map.setMario(mario);
    }

    public boolean createMap(image_loader loader, String path) {
        map_creator map_creator = new map_creator(loader, difficulty);
        map = map_creator.createMap("/maps/" + path, 400);

        return map != null;
    }

    public void acquirePoints(int point) {
        map.getMario().acquirePoints(point);

    }

    public mario getMario() {
        return map.getMario();
    }

    public void fire(game_engine engine) {
        fireball fireball = getMario().fire();
        if (fireball != null) {
            map.addFireball(fireball);
            engine.playFireball(); //es el sonido del fuego
        }
    }

    public boolean isGameOver() {
        return getMario().getRemainingLives() == 0 || map.isTimeOver();
    }

    public int getScore() {
        return getMario().getPoints();
    }

    public int getRemainingLives() {
        return getMario().getRemainingLives();
    }

    public int getCoins() {
        return getMario().getCoins();
    }

    public void drawMap(Graphics2D g2) {
        map.drawMap(g2);
    }

    public int passMission() {
        if(getMario().getX() >= map.getEndPoint().getX() && !map.getEndPoint().isTouched()){
            map.getEndPoint().setTouched(true);
            int height = (int)getMario().getY();
            return height * 2;
        }
        else
            return -1;
    }

    public boolean endLevel(){
        return getMario().getX() >= map.getEndPoint().getX() + 320;
    }

    public void checkCollisions(game_engine engine) {
        if (map == null) {
            return;
        }
        checkBottomCollisions(engine);
        checkTopCollisions(engine);
        checkMarioHorizontalCollision(engine);
        checkEnemyCollisions();
        checkPrizeCollision();
        checkPrizeContact(engine);
        checkFireballContact();
    }

    private void checkBottomCollisions(game_engine engine) {
        mario mario = getMario();
        ArrayList<brick> bricks = map.getAllBricks();
        ArrayList<enemy> enemies = map.getEnemies();
        ArrayList<game_object> toBeRemoved = new ArrayList<>();

        Rectangle marioBottomBounds = mario.getBottomBounds();

        if (!mario.isJumping())
            mario.setFalling(true);

        for (com.aDeAyme.superMarioBros.model.brick.brick brick : bricks) {
            Rectangle brickTopBounds = brick.getTopBounds();
            if (marioBottomBounds.intersects(brickTopBounds)) {
                mario.setY(brick.getY() - mario.getDimension().height + 1);
                mario.setFalling(false);
                mario.setVelY(0);
            }
        }

        for (com.aDeAyme.superMarioBros.model.enemy.enemy enemy : enemies) {
            Rectangle enemyTopBounds = enemy.getTopBounds();
            if (marioBottomBounds.intersects(enemyTopBounds)) {
                mario.acquirePoints(100);
                toBeRemoved.add(enemy);
                engine.playStomp();
            }
        }

        if (mario.getY() + mario.getDimension().height >= map.getBottomBorder()) {
            mario.setY(map.getBottomBorder() - mario.getDimension().height);
            mario.setFalling(false);
            mario.setVelY(0);
        }

        removeObjects(toBeRemoved);
    }

    private void checkTopCollisions(game_engine engine) {
        mario mario = getMario();
        ArrayList<brick> bricks = map.getAllBricks();

        Rectangle marioTopBounds = mario.getTopBounds();
        for (com.aDeAyme.superMarioBros.model.brick.brick brick : bricks) {
            Rectangle brickBottomBounds = brick.getBottomBounds();
            if (marioTopBounds.intersects(brickBottomBounds)) {
                mario.setVelY(0);
                mario.setY(brick.getY() + brick.getDimension().height);
                prize prize = brick.reveal(engine);
                if(prize != null)
                    map.addRevealedPrize(prize);
            }
        }
    }

    private void checkMarioHorizontalCollision(game_engine engine){
        mario mario = getMario();
        ArrayList<brick> bricks = map.getAllBricks();
        ArrayList<enemy> enemies = map.getEnemies();
        ArrayList<game_object> toBeRemoved = new ArrayList<>();

        boolean marioDies = false;
        boolean toRight = mario.getToRight();

        Rectangle marioBounds = toRight ? mario.getRightBounds() : mario.getLeftBounds();

        for (com.aDeAyme.superMarioBros.model.brick.brick brick : bricks) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (marioBounds.intersects(brickBounds)) {
                mario.setVelX(0);
                if(toRight)
                    mario.setX(brick.getX() - mario.getDimension().width);
                else
                    mario.setX(brick.getX() + brick.getDimension().width);
            }
        }

        for(com.aDeAyme.superMarioBros.model.enemy.enemy enemy : enemies){
            Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
            if (marioBounds.intersects(enemyBounds)) {
                marioDies = mario.onTouchEnemy(engine , difficulty );
                toBeRemoved.add(enemy);
            }
        }
        removeObjects(toBeRemoved);


        if (mario.getX() <= engine.getCameraLocation().getX() && mario.getVelX() < 0) {
            mario.setVelX(0);
            mario.setX(engine.getCameraLocation().getX());
        }

        if(marioDies) {
            resetCurrentMap(engine);
        }
    }

    private void checkEnemyCollisions() {
        ArrayList<brick> bricks = map.getAllBricks();
        ArrayList<enemy> enemies = map.getEnemies();

        for (com.aDeAyme.superMarioBros.model.enemy.enemy enemy : enemies) {
            boolean standsOnBrick = false;

            for (com.aDeAyme.superMarioBros.model.brick.brick brick : bricks) {
                Rectangle enemyBounds = enemy.getLeftBounds();
                Rectangle brickBounds = brick.getRightBounds();

                Rectangle enemyBottomBounds = enemy.getBottomBounds();
                Rectangle brickTopBounds = brick.getTopBounds();

                if (enemy.getVelX() > 0) {
                    enemyBounds = enemy.getRightBounds();
                    brickBounds = brick.getLeftBounds();
                }

                if (enemyBounds.intersects(brickBounds)) {
                    enemy.setVelX(-enemy.getVelX());
                }

                if (enemyBottomBounds.intersects(brickTopBounds)){
                    enemy.setFalling(false);
                    enemy.setVelY(0);
                    enemy.setY(brick.getY()-enemy.getDimension().height);
                    standsOnBrick = true;
                }
            }

            if(enemy.getY() + enemy.getDimension().height > map.getBottomBorder()){
                enemy.setFalling(false);
                enemy.setVelY(0);
                enemy.setY(map.getBottomBorder()-enemy.getDimension().height);
            }

            if (!standsOnBrick && enemy.getY() < map.getBottomBorder()){
                enemy.setFalling(true);
            }
        }
    }

    private void checkPrizeCollision() {
        ArrayList<prize> prizes = map.getRevealedPrizes();
        ArrayList<brick> bricks = map.getAllBricks();

        for (com.aDeAyme.superMarioBros.model.prize.prize prize : prizes) {
            if (prize instanceof boostItem) {
                boostItem boost = (boostItem) prize;
                Rectangle prizeBottomBounds = boost.getBottomBounds();
                Rectangle prizeRightBounds = boost.getRightBounds();
                Rectangle prizeLeftBounds = boost.getLeftBounds();
                boost.setFalling(true);

                for (com.aDeAyme.superMarioBros.model.brick.brick brick : bricks) {
                    Rectangle brickBounds;

                    if (boost.isFalling()) {
                        brickBounds = brick.getTopBounds();

                        if (brickBounds.intersects(prizeBottomBounds)) {
                            boost.setFalling(false);
                            boost.setVelY(0);
                            boost.setY(brick.getY() - boost.getDimension().height + 1);
                            if (boost.getVelX() == 0)
                                boost.setVelX(2);
                        }
                    }

                    if (boost.getVelX() > 0) {
                        brickBounds = brick.getLeftBounds();

                        if (brickBounds.intersects(prizeRightBounds)) {
                            boost.setVelX(-boost.getVelX());
                        }
                    } else if (boost.getVelX() < 0) {
                        brickBounds = brick.getRightBounds();

                        if (brickBounds.intersects(prizeLeftBounds)) {
                            boost.setVelX(-boost.getVelX());
                        }
                    }
                }

                if (boost.getY() + boost.getDimension().height > map.getBottomBorder()) {
                    boost.setFalling(false);
                    boost.setVelY(0);
                    boost.setY(map.getBottomBorder() - boost.getDimension().height);
                    if (boost.getVelX() == 0)
                        boost.setVelX(2);
                }

            }
        }
    }

    private void checkPrizeContact(game_engine engine) {
        ArrayList<prize> prizes = map.getRevealedPrizes();
        ArrayList<game_object> toBeRemoved = new ArrayList<>();

        Rectangle marioBounds = getMario().getBounds();
        for(com.aDeAyme.superMarioBros.model.prize.prize prize : prizes){
            Rectangle prizeBounds = prize.getBounds();
            if (prizeBounds.intersects(marioBounds)) {
                prize.onTouch(getMario(), engine);
                toBeRemoved.add((game_object) prize);
            } else if(prize instanceof coin){
                prize.onTouch(getMario(), engine);
            }
        }

        removeObjects(toBeRemoved);
    }

    private void checkFireballContact() {
        ArrayList<fireball> fireballs = map.getFireballs();
        ArrayList<enemy> enemies = map.getEnemies();
        ArrayList<brick> bricks = map.getAllBricks();
        ArrayList<game_object> toBeRemoved = new ArrayList<>();

        for(com.aDeAyme.superMarioBros.model.hero.fireball fireball : fireballs){
            Rectangle fireballBounds = fireball.getBounds();

            for(com.aDeAyme.superMarioBros.model.enemy.enemy enemy : enemies){
                Rectangle enemyBounds = enemy.getBounds();
                if (fireballBounds.intersects(enemyBounds)) {
                    acquirePoints(100);
                    toBeRemoved.add(enemy);
                    toBeRemoved.add(fireball);
                }
            }

            for(com.aDeAyme.superMarioBros.model.brick.brick brick : bricks){
                Rectangle brickBounds = brick.getBounds();
                if (fireballBounds.intersects(brickBounds)) {
                    toBeRemoved.add(fireball);
                }
            }
        }

        removeObjects(toBeRemoved);
    }

    private void removeObjects(ArrayList<game_object> list){
        if(list == null)
            return;

        for(game_object object : list){
            if(object instanceof fireball){
                map.removeFireball((fireball)object);
            }
            else if(object instanceof enemy){
                map.removeEnemy((enemy)object);
            }
            else if(object instanceof coin || object instanceof boostItem){
                map.removePrize((prize)object);
            }
        }
    }

    public void addRevealedBrick(ordinary_brick ordinary_brick) {
        map.addRevealedBrick(ordinary_brick);
    }

    public void updateTime(){
        if(map != null)
            map.updateTime(1);
    }

    public int getRemainingTime() {
        return (int)map.getRemainingTime();
    }
}
