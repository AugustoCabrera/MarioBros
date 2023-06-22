package com.aDeAyme.superMarioBros.controller;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

//Administrador de los sonidos del videojuego
public class SoundManager {

    private Clip background;    //Música de fond del videojuego
    private long clipTime = 0;  //Tiempo de reproduccion del sonido

    public SoundManager() {
        background = getClip(loadAudio("background"));
    } //Determina la musica del fonde

    private AudioInputStream loadAudio(String url) {      //Carga el audio segun su path en el proyecto
        try {
            InputStream audioSrc = getClass().getResourceAsStream("/media/audio/" + url + ".wav");
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            return AudioSystem.getAudioInputStream(bufferedIn);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private Clip getClip(AudioInputStream stream) {     //Devuelve el clip de un determinado stream de entrada
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void resumeBackground(){     //Reanuda la reproduccion de la música de fondo
        background.setMicrosecondPosition(clipTime);
        background.start();
    }

    public void pauseBackground(){      //Para la reproduccion de la música de fondo
        clipTime = background.getMicrosecondPosition();
        background.stop();
    }

    public void restartBackground(){      //Resetea la reproduccion de la música de fondo
        clipTime = 0;
        resumeBackground();
    }

    public void playJump() {        //Reproduce el sonido de salto de Mario
        Clip clip = getClip(loadAudio("jump"));
        clip.start();
    }

    public void playCoin() {        //Reproduce el sonido de toma de monedas
        Clip clip = getClip(loadAudio("coin"));
        clip.start();
    }

    public void playFireball() {        //Reproduce el sonido de lanzamiento de bolas de fuego
        Clip clip = getClip(loadAudio("fireball"));
        clip.start();
    }

    public void playGameOver() {        //Reproduce el sonido de "game over " del videojuego
        Clip clip = getClip(loadAudio("gameOver"));
        clip.start();
    }

    public void playStomp() {           //Reproduce el sonido de ruptura de un bloque
        Clip clip = getClip(loadAudio("stomp"));
        clip.start();
    }

    public void playOneUp() {       //Reproduce el sonido de contacto con un hongo verde
        Clip clip = getClip(loadAudio("oneUp"));
        clip.start();
    }

    public void playSuperMushroom() {       //Reproduce el sonido de contacto con un hongo rojo
        Clip clip = getClip(loadAudio("superMushroom"));
        clip.start();
    }

    public void playMarioDies() {           //Reproduce el sonido de muerte de Mario
        Clip clip = getClip(loadAudio("marioDies"));
        clip.start();
    }
}
