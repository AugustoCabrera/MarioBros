package com.AdeAyme.superMarioBros.controller;

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

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void notifyObserver() {

        System.out.println(observers.size());

        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update();
        }
    }

    public void setDifficulty(){
        System.out.println("NOTIFIQUE");
        notifyObserver();
    }


}
