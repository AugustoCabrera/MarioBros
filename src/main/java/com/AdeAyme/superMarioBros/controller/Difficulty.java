package com.aDeAyme.superMarioBros.controller;
import java.util.ArrayList;
//Sujeto del patron Observer. Observa la cantidad de puntos que gana Mario e informa a sus observadores suscriptos
public class difficulty implements subject {
    private ArrayList<observer> observers;      //Colleccion de observadores del patron Observer
    public difficulty() {
        this.observers = new ArrayList<>();
    }

    //Realiza la suscripcion de un observador a la lista de observadores
    public void registerObserver(observer o) {
        observers.add(o);
    }

    //Realiza la desuscripcion de un observador a la lista de observadores
    public void removeObserver(observer o) {
        int i = observers.size();
        if (i >= 0) {
            observers.remove(i);
        }
    }

    //Realiza la notificacion de una actualizacion a los observadores de la lista
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            observer observer = observers.get(i);
            observer.update();
        }
    }

    //Realiza la llamada al metodo que se encarga a notificar a los observadores
    public void setDifficulty(){
        notifyObserver();
    }
}
