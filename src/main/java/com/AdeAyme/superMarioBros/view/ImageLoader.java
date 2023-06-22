package com.aDeAyme.superMarioBros.view;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//Cargador de imagenes. Busca las imagenes de los objetos en el proyecto y las carga en buffers

public class ImageLoader {
    private BufferedImage marioForms;   //Imagenes de las formas de Mario
    private BufferedImage brickAnimation;       //Imagenesd de las animaciones de los bloques

    public ImageLoader(){
        marioForms = loadImage("/mario-forms.png");
        brickAnimation = loadImage("/brick-animation.png");
    }

    public BufferedImage loadImage(String path){      //Carga las imagenes segun la localizacion en el proyecto que se determine
        BufferedImage imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(getClass().getResource("/media" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

    public BufferedImage loadImage(File file){       //Carga las imagenes segun el archivo que se determine
        BufferedImage imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageToReturn;
    }

    public BufferedImage getSubImage(BufferedImage image, int col, int row, int w, int h){  //Devuelve la imagen de cada objeto a partir del buffer
        if((col == 1 || col == 4) && row == 3){
            return image.getSubimage((col-1)*48, 128, w, h);
        }
        return image.getSubimage((col-1)*48, (row-1)*48, w, h);
    }

    public BufferedImage[] getLeftFrames(int marioForm){    //Devuelve la imagen izquierda del buffer de Mario
        BufferedImage[] leftFrames = new BufferedImage[5];
        int col = 1;
        int width = 52, height = 48;

        if(marioForm == 1) { //super mario
            col = 4;
            width = 48;
            height = 96;
        }
        else if(marioForm == 2){ //fire mario
            col = 7;
            width = 48;
            height = 96;
        }

        for(int i = 0; i < 5; i++){
            leftFrames[i] = marioForms.getSubimage((col-1)*width, (i)*height, width, height);
        }
        return leftFrames;
    }

    public BufferedImage[] getRightFrames(int marioForm){       //Devuelve la imagen derecha del buffer de Mario
        BufferedImage[] rightFrames = new BufferedImage[5];
        int col = 2;
        int width = 52, height = 48;

        if(marioForm == 1) { //super mario
            col = 5;
            width = 48;
            height = 96;
        }
        else if(marioForm == 2){ //fire mario
            col = 8;
            width = 48;
            height = 96;
        }
        for(int i = 0; i < 5; i++){
            rightFrames[i] = marioForms.getSubimage((col-1)*width, (i)*height, width, height);
        }
        return rightFrames;
    }

    public BufferedImage[] getBrickFrames(){        //Devuelve el fotograma del bloque
        BufferedImage[] frames = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            frames[i] = brickAnimation.getSubimage(i*105, 0, 105, 105);
        }
        return frames;
    }
}
