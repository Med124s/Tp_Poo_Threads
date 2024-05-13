package org.enset.Ex2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        int[] tableau = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int nombreThreads = 4; // Nombre de threads dans le pool

        // Création du pool de threads
        ExecutorService pool = Executors.newFixedThreadPool(nombreThreads);

        // Divisez le tableau en plusieurs plages et créez un thread pour chaque plage
        int taillePlage = tableau.length / nombreThreads;
        int debut = 0;
        int fin = taillePlage;

        for (int i = 0; i < nombreThreads; i++) {
            if (i == nombreThreads - 1) {
                fin = tableau.length; // Pour la dernière plage, prenez jusqu'à la fin du tableau
            }
            Sommeur sommeur = new Sommeur(tableau, debut, fin);
            pool.execute(sommeur);
            debut = fin;
            fin += taillePlage;
        }

        // Attendre que tous les threads aient terminé
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Calculer la somme totale
        int sommeTotale = 0;
        for (int i = 0; i < nombreThreads; i++) {
            sommeTotale += ((Sommeur) pool).getSomme();
        }

        // Afficher la somme totale
        System.out.println("Somme totale : " + sommeTotale);
    }
}
