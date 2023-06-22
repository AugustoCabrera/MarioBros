package com.aDeAyme.superMarioBros.controller;
import com.aDeAyme.superMarioBros.model.hero.Mario;
import com.aDeAyme.superMarioBros.view.ImageLoader;
import com.aDeAyme.superMarioBros.view.StartScreenSelection;
import com.aDeAyme.superMarioBros.view.UIManager;

import javax.swing.*;
import java.awt.*;

//Realiza la ejecucion del videojuego
public class GameEngine implements Runnable {
    private final static int WIDTH = 1268, HEIGHT = 708; //Dimensiones de la ventana del videojuego
    private MapManager MapManager;        //Administrador del mapa del videojuego
    private UIManager uiManager;           //Administrador de las imagenes del videojuego
    private SoundManager SoundManager;    //Administrador del sonido del videojuego
    private GameStatus GameStatus;        //Enumerado del estado del videojuego
    private boolean isRunning;              //Determina si el videojuego esta corriendo
    private Camera camera;                  //Objeto que realiza el seguimiento de Mario en el videojuego
    private ImageLoader ImageLoader;      //Cargador de imagenes del videojuego
    private Thread thread;                  //Hilo que realiza la ejecucion del juego

    //Enumerado de la pantalla actual en la que se encuentra el programa del videojuego
    private StartScreenSelection StartScreenSelection = com.aDeAyme.superMarioBros.view.StartScreenSelection.START_GAME;
    private int selectedMap = 0;            //Mapa seleccionado
    private boolean Test= false;            //Determina si se encuentra o no (true o false respectivamente) en un test

    public GameEngine(String msj){           //Constructor para uso SOLO en test
        System.out.println(msj);
        this.MapManager = new MapManager();
        this.ImageLoader = new ImageLoader();
        this.camera= new Camera();
        MapManager.createMap(ImageLoader,"Map 1.png");
        this.SoundManager =new SoundManager();
        Test = true;
    }
    private GameEngine() {
        init();
    }         //Constructor para uso generico del juego

    private void init() {                     //Carga de los atributos de la clase
        ImageLoader = new ImageLoader();
        InputManager InputManager = new InputManager(this);
        GameStatus = GameStatus.START_SCREEN;
        camera = new Camera();
        uiManager = new UIManager(this, WIDTH, HEIGHT);
        SoundManager = new SoundManager();
        MapManager = new MapManager();

        JFrame frame = new JFrame("Super Mario Bros ✨🍄"); //Jframe es la clase consola emergente
        frame.add(uiManager);
        frame.addKeyListener(InputManager);
        frame.addMouseListener(InputManager);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        start(); //llamada al run
    }

    private synchronized void start() {    //Inicia la ejecucion del videojuego mediante un hilo
        if (isRunning)
            return;

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void reset(){      //Resetea el videojuego
        resetCamera();
        setGameStatus(GameStatus.START_SCREEN);
    }

    public void resetCamera(){      //Resetea la camara y la musica de fondo
        camera = new Camera();
        if(!Test){                  //Asegura que no se ejecuten sonidos al realizar tests en el pipeline
            SoundManager.restartBackground();
        }
    }

    public void selectMapViaMouse() {       //Seleccion de opciones mediante mouse
        String path = uiManager.selectMapViaMouse(uiManager.getMousePosition());
        if (path != null) {
            createMap(path);
        }
    }

    public void selectMapViaKeyboard(){     //Seleccion de opciones mediante flechas del teclado
        String path = uiManager.selectMapViaKeyboard(selectedMap);
        if (path != null) {
            createMap(path);
        }
    }

    public void changeSelectedMap(boolean up){      //Movimiento (arriba/abajo) entre las opciones
        selectedMap = uiManager.changeSelectedMap(selectedMap, up);
    }

    private void createMap(String path) {           //Crea el mapa del videojuego
        boolean loaded = MapManager.createMap(ImageLoader, path);
        if(loaded){
            setGameStatus(GameStatus.RUNNING);
            if(!Test)
            {
                SoundManager.restartBackground();
            }
        }

        else
            setGameStatus(GameStatus.START_SCREEN);
    }

    @Override
    public void run() {                     //run() sobrescrito de la clase Runnable (Ejecucion el videojuego)
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (isRunning && !thread.isInterrupted()) {   //Actualiza las condiciones del videojuego constantemente (loop de ejecucion)
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (GameStatus == GameStatus.RUNNING) {
                    gameLoop();
                }
                delta--;
            }
            render();

            if(GameStatus != GameStatus.RUNNING) {
                timer = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                MapManager.updateTime();
            }
        }
    }

    private void render() {         //Vuelve a "dibujar" los elementos del videojuego
        uiManager.repaint();
    }

    private void gameLoop() {       //Consulta sobre actualizaciones en el videojuego:
        updateLocations();          //-localizacion de elementos
        checkCollisions();          //-coliciones entre elementos
        updateCamera();             //-seguimiento de la camara a Mario en movimiento
        if (isGameOver()) {
            setGameStatus(GameStatus.GAME_OVER);
        }
        int missionPassed = passMission();
        if(missionPassed > -1){
            MapManager.acquirePoints(missionPassed);
        } else if(MapManager.endLevel())
            setGameStatus(GameStatus.MISSION_PASSED);
    }

    private void updateCamera() {       //Actualiza el seguimiento de la camara segun el movimiento de Mario
        Mario mario = MapManager.getMario();
        double marioVelocityX = mario.getVelX();
        double shiftAmount = 0;
        if (marioVelocityX > 0 && mario.getX() - 600 > camera.getX()) {
            shiftAmount = marioVelocityX;
        }
        camera.moveCam(shiftAmount, 0);
    }

    private void updateLocations() {      //Actualiza la localizacion de los elementos en el mapa
        MapManager.updateLocations();
    }

    private void checkCollisions() {       //Actuliza sobre las coliciones entre los elementos del mapa
        MapManager.checkCollisions(this);
    }

    public void receiveInput(ButtonAction input) {     //Interpreta la llegada de informacion por perifericos (teclado y mouse)

        if (GameStatus == GameStatus.START_SCREEN) {
            if (input == ButtonAction.SELECT && StartScreenSelection == StartScreenSelection.START_GAME) {
                startGame();
            } else if (input == ButtonAction.SELECT && StartScreenSelection == StartScreenSelection.VIEW_ABOUT) {
                setGameStatus(GameStatus.ABOUT_SCREEN);
            } else if (input == ButtonAction.SELECT && StartScreenSelection == StartScreenSelection.VIEW_HELP) {
                setGameStatus(GameStatus.HELP_SCREEN);
            } else if (input == ButtonAction.GO_UP) {
                selectOption(true);
            } else if (input == ButtonAction.GO_DOWN) {
                selectOption(false);
            }
        }
        else if(GameStatus == GameStatus.MAP_SELECTION){
            if(input == ButtonAction.SELECT){
                selectMapViaKeyboard();
            }
            else if(input == ButtonAction.GO_UP){
                changeSelectedMap(true);
            }
            else if(input == ButtonAction.GO_DOWN){
                changeSelectedMap(false);
            }
        } else if (GameStatus == GameStatus.RUNNING) {
            Mario mario = MapManager.getMario();
            if (input == ButtonAction.JUMP) {
                mario.jump(this);
            } else if (input == ButtonAction.M_RIGHT) {
                mario.move(true, camera);
            } else if (input == ButtonAction.M_LEFT) {
                mario.move(false, camera);
            } else if (input == ButtonAction.ACTION_COMPLETED) {
                mario.setVelX(0);
            } else if (input == ButtonAction.FIRE) {
                MapManager.fire(this);
            } else if (input == ButtonAction.PAUSE_RESUME) {
                pauseGame();
            }
        } else if (GameStatus == GameStatus.PAUSED) {
            if (input == ButtonAction.PAUSE_RESUME) {
                pauseGame();
            }
        } else if(GameStatus == GameStatus.GAME_OVER && input == ButtonAction.GO_TO_START_SCREEN){
            reset();
        } else if(GameStatus == GameStatus.MISSION_PASSED && input == ButtonAction.GO_TO_START_SCREEN){
            reset();
        }

        if(input == ButtonAction.GO_TO_START_SCREEN){
            setGameStatus(GameStatus.START_SCREEN);
        }
    }

    private void selectOption(boolean selectUp) {       //Selecciona una de las opciones en la lista de opciones
        StartScreenSelection = StartScreenSelection.select(selectUp);
    }

    //Establece que el estado del videojuego es el selector de mapas cuando esta en la pantalla principal
    private void startGame() {
        if (GameStatus != GameStatus.GAME_OVER) {
            setGameStatus(GameStatus.MAP_SELECTION);
        }
    }

    //Establece que el estado del videojuego es "en pausa" cuando se esta corriendo el videojuego y viceversa
    private void pauseGame() {
        if (GameStatus == GameStatus.RUNNING) {
            setGameStatus(GameStatus.PAUSED);
            if(!Test){
                SoundManager.pauseBackground();
            }
        } else if (GameStatus == GameStatus.PAUSED) {
            setGameStatus(GameStatus.RUNNING);
            if(!Test){
                SoundManager.resumeBackground();
            }
        }

    }

    public void shakeCamera(){      //Sacude la camara
        camera.shakeCamera();
    }

    private boolean isGameOver() {      //Establece que el estado del videojuego es "terminado" cuando el videojuego esta corriendo
        if(GameStatus == GameStatus.RUNNING)
            return MapManager.isGameOver();
        return false;
    }

    public ImageLoader getImageLoader() {       //Devuelve el cargador de imagenes
        return ImageLoader;
    }

    public GameStatus getGameStatus() {         //Devuelve el estado del videojuego
        return GameStatus;
    }

    public StartScreenSelection getStartScreenSelection() {     //Devuelve la seleccion de la pantalla
        return StartScreenSelection;
    }

    public void setGameStatus(GameStatus GameStatus) {      //Determina el estado del videojuego
        this.GameStatus = GameStatus;
    }

    public int getScore() {     //Devuelve los puntos de Mario
        return MapManager.getScore();
    }

    public int getRemainingLives() {        //Devuelve las vidas de Mario
        return MapManager.getRemainingLives();
    }

    public int getCoins() {     //Devuelve las monedas de Mario
        return MapManager.getCoins();
    }

    public int getSelectedMap() {       //Devuelve el mapa seleccionado
        return selectedMap;
    }

    public void drawMap(Graphics2D g2) {        //Devuelve la imagen del mapa
        MapManager.drawMap(g2);
    }

    public Point getCameraLocation() {      //Devuelve la localizacion de la camara
        return new Point((int)camera.getX(), (int)camera.getY());
    }

    private int passMission(){      //Devuelve un numero >0 si Mario paso de mision
        return MapManager.passMission();
    }

    public void playCoin() {        //Reproduce el sonido cuando Mario toca una moneda
        if(!Test){
            SoundManager.playCoin();
        }
    }

    public void playOneUp() {       //Reproduce el sonido cuando Mario toca un hongo verde
        if (!Test) {
            SoundManager.playOneUp();
        }
    }

    public void playSuperMushroom() {       //Reproduce el sonido cuando Mario toca un hongo rojo
        if (!Test){
            SoundManager.playSuperMushroom();
        }
    }

    public void playMarioDies() {           //Reproduce el sonido cuando Mario muere
        if (!Test){
            SoundManager.playMarioDies();
        }
    }

    public void playJump() {        //Reproduce el sonido cuando Mario salta
        if(!Test){
            SoundManager.playJump();
        }
    }

    public void playFireFlower() {      //Reproduce el sonido cuando Mario toca la flor de fuego
        if(!Test){
            SoundManager.playFireFlower();
        }
    }

    public void playFireball() {        //Reproduce el sonido cuando Mario lanza bolas de fuego
        if(!Test){
            SoundManager.playFireball();
        }
    }

    public void playStomp() {           //Reproduce el sonido cuando Mario toca monedas
        if(!Test){
            SoundManager.playStomp();
        }
    }

    public MapManager getMapManager() {        //Devuelve el administrador el mapa
        return MapManager;
    }

    public static void main(String... args) {
        new GameEngine();
    }

    public int getRemainingTime() {             //Devuelve el tiempo de juego
        return MapManager.getRemainingTime();
    }
}
