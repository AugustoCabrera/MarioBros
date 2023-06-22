package com.aDeAyme.superMarioBros.view;
import java.awt.image.BufferedImage;
//Establece la animacion de los objetos del mapa

public class Animation {

    private int index = 0, count = 0;
    private BufferedImage[] leftFrames, rightFrames;    //Imagenes izquierda y derecha del objeto
    private BufferedImage currentFrame;                 //Imagen actual del objeto

    public Animation(BufferedImage[] leftFrames, BufferedImage[] rightFrames){
        this.leftFrames = leftFrames;
        this.rightFrames = rightFrames;

        currentFrame = rightFrames[1];
    }

    public BufferedImage animate(int speed, boolean toRight){   //Realiza la animacion segun su velocidad y su movimiento horizontal
        count++;
        BufferedImage[] frames = toRight ? rightFrames : leftFrames;

        if(count > speed){
            nextFrame(frames);
            count = 0;
        }

        return currentFrame;
    }

    private void nextFrame(BufferedImage[] frames) {    //Espera al siguiete fotograma
        if(index + 3 > frames.length)
            index = 0;

        currentFrame = frames[index+2];
        index++;
    }

    public BufferedImage[] getLeftFrames() {
        return leftFrames;
    }   //Devuelve la imagen izquierda del objeto

    public BufferedImage[] getRightFrames() {
        return rightFrames;
    }   //Devuelve la imagen derecha del objeto

}
