package com.aDeAyme.superMarioBros.model.prize;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.model.hero.Mario;

import java.awt.*;

//Interfaz que determina el comportamiento y elementos de un premio en el mapa
public interface Prize {
    int getPoint();         //Devuelve los puntos que contiene

    void reveal();          //Determina si se han revelado o no los puntos

    Rectangle getBounds();  //Devuelve los limites de contacto del objeto

    void onTouch(Mario mario, GameEngine engine);       //Establece que Mario tomo el objeto

}
