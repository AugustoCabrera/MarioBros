package com.aDeAyme.superMarioBros.controller;

public interface subject {
    void registerObserver(observer observer);
    void removeObserver(observer observer);
    void notifyObserver();
}
