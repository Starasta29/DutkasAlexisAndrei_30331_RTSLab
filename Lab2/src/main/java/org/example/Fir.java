package org.example;

import java.util.ArrayList;
import java.util.List;

public class Fir extends Thread implements org.example.Observable {

    private int id;
    private int processorLoad;
    private List<Observer> observers = new ArrayList<>();

    Fir(int id, int priority, int procLoad) {
        this.id = id;
        this.processorLoad = procLoad;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        int c = 0;
        while (c < 1000) {
            for (int j = 0; j < this.processorLoad; j++) {
                j++;
                j--;
            }
            c++;

            notifyObservers(id, c);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int id, int value) {
        for (Observer observer : observers) {
            observer.update(id, value);
        }
    }
}