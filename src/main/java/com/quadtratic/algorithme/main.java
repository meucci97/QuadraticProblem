package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class main {
    //Tabou
    public final static int MAX_ITER = 20000;

    // Recuit
    public final static int MOVES_AT_TEMP = 1000;
    public final static double TEMPERATURE_DECREASE_COEFF = 0.1;
    public final static double INITIALE_TEMPERATURE = 1000;
    public final static int CHANGE_OF_TEMP = 100;

    public static void main (String[] args) {

        launchAllTailRecuitParallel();

        //launchAllTailTabouParallel();
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisir Type d'algo (0 -> Tabou, 1->Recuit)");

        boolean tabou = Integer.parseInt(sc.nextLine()) == 0;
        System.out.println("Taille du jeu de donnée tai");
        int taiSize =Integer.parseInt(sc.nextLine());

        if(tabou)
            launchParallelTabou(taiSize);
        else
            launchParallelRecuit(taiSize);
*/
        return ;
    }

    private static void launchTabou() {
        Quadratic q2 = new Quadratic("tai80a.dat");
        q2.affiche();
        Solution s2 = new Solution();
//        int[] i= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
//
//        //8,1,6,2,11,10,3,5,9,7,12,4 224416.0
//        s2.setSolution(i);
        Solution s = new Solution(80);
        Tabou t = new Tabou(q2, s, false, 2000, 70);
        Solution solutionTabou = t.evaluateSolution();

        System.out.println("Fitness: " + solutionTabou.getFitness());

        results(solutionTabou);
    }

    private static void launchRecuitSimule() {
        Quadratic q3 = new Quadratic("tai12a.dat");
        q3.affiche();
        Solution s3 = new Solution();

        int[] i= {1, 3, 4, 5, 2};
        s3.setSolution(i);

        RecuitSimule r = new RecuitSimule(q3, s3, true, 0.1, 10, 250, 10);
        Solution solutionRecuit = r.evaluateSolution();

        results(solutionRecuit);
    }

    private static void launchParallelTabou(int taiSize){
        String tailFileName = "tai"+taiSize+"a.dat";

        long timeLaunch = System.currentTimeMillis();
        ArrayList<Algorithm> tasks = new ArrayList<Algorithm>();

        for (int tabouSize = 1; tabouSize < taiSize; tabouSize++) {
            Quadratic q = new Quadratic(tailFileName);
            Solution s = new Solution(taiSize);
            //8,1,6,2,11,10,3,5,9,7,12,4 224416.0
            Tabou t = new Tabou(q, s, true, MAX_ITER, tabouSize);
            tasks.add(t);
        }

        int processors = Runtime.getRuntime().availableProcessors();

        ExecutorService myService = Executors.newFixedThreadPool(processors);
        try {
            List<Future<Solution>> solutions = myService.invokeAll(tasks);
            //System.out.println("Tabou Done for " + tailFileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long timeToExecute = System.currentTimeMillis() - timeLaunch;
        System.out.println("Tabou Done for " + tailFileName+ " - Total execution time: " + timeToExecute + " ms");
    }

    private static void launchParallelRecuit(int taiSize){

                /*
        System.out.println("Choisir le coefficient de diminuition");
        double temperatureDecreaseCoeff = sc.nextDouble();

        System.out.println("Choisir la température initiale");
        double initialTemperature = sc.nextDouble();

        System.out.println("Choisir le nombre de temperature");
        double changesOfTemp = sc.nextDouble();
        */

        String tailFileName = "tai"+taiSize+"a.dat";

        long timeLaunch = System.currentTimeMillis();
        ArrayList<Algorithm> tasks = new ArrayList<Algorithm>();
        for(double initial_temperature = INITIALE_TEMPERATURE; initial_temperature <= 10000; initial_temperature = initial_temperature + 1500) {
            for(double coeff = TEMPERATURE_DECREASE_COEFF; coeff < 1.0; coeff = coeff + 0.2) {
                System.out.println(coeff);
                Quadratic q = new Quadratic(tailFileName);
                Solution s = new Solution(taiSize);
                //8,1,6,2,11,10,3,5,9,7,12,4 224416.0
                RecuitSimule r = new RecuitSimule(q, s, true, coeff, MOVES_AT_TEMP, CHANGE_OF_TEMP, initial_temperature);
                tasks.add(r);
            }
        }

        int processors = Runtime.getRuntime().availableProcessors();

        ExecutorService myService = Executors.newFixedThreadPool(processors);

        try {
            List<Future<Solution>> solutions = myService.invokeAll(tasks);
            System.out.println("Recuit Done for " + tailFileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long timeToExecute = System.currentTimeMillis() - timeLaunch;
        System.out.println("Total execution time: " + timeToExecute + " ms");
    }

    private static void results(Solution solution) {
        System.out.println("Fitness : " + solution.getFitness());
        for(int x = 0; x < solution.getSolution().length; x++){
            System.out.print(solution.getSolutionValue(x) + " ");
        }
    }


    private static void launchAllTailTabouParallel(){
        int[] i = {10,12,15,17,20,25,30,35,40,50,80,100};

        for(int j=0 ; j<i.length;j++){
            launchParallelTabou(i[j]);
            System.out.println("-----------------------------");
        }
    }

    private static void launchAllTailRecuitParallel() {
        int[] i = {10,12,15,17,20,25,30,35,40,50,80,100};

        for(int j=0 ; j<i.length;j++){
            launchParallelRecuit(i[j]);
            System.out.println("-----------------------------");
        }
    }
}
