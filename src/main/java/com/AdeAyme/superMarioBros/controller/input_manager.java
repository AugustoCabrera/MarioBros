package com.aDeAyme.superMarioBros.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class input_manager implements KeyListener, MouseListener{

    private game_engine engine;

    input_manager(game_engine engine) {
        this.engine = engine; }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        game_status status = engine.getGameStatus();
        button_action currentAction = button_action.NO_ACTION;

            //Pregunto si lo que se presiono es ← ↑ → ↓ SPACE SCAPE ENTER
        if (keyCode == KeyEvent.VK_UP) {
            if(status == game_status.START_SCREEN || status == game_status.MAP_SELECTION) //Si el mapa todavia no se selecciono o todavia estoy en el menu
                currentAction = button_action.GO_UP;
            else
                currentAction = button_action.JUMP;  //si ya estoy adentro del juego
        }

        else if(keyCode == KeyEvent.VK_DOWN){
            if(status == game_status.START_SCREEN || status == game_status.MAP_SELECTION) //Si el mapa todavia no se selecciono o todavia estoy en el menu
                currentAction = button_action.GO_DOWN;
        }

        else if (keyCode == KeyEvent.VK_RIGHT) {
            currentAction = button_action.M_RIGHT;
        }

        else if (keyCode == KeyEvent.VK_LEFT) {
            currentAction = button_action.M_LEFT;
        }

        else if (keyCode == KeyEvent.VK_ENTER) {
            currentAction = button_action.SELECT;
        }

        else if (keyCode == KeyEvent.VK_ESCAPE) {
            if(status == game_status.RUNNING || status == game_status.PAUSED )
                currentAction = button_action.PAUSE_RESUME;
            else
                currentAction = button_action.GO_TO_START_SCREEN;

        }

        else if (keyCode == KeyEvent.VK_SPACE){
            currentAction = button_action.FIRE;
        }


        notifyInput(currentAction);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(engine.getGameStatus() == game_status.MAP_SELECTION){  //el mouse solo sirve para cuando estoy en el menu
            engine.selectMapViaMouse();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_LEFT)
            notifyInput(button_action.ACTION_COMPLETED);
    }

    private void notifyInput(button_action action) {
        if(action != button_action.NO_ACTION)
            engine.receiveInput(action);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
