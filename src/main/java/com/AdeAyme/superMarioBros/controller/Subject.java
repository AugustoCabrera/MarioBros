package com.aDeAyme.superMarioBros.controller;

//Interfaz del sujeto del patron Observer
public interface Subject {
    void registerObserver(Observer observer); //Agrega un nuevo observador
    void removeObserver(Observer observer);     //Remueve un observador
    void notifyObserver();      //Notifica a los observadores
}
