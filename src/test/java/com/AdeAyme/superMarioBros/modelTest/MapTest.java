package com.aDeAyme.superMarioBros.modelTest;
import com.aDeAyme.superMarioBros.controller.Difficulty;
import com.aDeAyme.superMarioBros.model.brick.Brick;
import com.aDeAyme.superMarioBros.model.EndFlag;
import com.aDeAyme.superMarioBros.model.Map;
import com.aDeAyme.superMarioBros.model.brick.GroundBrick;
import com.aDeAyme.superMarioBros.model.brick.OrdinaryBrick;
import com.aDeAyme.superMarioBros.model.enemy.Goomba;
import com.aDeAyme.superMarioBros.model.hero.Fireball;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.model.prize.Coin;
import com.aDeAyme.superMarioBros.model.prize.SuperMushroom;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class MapTest {
    @Test
    public void addAndGetEnemiesTest(){
        Map map = new Map(0.0,null);
        Goomba goomba= new Goomba(0,0,null);
        map.addEnemy(goomba);
        assertSame(map.getEnemies().get(0), goomba);
        map.removeEnemy(goomba);
        assertEquals(0, map.getEnemies().toArray().length,0.0);
    }
    @Test
    public void addGetAndRemoveFireballsTest(){
        Map map = new Map(0.0,null);
        Fireball fireball= new Fireball(0,0,null, true);
        map.addFireball(fireball);
        assertSame(map.getFireballs().get(0), fireball);
        map.removeFireball(fireball);
        assertEquals(0, map.getFireballs().toArray().length,0.0);
    }
    @Test
    public void addGetAndRemoveRevealedPrizeTest(){
        Map map = new Map(0.0,null);
        Coin coin = new Coin(0,0,null,0);
        map.addRevealedPrize(coin);
        assertSame(map.getRevealedPrizes().get(0), coin);
        map.removePrize(coin);
        assertEquals(0, map.getRevealedPrizes().toArray().length,0.0);
    }
    @Test
    public void addAndGetBricksTest(){
        Map map = new Map(0.0,null);
        OrdinaryBrick brick =new OrdinaryBrick(0,0,null);
        map.addBrick(brick);
        GroundBrick ground = new GroundBrick(0,0,null);
        map.addGroundBrick(ground);
        ArrayList<Brick> Bricks = map.getAllBricks();
        assertSame(Bricks.get(0), brick);
        assertSame(Bricks.get(1), ground);
    }
    @Test
    public void setAndGetEndPointTest(){
        Map map = new Map(0.0,null);
        EndFlag flag = new EndFlag(0,0,null);
        map.setEndPoint(flag);
        assertSame(map.getEndPoint(),flag);
    }
    @Test
    public void setAndGetPathTest(){
        Map map = new Map(0.0,null);
        String path = "aaa";
        map.setPath(path);
        assertSame(map.getPath(),path);
    }
    @Test
    public void updateLocationTest(){
        Map map = new Map(0.0,null);
        //creacion de elementos
        Mario mario = new Mario(0,0, new Difficulty());
        Goomba goomba =new Goomba(0,0,null);
        Coin coin =new Coin(0,0,null,0);
        coin.reveal(); //reveal == true
        SuperMushroom mushroom = new SuperMushroom(0,0,null);
        mushroom.reveal();
        Fireball fireball =new Fireball(0, 0, null, true);
        OrdinaryBrick brick = new OrdinaryBrick(0,0,null);
        EndFlag flag = new EndFlag(0,0,null);
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
        Map map = new Map(0.0,null);
        assertEquals(720 - 96, map.getBottomBorder(),0.0);
    }
    @Test
    public void updateTimeTest(){
        Map map = new Map(2,null);
        map.updateTime(1);
        assertEquals(1,map.getRemainingTime(),0.0);
    }
    @Test
    public void isTimeOverTest(){
        Map map = new Map(4,null);
        assertFalse(map.isTimeOver());
        map.updateTime(6);
        assertTrue(map.isTimeOver());
    }

}
