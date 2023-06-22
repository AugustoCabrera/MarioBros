package com.aDeAyme.superMarioBros.model.brick;

import com.aDeAyme.superMarioBros.controller.game_engine;

import java.awt.image.BufferedImage;

public class surprise_brick extends brick {

    private com.aDeAyme.superMarioBros.model.prize.prize prize;

    public surprise_brick(double x, double y, BufferedImage style, com.aDeAyme.superMarioBros.model.prize.prize prize) {
        super(x, y, style);
        setBreakable(false);
        setEmpty(false);
        this.prize = prize;
    }

    @Override
    public com.aDeAyme.superMarioBros.model.prize.prize reveal(game_engine engine){
        BufferedImage newStyle = engine.getImageLoader().loadImage("/sprite.png");
        newStyle = engine.getImageLoader().getSubImage(newStyle, 1, 2, 48, 48);

        if(prize != null){
            prize.reveal();
        }

        setEmpty(true);
        setStyle(newStyle);

        com.aDeAyme.superMarioBros.model.prize.prize toReturn = this.prize;
        this.prize = null;
        return toReturn;
    }

    @Override
    public com.aDeAyme.superMarioBros.model.prize.prize getPrize(){
        return prize;
    }
}
