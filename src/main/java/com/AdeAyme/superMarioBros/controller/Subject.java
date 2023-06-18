package com.AdeAyme.superMarioBros.controller;

import com.AdeAyme.superMarioBros.controller.Observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}
