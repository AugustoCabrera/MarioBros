package com.aDeAyme.superMarioBros.controller;
import com.aDeAyme.superMarioBros.model.GameObject;
import com.aDeAyme.superMarioBros.model.Map;
import com.aDeAyme.superMarioBros.model.brick.Brick;
import com.aDeAyme.superMarioBros.model.brick.OrdinaryBrick;
import com.aDeAyme.superMarioBros.model.enemy.Enemy;
import com.aDeAyme.superMarioBros.model.hero.Fireball;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.prize.BoostItem;
import com.aDeAyme.superMarioBros.model.prize.Coin;
import com.aDeAyme.superMarioBros.model.prize.Prize;
import com.aDeAyme.superMarioBros.view.ImageLoader;

import java.awt.*;
import java.util.ArrayList;
//Administrador del mapa

public class MapManager {
    private Map map;    //Referencia del mapa a adminsitrar
    private Difficulty difficulty;      //Referencia de la dificultad del mapa
    public MapManager() {difficulty = new Difficulty();}

    public void updateLocations() {     //Actualizacion de la localizacion del mapa
        if (map == null) return;
        map.updateLocations();
    }

    public void resetCurrentMap(GameEngine engine) {        //Resetea el mapa actual que se esta ejecutando
        Mario mario = getMario();
        mario.resetLocation();
        engine.resetCamera();
        createMap(engine.getImageLoader(), map.getPath());
        map.setMario(mario);
    }

    public boolean createMap(ImageLoader loader, String path) {     //Crea un mapa mediante un MapCreator
        MapCreator MapCreator = new MapCreator(loader, difficulty);
        map = MapCreator.createMap("/maps/" + path, 400); //Especificaciones del mapa a crear
        return map != null;
    }

    public void acquirePoints(int point) {      //Aumento de puntos de Mario en el mapa
        map.getMario().acquirePoints(point);
    }

    public Mario getMario() {
        return map.getMario();
    }   //Devuelve al Mario en ejecucion

    public void fire(GameEngine engine) {       //Disparo de bolas de fuego por parte de Mario
        Fireball fireball = getMario().fire();
        if (fireball != null) {
            map.addFireball(fireball);
            engine.playFireball(); //sonido del lanzamiento de fuego
        }
    }

    public boolean isGameOver() {
        return getMario().getRemainingLives() == 0 || map.isTimeOver();
    } //Determina si el juego termino

    public int getScore() {
        return getMario().getPoints();
    } //Devuelve los puntos de Mario

    public int getRemainingLives() {
        return getMario().getRemainingLives();
    } //Devuelve las vidas de Mario

    public int getCoins() {
        return getMario().getCoins();
    } //Devuelve las monedas de Mario

    public void drawMap(Graphics2D g2) {
        map.drawMap(g2);
    } //Dibuja el mapa

    public int passMission() { //Establece si se ha pasado la mision cuando devuelve un entero > 0
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
    } //Establece si se ha llegado al final del nivel

    public void checkCollisions(GameEngine engine) { //Actualiza sobre las colisiones entre los objetos en del mapa
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

    private void checkBottomCollisions(GameEngine engine) { //Actualiza sobre las colisiones hacia abajo entre los objetos del mapa
        Mario mario = getMario();
        ArrayList<Brick> Bricks = map.getAllBricks();
        ArrayList<Enemy> enemies = map.getEnemies();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        Rectangle marioBottomBounds = mario.getBottomBounds();

        if (!mario.isJumping())
            mario.setFalling(true);

        for (Brick brick : Bricks) {
            Rectangle brickTopBounds = brick.getTopBounds();
            if (marioBottomBounds.intersects(brickTopBounds)) {
                mario.setY(brick.getY() - mario.getDimension().height + 1);
                mario.setFalling(false);
                mario.setVelY(0);
            }
        }

        for (Enemy enemy : enemies) {
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

    private void checkTopCollisions(GameEngine engine) {   //Actualiza sobre las colisiones desde arriba entre los objetos del mapa
        Mario mario = getMario();
        ArrayList<Brick> Bricks = map.getAllBricks();

        Rectangle marioTopBounds = mario.getTopBounds();
        for (Brick brick : Bricks) {
            Rectangle brickBottomBounds = brick.getBottomBounds();
            if (marioTopBounds.intersects(brickBottomBounds)) {
                mario.setVelY(0);
                mario.setY(brick.getY() + brick.getDimension().height);
                Prize prize = brick.reveal(engine);
                if(prize != null)
                    map.addRevealedPrize(prize);
            }
        }
    }

    private void checkMarioHorizontalCollision(GameEngine engine){ //Actualiza sobre las colisiones horizontales entre los objetos del mapa
        Mario mario = getMario();
        ArrayList<Brick> Bricks = map.getAllBricks();
        ArrayList<Enemy> enemies = map.getEnemies();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        boolean marioDies = false;
        boolean toRight = mario.getToRight();

        Rectangle marioBounds = toRight ? mario.getRightBounds() : mario.getLeftBounds();

        for (Brick brick : Bricks) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (marioBounds.intersects(brickBounds)) {
                mario.setVelX(0);
                if(toRight)
                    mario.setX(brick.getX() - mario.getDimension().width);
                else
                    mario.setX(brick.getX() + brick.getDimension().width);
            }
        }

        for(Enemy enemy : enemies){
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

    private void checkEnemyCollisions() {       //Actualiza sobre las colisiones con los enemigos del mapa
        ArrayList<Brick> Bricks = map.getAllBricks();
        ArrayList<Enemy> enemies = map.getEnemies();

        for (Enemy enemy : enemies) {
            boolean standsOnBrick = false;

            for (Brick brick : Bricks) {
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

    private void checkPrizeCollision() {    //Actualiza sobre las colisiones con los premios
        ArrayList<Prize> Prizes = map.getRevealedPrizes();
        ArrayList<Brick> Bricks = map.getAllBricks();

        for (Prize prize : Prizes) {
            if (prize instanceof BoostItem) {
                BoostItem boost = (BoostItem) prize;
                Rectangle prizeBottomBounds = boost.getBottomBounds();
                Rectangle prizeRightBounds = boost.getRightBounds();
                Rectangle prizeLeftBounds = boost.getLeftBounds();
                boost.setFalling(true);

                for (Brick brick : Bricks) {
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

    private void checkPrizeContact(GameEngine engine) {     //Actualiza sobre las colisiones con premios ya tocados
        ArrayList<Prize> Prizes = map.getRevealedPrizes();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        Rectangle marioBounds = getMario().getBounds();
        for(Prize prize : Prizes){
            Rectangle prizeBounds = prize.getBounds();
            if (prizeBounds.intersects(marioBounds)) {
                prize.onTouch(getMario(), engine);
                toBeRemoved.add((GameObject) prize);
            } else if(prize instanceof Coin){
                prize.onTouch(getMario(), engine);
            }
        }

        removeObjects(toBeRemoved);
    }

    private void checkFireballContact() {       //Actualiza sobre el contacto de una bola de fuego con los elementos del mapa
        ArrayList<Fireball> Fireballs = map.getFireballs();
        ArrayList<Enemy> enemies = map.getEnemies();
        ArrayList<Brick> Bricks = map.getAllBricks();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        for(Fireball fireball : Fireballs){
            Rectangle fireballBounds = fireball.getBounds();

            for(Enemy enemy : enemies){
                Rectangle enemyBounds = enemy.getBounds();
                if (fireballBounds.intersects(enemyBounds)) {
                    acquirePoints(100);
                    toBeRemoved.add(enemy);
                    toBeRemoved.add(fireball);
                }
            }

            for(Brick brick : Bricks){
                Rectangle brickBounds = brick.getBounds();
                if (fireballBounds.intersects(brickBounds)) {
                    toBeRemoved.add(fireball);
                }
            }
        }

        removeObjects(toBeRemoved);
    }

    private void removeObjects(ArrayList<GameObject> list){         //Remueve objetos del mapa
        if(list == null)
            return;

        for(GameObject object : list){
            if(object instanceof Fireball){
                map.removeFireball((Fireball)object);
            }
            else if(object instanceof Enemy){
                map.removeEnemy((Enemy)object);
            }
            else if(object instanceof Coin || object instanceof BoostItem){
                map.removePrize((Prize)object);
            }
        }
    }

    //Agrega un bloque a la lista de bloques revelados del mapa
    public void addRevealedBrick(OrdinaryBrick OrdinaryBrick) {
        map.addRevealedBrick(OrdinaryBrick);
    }

    public void updateTime(){   //Actualiza el tiempo de ejecucion del mapa
        if(map != null)
            map.updateTime(1);
    }

    public int getRemainingTime() {
        return (int)map.getRemainingTime();
    } //Devuelve el tiempo restante de ejecucion
}
