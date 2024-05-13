package org.enset.Ex1;

public class Talkative implements Runnable {
    private int value;

    public Talkative(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + value);
            try {
                Thread.sleep(10); // Ajout d'une pause de 10 millisecondes pour mieux visualiser l'entrelacement
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
