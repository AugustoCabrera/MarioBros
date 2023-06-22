package com.aDeAyme.superMarioBros.model.prize;

import com.aDeAyme.superMarioBros.controller.game_engine;
import com.aDeAyme.superMarioBros.model.hero.mario;

import java.awt.*;

public interface prize {

    int getPoint();

    void reveal();

    Rectangle getBounds();

    void onTouch(mario mario, game_engine engine);

}
