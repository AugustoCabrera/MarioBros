package com.aDeAyme.superMarioBros.controller;
import java.util.ArrayList;
//Sujeto del patron Observer. Observa la cantidad de puntos que gana Mario e informa a sus observadores suscriptos
public class Difficulty implements Subject {
    private ArrayList<Observer> Observers;      //Colleccion de observadores del patron Observer
    public Difficulty() {
        this.Observers = new ArrayList<>();
    }

    //Realiza la suscripcion de un observador a la lista de observadores
    public void registerObserver(Observer o) {
        Observers.add(o);
    }

    //Realiza la desuscripcion de un observador a la lista de observadores
    public void removeObserver(Observer o) {
        int i = Observers.size();
        if (i >= 0) {
            Observers.remove(i);
        }
    }

    //Realiza la notificacion de una actualizacion a los observadores de la lista
    public void notifyObserver() {
        for (int i = 0; i < Observers.size(); i++) {
            Observer observer = Observers.get(i);
            observer.update();
        }
    }

    //Realiza la llamada al metodo que se encarga a notificar a los observadores
    public void setDifficulty(){
        notifyObserver();
    }
}
