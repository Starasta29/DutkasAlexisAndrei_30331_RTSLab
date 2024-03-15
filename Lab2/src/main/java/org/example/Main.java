package org.example;

import java.awt.*;

public class Main {

    private static final int noOfThreads = 6;
    private static final int processorLoad = 1000000;

    public static void main(String args[]) {
        Window win = new Window(noOfThreads);

        for (int i = 0; i < noOfThreads; i++) {
            Fir fir = new Fir(i, i + 2, processorLoad);
            fir.addObserver(win);
            new Thread(fir).start();
        }
    }
}