package com.aDeAyme.superMarioBros.model.brick;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.controller.MapManager;
import com.aDeAyme.superMarioBros.model.hero.MarioSuper;
import com.aDeAyme.superMarioBros.model.prize.Prize;
import com.aDeAyme.superMarioBros.view.Animation;
import com.aDeAyme.superMarioBros.view.ImageLoader;

import java.awt.image.BufferedImage;
//Bloque ordinario, rompible para el SuperMario

public class OrdinaryBrick extends Brick {
    private Animation animation;    //Animacion del bloque
    private boolean breaking;       //Determina si se puede romper o no
    private int frames;             //Controla el número de fotogramas

    public OrdinaryBrick(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(true);     //Puede romperse
        setEmpty(true);         //Esta vacia

        setAnimation();
        breaking = false;
        frames = animation.getLeftFrames().length;
    }

    private void setAnimation(){        //Establece al animacion del bloque
        ImageLoader ImageLoader = new ImageLoader();
        BufferedImage[] leftFrames = ImageLoader.getBrickFrames();

        animation = new Animation(leftFrames, leftFrames);
    }

    @Override
    public Prize reveal(GameEngine engine){     //Revela el premio del bloque (no tiene premio, solo se rompe)
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
    }   //Devuelve el frame actual

    public void animate(){      //Anima la ruptura de un bloque
        if(breaking){
            setStyle(animation.animate(3, true));
            frames--;
        }
    }
}
