package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.Random;

public class RecuitSimule extends Algorithm {

    private double temperatureDecreaseCoeff;
    private int changesOfTemp;
    private int movesAtTemp;
    private double initialTemperature;

    public RecuitSimule(Quadratic quadratic, Solution solutionInitiale, boolean log, double temperatureDecreaseCoeff,
                        int movesAtTemp, int changesOfTemp, double initialTemperature) {
        super(quadratic, solutionInitiale, log, (int) initialTemperature);
        this.temperatureDecreaseCoeff = temperatureDecreaseCoeff;
        this.movesAtTemp = movesAtTemp;
        this.changesOfTemp = changesOfTemp;
        this.initialTemperature = initialTemperature;

        this.loggerResults = new Logger(quadratic, temperatureDecreaseCoeff, changesOfTemp, movesAtTemp, initialTemperature);
        this.loggerResults.initializeFirstLineResultsRecuitSimule();

        if(log) {
            this.logger = new Logger(quadratic.getFileName(), "recuit");
        } else {
            this.logger = null;
        }
    }

    @Override
    public Solution evaluateSolution() {
        solutionInitiale.setFitness(this.quadratic.calculateSolutionFitness(solutionInitiale));
        this.quadratic.setSolution(solutionInitiale);

        double temperature = initialTemperature;

        ArrayList<Solution> solutionsPossibles;

        Random random = new Random();
        Solution randomSolution;
        int iteration = 0;
        for(int k = 0; k < this.changesOfTemp; k++) {
            for(int l = 1; l <= this.movesAtTemp; l++) {

                // On récupère les solutions possibles
                solutionsPossibles = this.quadratic.getPossibleSolutions();

                // Selection d'une permutation au hasard
                randomSolution = solutionsPossibles.get(random.nextInt(solutionsPossibles.size()));

                // Delta de la fitness
                double deltaFitness = randomSolution.getFitness() - this.quadratic.getSolution().getFitness();

                if(deltaFitness <= 0) {
                    this.quadratic.setSolution(randomSolution);
                    if(randomSolution.getFitness() < this.solutionMin.getFitness()) {
                        this.solutionMin = randomSolution;
                    }
                    this.loggerResults.writeResultsInFileRecuitSimule(loggerResults.getFilename(),
                            temperatureDecreaseCoeff, temperature, changesOfTemp, k, movesAtTemp, l,
                            initialTemperature,iteration, solutionMin.getFitness(), quadratic.getSolution().getFitness());
                    iteration++;

                } else {
                    double p = random.nextDouble();
                    //System.out.println(p);
                    if(p <= Math.exp(-deltaFitness / temperature)) {
                        this.quadratic.setSolution(randomSolution);

                        this.loggerResults.writeResultsInFileRecuitSimule(loggerResults.getFilename(),
                                temperatureDecreaseCoeff, temperature, changesOfTemp, k, movesAtTemp, l,
                                initialTemperature,iteration,solutionMin.getFitness(), quadratic.getSolution().getFitness());

                        iteration++;
                        if(log) {
                            // On écrit les logs dans un fichier
                            this.logger.writeLogInFileRecuitSimule(this.solutionMin, this.quadratic.getSolution(), k, l);
                        } else {
                            System.out.println("-------- Temp = " + k + ", move = " + l + "-------- ");
                            System.out.println("Solution min : ");
                            solutionMin.affiche();
                            System.out.println("Solution opt : ");
                            this.quadratic.getSolution().affiche();
                        }
                    }
                }
            }

            temperature *= this.temperatureDecreaseCoeff;
        }

        if(log) {
            this.logger.closeFile();
        }

        this.loggerResults.closeFile();

        return solutionMin;
    }
}
