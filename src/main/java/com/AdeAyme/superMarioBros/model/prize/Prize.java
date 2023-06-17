package com.AdeAyme.superMarioBros.model.prize;

import com.AdeAyme.superMarioBros.manager.GameEngine;
import com.AdeAyme.superMarioBros.manager.MapManager;
import com.AdeAyme.superMarioBros.model.hero.Mario;

import java.awt.*;

public interface Prize {

    int getPoint();

    void reveal();

    Rectangle getBounds();

    void onTouch(Mario mario, GameEngine engine);

}
