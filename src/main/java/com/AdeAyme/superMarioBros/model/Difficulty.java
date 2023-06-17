package com.AdeAyme.superMarioBros.model;

import java.util.ArrayList;

public class Difficulty implements Subject {

    private ArrayList<Observer> observers;

    public Difficulty() {
        this.observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = observers.size();
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObserver() {

        System.out.println(observers.size());

        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update();
        }
    }

    public void setDifficulty(){
        System.out.println("NOTIFIQUE");
        notifyObserver();
    }


}
