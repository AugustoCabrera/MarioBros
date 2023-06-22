package com.aDeAyme.superMarioBros.model.brick;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.controller.map_manager;
import com.aDeAyme.superMarioBros.model.hero.mario_super;
import com.aDeAyme.superMarioBros.model.prize.prize;
import com.aDeAyme.superMarioBros.view.animation;
import com.aDeAyme.superMarioBros.view.image_loader;

import java.awt.image.BufferedImage;

public class ordinary_brick extends brick {

    private com.aDeAyme.superMarioBros.view.animation animation;
    private boolean breaking;
    private int frames;

    public ordinary_brick(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(true);
        setEmpty(true);

        setAnimation();
        breaking = false;
        frames = animation.getLeftFrames().length;
    }

    private void setAnimation(){
        image_loader image_loader = new image_loader();
        BufferedImage[] leftFrames = image_loader.getBrickFrames();

        animation = new animation(leftFrames, leftFrames);
    }

    @Override
    public prize reveal(game_engine engine){
        map_manager manager = engine.getMapManager();
        if(!(manager.getMario().getMarioForm() instanceof mario_super))
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
