package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Tabou extends Algorithm {

    private int maxIter;
    private List<String> tabouList;
    private int tabouListSize;

    public Tabou(Quadratic quadratic, Solution solutionInitiale, boolean log, int maxIter, int tabouListSize) {
        super(quadratic, solutionInitiale, log);
        this.maxIter = maxIter;
        this.tabouListSize = tabouListSize;
        this.tabouList = new ArrayList<>();

        this.loggerResults = new Logger(quadratic, maxIter, tabouListSize);
        this.loggerResults.initializeFirstLineResultsTabou();

        if(log) {
            this.logger = new Logger(quadratic.getFileName(), "tabou");
        } else {
            this.logger = null;
        }
    }

    @Override
    public Solution evaluateSolution() {
        solutionInitiale.setFitness(this.quadratic.calculateSolutionFitness(solutionInitiale));
        this.quadratic.setSolution(solutionInitiale);

        for (int i = 0; i < this.maxIter; i++) {
            // 1. V(xi) = Liste des permutations
            ArrayList<Solution> solutions = this.quadratic.getPossibleSolutions();
            solutions = removeExistingPermutation(solutions);
            // Solution optimale avec fitness
            Solution sOptimale = solutions.stream().min(Comparator.comparing(s-> s.getFitness())).get();

            //Solution sOptimale = solutions.stream().min(Comparator.comparingDouble(Solution::getFitness)).get();

            // 5. Si deltaF < 0 alors on ajoute m^-1 a T
            if (sOptimale.getFitness() >  this.quadratic.getSolution().getFitness()) {
                this.addToTabouList(sOptimale.getPermutation());
            }

            // 6. Si f(xi+1) < fMin alors xMin = xi+1 et fMin = f(xi+1)
            if(sOptimale.getFitness() < this.solutionMin.getFitness()) {
                this.solutionMin = sOptimale;
            }
            this.quadratic.setSolution(sOptimale);

            this.loggerResults.writeResultsInFileTabou();

            if(log) {
                // On écrit les logs dans un fichier
                this.logger.writeLogInFileTabou(this.solutionMin, this.quadratic.getSolution(), i);
            } else {
                System.out.println("-------- Iteration " + i + "--------");
                System.out.print("Solution min : ");
                this.solutionMin.affiche();
                System.out.print("Solution opt : ");
                this.quadratic.getSolution().affiche();
            }
        }

        if(log) {
            this.logger.closeFile();
        }

        this.loggerResults.closeFile();

        return solutionMin;
    }

    private ArrayList<Solution> removeExistingPermutation(ArrayList<Solution> solutions){
        return solutions.stream()
                .filter(el -> tabouList.stream().noneMatch(match -> match.equals(el.getPermutation()[0] + "-" + el.getPermutation()[1])))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void addToTabouList(int[] permutation) {
        if(this.tabouList.size() < this.tabouListSize) {
            this.tabouList.add(permutation[0] + "-" + permutation[1]);
        } else {
            this.tabouList.remove(0);
            this.tabouList.add(permutation[0] + "-" + permutation[1]);
        }
    }
}
