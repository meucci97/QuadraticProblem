package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class main {
    public final static int MAX_ITER = 2000;

    public static void main (String[] args) {
        int maxIter;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisir Type d'algo (0 -> Tabou, 1->Recuit)");
        boolean tabou = sc.nextLine() == "0";
        System.out.println("Taille du jeu de donnée tai0");
        int tabouMaxSize =Integer.parseInt(sc.nextLine());
        String tailFileName = "tai"+tabouMaxSize+"a.dat";

        long timeLaunch = System.currentTimeMillis();
        ArrayList<Algorithm> tasks = new ArrayList<Algorithm>();

        for (int tabouSize = 1; tabouSize < tabouMaxSize; tabouSize++) {
            Quadratic q = new Quadratic(tailFileName);
            Solution s = new Solution(12);
            //8,1,6,2,11,10,3,5,9,7,12,4 224416.0
            Tabou t = new Tabou(q, s, true, MAX_ITER, tabouSize);
            tasks.add(t);
        }

        int processors = Runtime.getRuntime().availableProcessors();

        ExecutorService myService = Executors.newFixedThreadPool(processors);
        try {
            List<Future<Solution>> solutions = myService.invokeAll(tasks);
            for (Future<Solution> future : solutions) {
                Solution cc = future.get();
                cc.affiche();
            }
            System.out.println("DONE");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long timeToExecute = System.currentTimeMillis() - timeLaunch;

        System.out.println("\nTemps d'éxecution Total: " + timeToExecute + " ms");

    }

    private static void launchTabou() {
        Quadratic q2 = new Quadratic("tai12a.dat");
        q2.affiche();
        Solution s2 = new Solution();
        int[] i= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        //8,1,6,2,11,10,3,5,9,7,12,4 224416.0
        s2.setSolution(i);

        Tabou t = new Tabou(q2, s2, true, 2000, 9);
        Solution solutionTabou = t.evaluateSolution();

        System.out.println("Fitness: " + solutionTabou.getFitness());

        results(solutionTabou);
    }

    private static void launchRecuitSimule() {
        Quadratic q3 = new Quadratic("test.dat");
        q3.affiche();
        Solution s3 = new Solution();

        int[] i= {1, 3, 4, 5, 2};
        s3.setSolution(i);

        RecuitSimule r = new RecuitSimule(q3, s3, true, 0.1, 1000, 100, 18);
        Solution solutionRecuit = r.evaluateSolution();

        results(solutionRecuit);
    }

    private static void results(Solution solution) {
        System.out.println("Fitness : " + solution.getFitness());
        for(int x = 0; x < solution.getSolution().length; x++){
            System.out.print(solution.getSolutionValue(x) + " ");
        }
    }
}
