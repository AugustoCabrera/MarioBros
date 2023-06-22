package com.aDeAyme.superMarioBros.view;
//Enumerado de items de la pantalla principal
public enum StartScreenSelection {
    START_GAME(0),      //Comienzar del juego
    VIEW_HELP(1),       //Ayuda
    VIEW_ABOUT(2);      //Sobre el juego
    private final int lineNumber;   //Opcion actual (que se esta señalando)
    StartScreenSelection(int lineNumber){ this.lineNumber = lineNumber; }

    public static StartScreenSelection getSelection(int number){    //Devuelve la opcion elegida
        if(number == 0)
            return START_GAME;
        else if(number == 1)
            return VIEW_HELP;
        else if(number == 2)
            return VIEW_ABOUT;
        else return null;
    }

    public StartScreenSelection select(boolean toUp){       //Selecciona una opcion
        int selection;

        if(lineNumber > -1 && lineNumber < 3){
            selection = lineNumber - (toUp ? 1 : -1);
            if(selection == -1)
                selection = 2;
            else if(selection == 3)
                selection = 0;
            return getSelection(selection);
        }

        return null;
    }

    public int getLineNumber() {
        return lineNumber;
    }   //Devuelve la opcion sobre la que se señala
}
