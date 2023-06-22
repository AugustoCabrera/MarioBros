package com.aDeAyme.superMarioBros.controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Administra las entradas por perifericos (teclado y mouse)
public class InputManager implements KeyListener, MouseListener{

    private GameEngine engine;      //Referencia al "motor" de juego

    InputManager(GameEngine engine) {
        this.engine = engine; }

    @Override
    public void keyPressed(KeyEvent event) {        //Actualiza el estado del juego segun la tecla presionada
        int keyCode = event.getKeyCode();
        GameStatus status = engine.getGameStatus();
        ButtonAction currentAction = ButtonAction.NO_ACTION;

            //Pregunto si lo que se presiono es ← ↑ → ↓ SPACE SCAPE ENTER
        if (keyCode == KeyEvent.VK_UP) {
            if(status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION) //Si el mapa todavia no se selecciono o todavia estoy en el menu
                currentAction = ButtonAction.GO_UP;
            else
                currentAction = ButtonAction.JUMP;  //si ya estoy adentro del juego
        }

        else if(keyCode == KeyEvent.VK_DOWN){
            if(status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION) //Si el mapa todavia no se selecciono o todavia estoy en el menu
                currentAction = ButtonAction.GO_DOWN;
        }

        else if (keyCode == KeyEvent.VK_RIGHT) {
            currentAction = ButtonAction.M_RIGHT;
        }

        else if (keyCode == KeyEvent.VK_LEFT) {
            currentAction = ButtonAction.M_LEFT;
        }

        else if (keyCode == KeyEvent.VK_ENTER) {
            currentAction = ButtonAction.SELECT;
        }

        else if (keyCode == KeyEvent.VK_ESCAPE) {
            if(status == GameStatus.RUNNING || status == GameStatus.PAUSED )
                currentAction = ButtonAction.PAUSE_RESUME;
            else
                currentAction = ButtonAction.GO_TO_START_SCREEN;
        }

        else if (keyCode == KeyEvent.VK_SPACE){
            currentAction = ButtonAction.FIRE;
        }

        notifyInput(currentAction);
    }

    @Override
    public void mousePressed(MouseEvent e) {      //Actualiza el estado del juego segun el punto de la pantalla seleccionado via mouse
        if(engine.getGameStatus() == GameStatus.MAP_SELECTION){
            engine.selectMapViaMouse();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {       //Actualiza el estado del juego segun la tecla que se deja de presionar
        if(event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_LEFT)
            notifyInput(ButtonAction.ACTION_COMPLETED);
    }

    private void notifyInput(ButtonAction action) {   //Notifica una entrada por teclado
        if(action != ButtonAction.NO_ACTION)
            engine.receiveInput(action);
    }

    //Metodos implementados por la implementacion de KeyListener y MouseListener (sin utilizar)
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
