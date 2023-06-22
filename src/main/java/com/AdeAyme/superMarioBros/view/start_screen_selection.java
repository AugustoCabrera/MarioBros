package com.aDeAyme.superMarioBros.view;

public enum start_screen_selection {
    START_GAME(0),
    VIEW_HELP(1),
    VIEW_ABOUT(2);

    private final int lineNumber;
    start_screen_selection(int lineNumber){ this.lineNumber = lineNumber; }

    public static start_screen_selection getSelection(int number){
        if(number == 0)
            return START_GAME;
        else if(number == 1)
            return VIEW_HELP;
        else if(number == 2)
            return VIEW_ABOUT;
        else return null;
    }

    public start_screen_selection select(boolean toUp){
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
    }
}
