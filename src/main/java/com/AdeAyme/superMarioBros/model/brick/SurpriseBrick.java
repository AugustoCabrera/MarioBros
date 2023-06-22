package com.aDeAyme.superMarioBros.model.brick;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.prize.Prize;

import java.awt.image.BufferedImage;
//Bloque sorpresa del mapa. Tiene premios adentro

public class SurpriseBrick extends Brick {
    private Prize prize;    //Premio que contiene el bloque
    public SurpriseBrick(double x, double y, BufferedImage style, Prize prize) {
        super(x, y, style);
        setBreakable(false);        //No puede romperse (solo revelarse)
        setEmpty(false);            //No estan vacios
        this.prize = prize;
    }

    @Override
    public Prize reveal(GameEngine engine){     //Se revela el premio que contiene el bloque sorpresa
        BufferedImage newStyle = engine.getImageLoader().loadImage("/sprite.png");
        newStyle = engine.getImageLoader().getSubImage(newStyle, 1, 2, 48, 48);

        if(prize != null){
            prize.reveal();
        }

        setEmpty(true);
        setStyle(newStyle);

        Prize toReturn = this.prize;
        this.prize = null;
        return toReturn;
    }

    @Override
    public Prize getPrize(){
        return prize;
    }   //Devuelve el premio
}
