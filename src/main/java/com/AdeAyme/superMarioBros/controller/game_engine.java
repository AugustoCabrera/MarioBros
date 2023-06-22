package com.aDeAyme.superMarioBros.controller;
import com.aDeAyme.superMarioBros.model.hero.mario;
import com.aDeAyme.superMarioBros.view.image_loader;
import com.aDeAyme.superMarioBros.view.start_screen_selection;
import com.aDeAyme.superMarioBros.view.UI_manager;
import com.aDeAyme.superMarioBros.controller.camera;

import javax.swing.*;
import java.awt.*;

//Realiza la ejecucion del videojuego
public class game_engine implements Runnable {
    private final static int WIDTH = 1268, HEIGHT = 708; //Dimensiones de la ventana del videojuego
    private map_manager map_manager;        //Administrador del mapa del videojuego
    private UI_manager uiManager;           //Administrador de las imagenes del videojuego
    private sound_manager sound_manager;    //Administrador del sonido del videojuego
    private game_status game_status;        //Enumerado del estado del videojuego
    private boolean isRunning;              //Determina si el videojuego esta corriendo
    private camera camera;                  //Objeto que realiza el seguimiento de Mario en el videojuego
    private image_loader image_loader;      //Cargador de imagenes del videojuego
    private Thread thread;                  //Hilo que realiza la ejecucion del juego

    //Enumerado de la pantalla actual en la que se encuentra el programa del videojuego
    private start_screen_selection start_screen_selection = com.aDeAyme.superMarioBros.view.start_screen_selection.START_GAME;
    private int selectedMap = 0;            //Mapa seleccionado
    private boolean Test= false;            //Determina si se encuentra o no (true o false respectivamente) en un test

    public game_engine(String msj){           //Constructor para uso SOLO en test
        System.out.println(msj);
        this.map_manager = new map_manager();
        this.image_loader = new image_loader();
        this.camera= new camera();
        map_manager.createMap(image_loader,"Map 1.png");
        this.sound_manager =new sound_manager();
        Test = true;
    }
    private game_engine() {
        init();
    }         //Constructor para uso generico del juego

    private void init() {                     //Carga de los atributos de la clase
        image_loader = new image_loader();
        input_manager input_manager = new input_manager(this);
        game_status = game_status.START_SCREEN; //estoy en el menu de seleccion
        System.out.println("Estoy en el estado " + getGameStatus());
        camera = new camera();
        uiManager = new UI_manager(this, WIDTH, HEIGHT);
        sound_manager = new sound_manager();
        map_manager = new map_manager();

        JFrame frame = new JFrame("Super Mario Bros ✨🍄"); //Jframe es la Clase consola emergente
        frame.add(uiManager);
        frame.addKeyListener(input_manager);
        frame.addMouseListener(input_manager);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        if(!Test) start(); //llamo al run
    }

    private synchronized void start() {
        if (isRunning)
            return;

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void reset(){
        resetCamera();
        setGameStatus(game_status.START_SCREEN);
        System.out.println("Estoy en el estado " + getGameStatus());
    }

    public void resetCamera(){
        camera = new camera();
        if(!Test){
            sound_manager.restartBackground(); //vuelve a reproducir el Himno
        }
    }

    public void selectMapViaMouse() {
        System.out.println("Hice click para elegir mapa");
        String path = uiManager.selectMapViaMouse(uiManager.getMousePosition());
        if (path != null) {
            createMap(path);
        }
    }

    public void selectMapViaKeyboard(){
        System.out.println("Aprete ENTER para elegir mapa");
        String path = uiManager.selectMapViaKeyboard(selectedMap);
        if (path != null) {
            createMap(path);
        }
    }

    public void changeSelectedMap(boolean up){
        System.out.println("Estoy moviendo el cursor para seleccionar el mapa");
        selectedMap = uiManager.changeSelectedMap(selectedMap, up);
    }

    private void createMap(String path) {
        System.out.println("Se creo el mapa");
        boolean loaded = map_manager.createMap(image_loader, path);
        if(loaded){
            setGameStatus(game_status.RUNNING);
            if(!Test)
            {
                sound_manager.restartBackground(); //resetea el himno
            }
        }

        else
            setGameStatus(game_status.START_SCREEN);
    }

    @Override
    public void run() { //llamo al hilo (Concurrente)
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (isRunning && !thread.isInterrupted()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (game_status == game_status.RUNNING) {
                    gameLoop();
                }
                delta--;
            }
            render();

            if(game_status != game_status.RUNNING) {
                timer = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                map_manager.updateTime();
            }
        }
    }

    private void render() {
        uiManager.repaint();
    }

    private void gameLoop() {
        updateLocations();
        checkCollisions();
        updateCamera();

        if (isGameOver()) {
            setGameStatus(game_status.GAME_OVER);
        }

        int missionPassed = passMission();
        if(missionPassed > -1){
            map_manager.acquirePoints(missionPassed);
            //setGameStatus(GameStatus.MISSION_PASSED);
        } else if(map_manager.endLevel())
            setGameStatus(game_status.MISSION_PASSED);
    }

    private void updateCamera() {
        mario mario = map_manager.getMario();
        double marioVelocityX = mario.getVelX();
        double shiftAmount = 0;

        if (marioVelocityX > 0 && mario.getX() - 600 > camera.getX()) {
            shiftAmount = marioVelocityX;
        }

        camera.moveCam(shiftAmount, 0);
    }

    private void updateLocations() {
        map_manager.updateLocations();
    }

    private void checkCollisions() {
        map_manager.checkCollisions(this);
    }

    public void receiveInput(button_action input) {

        if (game_status == game_status.START_SCREEN) {
            if (input == button_action.SELECT && start_screen_selection == start_screen_selection.START_GAME) {
                startGame();
            } else if (input == button_action.SELECT && start_screen_selection == start_screen_selection.VIEW_ABOUT) {
                setGameStatus(game_status.ABOUT_SCREEN);
            } else if (input == button_action.SELECT && start_screen_selection == start_screen_selection.VIEW_HELP) {
                setGameStatus(game_status.HELP_SCREEN);
            } else if (input == button_action.GO_UP) {
                selectOption(true);
            } else if (input == button_action.GO_DOWN) {
                selectOption(false);
            }
        }
        else if(game_status == game_status.MAP_SELECTION){
            if(input == button_action.SELECT){
                selectMapViaKeyboard();
            }
            else if(input == button_action.GO_UP){
                changeSelectedMap(true);
            }
            else if(input == button_action.GO_DOWN){
                changeSelectedMap(false);
            }
        } else if (game_status == game_status.RUNNING) {
            mario mario = map_manager.getMario();
            if (input == button_action.JUMP) {
                mario.jump(this);
            } else if (input == button_action.M_RIGHT) {
                mario.move(true, camera);
            } else if (input == button_action.M_LEFT) {
                mario.move(false, camera);
            } else if (input == button_action.ACTION_COMPLETED) {
                mario.setVelX(0);
            } else if (input == button_action.FIRE) {
                map_manager.fire(this);
            } else if (input == button_action.PAUSE_RESUME) {
                pauseGame();
            }
        } else if (game_status == game_status.PAUSED) {
            if (input == button_action.PAUSE_RESUME) {
                pauseGame();
            }
        } else if(game_status == game_status.GAME_OVER && input == button_action.GO_TO_START_SCREEN){
            reset();
        } else if(game_status == game_status.MISSION_PASSED && input == button_action.GO_TO_START_SCREEN){
            reset();
        }

        if(input == button_action.GO_TO_START_SCREEN){
            setGameStatus(game_status.START_SCREEN);
        }
    }

    private void selectOption(boolean selectUp) {
        start_screen_selection = start_screen_selection.select(selectUp);
    }

    private void startGame() {
        if (game_status != game_status.GAME_OVER) {
            setGameStatus(game_status.MAP_SELECTION);
        }
    }

    private void pauseGame() {
        if (game_status == game_status.RUNNING) {
            setGameStatus(game_status.PAUSED);
            if(!Test){
                sound_manager.pauseBackground();
            }
        } else if (game_status == game_status.PAUSED) {
            setGameStatus(game_status.RUNNING);
            if(!Test){
                sound_manager.resumeBackground();
            }
        }

    }

    public void shakeCamera(){
        camera.shakeCamera();
    }

    private boolean isGameOver() {
        if(game_status == game_status.RUNNING)
            return map_manager.isGameOver();
        return false;
    }

    public image_loader getImageLoader() {
        return image_loader;
    }

    public game_status getGameStatus() {
        return game_status;
    }

    public start_screen_selection getStartScreenSelection() {
        return start_screen_selection;
    }

    public void setGameStatus(game_status game_status) {
        this.game_status = game_status;
    }

    public int getScore() {
        return map_manager.getScore();
    }

    public int getRemainingLives() {
        return map_manager.getRemainingLives();
    }

    public int getCoins() {
        return map_manager.getCoins();
    }

    public int getSelectedMap() {
        return selectedMap;
    }

    public void drawMap(Graphics2D g2) {
        map_manager.drawMap(g2);
    }

    public Point getCameraLocation() {
        return new Point((int)camera.getX(), (int)camera.getY());
    }

    private int passMission(){
        return map_manager.passMission();
    }

    public void playCoin() {
        if(!Test){
            sound_manager.playCoin();
        }
    }

    public void playOneUp() {
        if (!Test) {
            sound_manager.playOneUp();
        }
    }

    public void playSuperMushroom() {
        if (!Test){
            sound_manager.playSuperMushroom();
        }
    }

    public void playMarioDies() {
        if (!Test){
            sound_manager.playMarioDies();
        }
    }

    public void playJump() {
        if(!Test){
            sound_manager.playJump();
        }
    }

    public void playFireFlower() {
        if(!Test){
            sound_manager.playFireFlower();
        }
    }

    public void playFireball() {
        if(!Test){
            sound_manager.playFireball();
        }
    }

    public void playStomp() {
        if(!Test){
            sound_manager.playStomp();
        }
    }

    public map_manager getMapManager() {
        return map_manager;
    }

    public static void main(String... args) {
        new game_engine();
    }

    public int getRemainingTime() {
        return map_manager.getRemainingTime();
    }
}
