package com.aDeAyme.superMarioBros.view;
import com.aDeAyme.superMarioBros.controller.GameEngine;
import com.aDeAyme.superMarioBros.controller.GameStatus;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
//Administrador de las imagenes en el videojuego

public class UIManager extends JPanel{

    private GameEngine engine;  //Referencia al motor del videojuego
    private Font gameFont;      //Fuente de letra del videojuego
    private BufferedImage startScreenImage, aboutScreenImage, helpScreenImage, gameOverScreen;  //Imagenes de las pantallas de videojuego
    private BufferedImage heartIcon;    //Icono de corazon en el juego (vidas)
    private BufferedImage coinIcon;     //Icono de monedas en el juego
    private BufferedImage selectIcon;   //Selector (hongo rojo)
    private MapSelection MapSelection;  //Selector de mapas

    public UIManager(GameEngine engine, int width, int height) {    //Establece todas las vistas del videojuego antes y durante la partida
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

        this.engine = engine;
        ImageLoader loader = engine.getImageLoader();

        MapSelection = new MapSelection();

        BufferedImage sprite = loader.loadImage("/sprite.png");
        this.heartIcon = loader.loadImage("/heart-icon.png");
        this.coinIcon = loader.getSubImage(sprite, 1, 5, 48, 48);
        this.selectIcon = loader.loadImage("/select-icon.png");
        this.startScreenImage = loader.loadImage("/start-screen.png");
        this.helpScreenImage = loader.loadImage("/help-screen.png");
        this.aboutScreenImage = loader.loadImage("/about-screen.png");
        this.gameOverScreen = loader.loadImage("/game-over.png");

        try {
            InputStream in = getClass().getResourceAsStream("/media/font/mario-font.ttf");
            gameFont = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (FontFormatException | IOException e) {
            gameFont = new Font("Verdana", Font.PLAIN, 12);
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g){         //Dibujo de elementos el juego
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        GameStatus GameStatus = engine.getGameStatus();

        if(GameStatus == GameStatus.START_SCREEN){
            drawStartScreen(g2);
        }
        else if(GameStatus == GameStatus.MAP_SELECTION){
            drawMapSelectionScreen(g2);
        }
        else if(GameStatus == GameStatus.ABOUT_SCREEN){
            drawAboutScreen(g2);
        }
        else if(GameStatus == GameStatus.HELP_SCREEN){
            drawHelpScreen(g2);
        }
        else if(GameStatus == GameStatus.GAME_OVER){
            drawGameOverScreen(g2);
        }
        else {
            Point camLocation = engine.getCameraLocation();
            g2.translate(-camLocation.x, -camLocation.y);
            engine.drawMap(g2);
            g2.translate(camLocation.x, camLocation.y);

            drawPoints(g2);
            drawRemainingLives(g2);
            drawAcquiredCoins(g2);
            drawRemainingTime(g2);

            if(GameStatus == GameStatus.PAUSED){
                drawPauseScreen(g2);
            }
            else if(GameStatus == GameStatus.MISSION_PASSED){
                drawVictoryScreen(g2);
            }
        }

        g2.dispose();
    }

    private void drawRemainingTime(Graphics2D g2) {     //Dibujo del tiempo restante
        g2.setFont(gameFont.deriveFont(25f));
        g2.setColor(Color.WHITE);
        String displayedStr = "TIME: " + engine.getRemainingTime();
        g2.drawString(displayedStr, 750, 50);
    }

    private void drawVictoryScreen(Graphics2D g2) {     //Dibujo de la pantalla de victoria
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);
        String displayedStr = "YOU WON!";
        int stringLength = g2.getFontMetrics().stringWidth(displayedStr);
        g2.drawString(displayedStr, (getWidth()-stringLength)/2, getHeight()/2);
    }

    private void drawHelpScreen(Graphics2D g2) {
        g2.drawImage(helpScreenImage, 0, 0, null);
    }   //Dibujo de la pantalla de ayuda

    private void drawAboutScreen(Graphics2D g2) {
        g2.drawImage(aboutScreenImage, 0, 0, null);
    }   //Dibujo de la pantalla de "sobre el juego"

    private void drawGameOverScreen(Graphics2D g2) {        //Dibujo de la pantalla de "partida terminada"
        g2.drawImage(gameOverScreen, 0, 0, null);
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(new Color(130, 48, 48));
        String acquiredPoints = "Score: " + engine.getScore();
        int stringLength = g2.getFontMetrics().stringWidth(acquiredPoints);
        int stringHeight = g2.getFontMetrics().getHeight();
        g2.drawString(acquiredPoints, (getWidth()-stringLength)/2, getHeight()-stringHeight*2);
    }

    private void drawPauseScreen(Graphics2D g2) {           //Dibujo de la pantalla de pausa
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);
        String displayedStr = "PAUSED";
        int stringLength = g2.getFontMetrics().stringWidth(displayedStr);
        g2.drawString(displayedStr, (getWidth()-stringLength)/2, getHeight()/2);
    }

    private void drawAcquiredCoins(Graphics2D g2) {         //Dibujo del incremento de monedas
        g2.setFont(gameFont.deriveFont(30f));
        g2.setColor(Color.WHITE);
        String displayedStr = "" + engine.getCoins();
        g2.drawImage(coinIcon, getWidth()-115, 10, null);
        g2.drawString(displayedStr, getWidth()-65, 50);
    }

    private void drawRemainingLives(Graphics2D g2) {        //Dibujo de las vidas restantes
        g2.setFont(gameFont.deriveFont(30f));
        g2.setColor(Color.WHITE);
        String displayedStr = "" + engine.getRemainingLives();
        g2.drawImage(heartIcon, 50, 10, null);
        g2.drawString(displayedStr, 100, 50);
    }

    private void drawPoints(Graphics2D g2){                 //Dibujo de los puntos
        g2.setFont(gameFont.deriveFont(25f));
        g2.setColor(Color.WHITE);
        String displayedStr = "Points: " + engine.getScore();
        int stringLength = g2.getFontMetrics().stringWidth(displayedStr);;
        //g2.drawImage(coinIcon, 50, 10, null);
        g2.drawString(displayedStr, 300, 50);
    }

    private void drawStartScreen(Graphics2D g2){            //Dibujo de la pantalla inicial
        int row = engine.getStartScreenSelection().getLineNumber();
        g2.drawImage(startScreenImage, 0, 0, null);
        g2.drawImage(selectIcon, 375, row * 70 + 440, null);
    }

    private void drawMapSelectionScreen(Graphics2D g2){     //Dibujo de la pantalla de seleccion de mapas
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);
        MapSelection.draw(g2);
        int row = engine.getSelectedMap();
        int y_location = row*100+300-selectIcon.getHeight();
        g2.drawImage(selectIcon, 375, y_location, null);
    }

    public String selectMapViaMouse(Point mouseLocation) {
        return MapSelection.selectMap(mouseLocation);
    } //Seleccion de mapas mediante el mouse

    public String selectMapViaKeyboard(int index){
        return MapSelection.selectMap(index);
    } //Seleccion de mapas mediante el teclado

    public int changeSelectedMap(int index, boolean up){
        return MapSelection.changeSelectedMap(index, up);
    } //Cambio del mapa señalado
}