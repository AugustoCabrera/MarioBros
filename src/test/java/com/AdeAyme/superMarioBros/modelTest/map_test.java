package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.controller.difficulty;
import com.aDeAyme.superMarioBros.model.end_flag;
import com.aDeAyme.superMarioBros.model.map;
import com.aDeAyme.superMarioBros.model.brick.ground_brick;
import com.aDeAyme.superMarioBros.model.brick.ordinary_brick;
import com.aDeAyme.superMarioBros.model.enemy.goomba;
import com.aDeAyme.superMarioBros.model.hero.fireball;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.model.prize.coin;
import com.aDeAyme.superMarioBros.model.prize.super_mushroom;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class map_test {
    @Test
    public void addAndGetEnemiesTest(){
        map map = new map(0.0,null);
        goomba goomba= new goomba(0,0,null);
        map.addEnemy(goomba);
        assertSame(map.getEnemies().get(0), goomba);
        map.removeEnemy(goomba);
        assertEquals(0, map.getEnemies().toArray().length,0.0);
    }
    @Test
    public void addGetAndRemoveFireballsTest(){
        map map = new map(0.0,null);
        fireball fireball= new fireball(0,0,null, true);
        map.addFireball(fireball);
        assertSame(map.getFireballs().get(0), fireball);
        map.removeFireball(fireball);
        assertEquals(0, map.getFireballs().toArray().length,0.0);
    }
    @Test
    public void addGetAndRemoveRevealedPrizeTest(){
        map map = new map(0.0,null);
        coin coin = new coin(0,0,null,0);
        map.addRevealedPrize(coin);
        assertSame(map.getRevealedPrizes().get(0), coin);
        map.removePrize(coin);
        assertEquals(0, map.getRevealedPrizes().toArray().length,0.0);
    }
    @Test
    public void addAndGetBricksTest(){
        map map = new map(0.0,null);
        ordinary_brick brick =new ordinary_brick(0,0,null);
        map.addBrick(brick);
        ground_brick ground = new ground_brick(0,0,null);
        map.addGroundBrick(ground);
        ArrayList<com.aDeAyme.superMarioBros.model.brick.brick> bricks = map.getAllBricks();
        assertSame(bricks.get(0), brick);
        assertSame(bricks.get(1), ground);
    }
    @Test
    public void setAndGetEndPointTest(){
        map map = new map(0.0,null);
        end_flag flag = new end_flag(0,0,null);
        map.setEndPoint(flag);
        assertSame(map.getEndPoint(),flag);
    }
    @Test
    public void setAndGetPathTest(){
        map map = new map(0.0,null);
        String path = "aaa";
        map.setPath(path);
        assertSame(map.getPath(),path);
    }
    @Test
    public void updateLocationTest(){
        map map = new map(0.0,null);
        //creacion de elementos
        mario mario = new mario(0,0, new difficulty());
        goomba goomba =new goomba(0,0,null);
        coin coin =new coin(0,0,null,0);
        coin.reveal(); //reveal == true
        super_mushroom mushroom = new super_mushroom(0,0,null);
        mushroom.reveal();
        fireball fireball =new fireball(0, 0, null, true);
        ordinary_brick brick = new ordinary_brick(0,0,null);
        end_flag flag = new end_flag(0,0,null);
        flag.setDimension(new Dimension(30,30));
        flag.setTouched(true); //bandera es tocada
        flag.setY(600); //bandera en alto
        //se agregan los elementos al mapa
        map.setMario(mario);
        map.addEnemy(goomba);
        map.addRevealedPrize(coin);
        map.addRevealedPrize(mushroom);
        map.addFireball(fireball);
        map.addBrick(brick);
        map.setEndPoint(flag);
        map.updateLocations();
        //validacion de que los elementos actualizaron sus ubicaciones
        assertEquals(0.38, mario.getVelY(),0.0); //tiene falling en true
        assertEquals(0, goomba.getVelY(), 0.0); //tiene falling y jumping en false
        assertEquals(-5, coin.getY(), 0.0); //con reveal=true (linea 62)
        assertEquals(0.38, mushroom.getVelY(),0.0); //tiene falling y jumping en false
        assertEquals(0.0, brick.getVelY(),0.0); //tiene falling y jumping en false
        assertFalse(flag.isFalling());
    }
    @Test
    public void getBottomBorderTest(){
        map map = new map(0.0,null);
        assertEquals(720 - 96, map.getBottomBorder(),0.0);
    }
    @Test
    public void updateTimeTest(){
        map map = new map(2,null);
        map.updateTime(1);
        assertEquals(1,map.getRemainingTime(),0.0);
    }
    @Test
    public void isTimeOverTest(){
        map map = new map(4,null);
        assertFalse(map.isTimeOver());
        map.updateTime(6);
        assertTrue(map.isTimeOver());
    }

}
