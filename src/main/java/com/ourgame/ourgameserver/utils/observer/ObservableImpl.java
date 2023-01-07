package com.ourgame.ourgameserver.utils.observer;

import java.util.HashSet;
import java.util.Set;


public abstract class ObservableImpl implements Observable {
    private final Set<Observer> observers = new HashSet<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
