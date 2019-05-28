package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.Random;

public class RecuitSimule {

    private Quadratic q;
    private Solution solutionMin;
    private boolean log;
    private Logger logger;
    private double temperatureDecreaseCoeff;
    private int changesOfTemp;
    private int movesAtTemp;

    public RecuitSimule(Quadratic q, boolean log, double temperatureDecreaseCoeff, int movesAtTemp, int changesOfTemp) {
        this.q = q;
        this.temperatureDecreaseCoeff = temperatureDecreaseCoeff;
        this.movesAtTemp = movesAtTemp;
        this.changesOfTemp = changesOfTemp;
        this.log = log;
        this.logger = new Logger(q.getFileName());
    }

    public Solution recuitSimuleSearch(Solution solutionInitiale, double initialTemperature) {
        solutionInitiale.setFitness(q.calculateSolutionFitness(solutionInitiale));
        solutionMin = solutionInitiale;
        q.setSolution(solutionInitiale);

        double temperature = initialTemperature;

        ArrayList<Solution> solutionsPossibles;

        Random random = new Random();
        Solution randomSolution;

        for(int k = 0; k < this.changesOfTemp; k++) {
            for(int l = 1; l <= this.movesAtTemp; l++) {

                // On récupère les solutions possibles
                solutionsPossibles = q.getPossibleSolutions();

                // Selection d'une permutation au hasard
                randomSolution = solutionsPossibles.get(random.nextInt(solutionsPossibles.size()));

                // Delta de la fitness
                double deltaFitness = randomSolution.getFitness() - q.getSolution().getFitness();

                if(deltaFitness <= 0) {
                    q.setSolution(randomSolution);
                    if(randomSolution.getFitness() < solutionMin.getFitness()) {
                        solutionMin = randomSolution;
                    }
                } else {
                    double p = random.nextDouble();
                    System.out.println(p);
                    if(p <= Math.exp(-deltaFitness / temperature)) {
                        q.setSolution(randomSolution);
                    }
                }

            }

            temperature *= this.temperatureDecreaseCoeff;
        }

        return solutionMin;
    }
}
