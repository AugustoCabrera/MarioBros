package com.AdeAyme.superMarioBros.model.brick;

import com.AdeAyme.superMarioBros.manager.GameEngine;
import com.AdeAyme.superMarioBros.manager.MapManager;
import com.AdeAyme.superMarioBros.model.Map;
import com.AdeAyme.superMarioBros.model.hero.MarioSuper;
import com.AdeAyme.superMarioBros.model.prize.Prize;
import com.AdeAyme.superMarioBros.view.Animation;
import com.AdeAyme.superMarioBros.view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OrdinaryBrick extends Brick {

    private Animation animation;
    private boolean breaking;
    private int frames;

    public OrdinaryBrick(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(true);
        setEmpty(true);

        setAnimation();
        breaking = false;
        frames = animation.getLeftFrames().length;
    }

    private void setAnimation(){
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getBrickFrames();

        animation = new Animation(leftFrames, leftFrames);
    }

    @Override
    public Prize reveal(GameEngine engine){
        MapManager manager = engine.getMapManager();
        if(!(manager.getMario().getMarioForm() instanceof MarioSuper))
            return null;
        breaking = true;
        manager.addRevealedBrick(this);

        double newX = getX() - 27, newY = getY() - 27;
        setLocation(newX, newY);

        return null;
    }

    public int getFrames(){
        return frames;
    }

    public void animate(){
        if(breaking){
            setStyle(animation.animate(3, true));
            frames--;
        }
    }
}
