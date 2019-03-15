package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Tabou {

    private int maxIter;
    private Quadratic q;
    private ArrayList<String> tabouList;
    private int tabouListSize;
    private Solution solutionMin;

    public Tabou(Quadratic q, int maxIter, int tabouListSize) {
        this.maxIter = maxIter;
        this.q = q;
        this.tabouListSize = tabouListSize;
        this.tabouList = new ArrayList();
    }

    public Solution tabuSearch(Solution solutionInitial) {
        solutionInitial.setFitness(q.calculateSolutionFitness(solutionInitial));
        solutionMin = solutionInitial;
        q.setSolution(solutionInitial);

        for (int i = 0; i < this.maxIter; i++) {
            // 1. V(xi) = Liste des permutations
            ArrayList<Solution> solutions = q.getPossibleSolutions();
            solutions = removeExistingPermutation(solutions);
            // Solution optimale avec fitness
            Solution sOptimale = solutions.stream().min(Comparator.comparing(s-> s.getFitness())).get();

            // 4. DeltaF = valeurFitness(xi+1) - valeurFitness(xi)
            //double deltaF = sOptimale.getFitness() - q.getSolution().getFitness();

            // 5. Si deltaF < 0 alors on ajoute m^-1 a T
            if (sOptimale.getFitness() <  q.getSolution().getFitness()) {
                this.addToTabouList(sOptimale.getPermutation());
            }

            // 6. Si f(xi+1) < fMin alors xMin = xi+1 et fMin = f(xi+1)
            if(sOptimale.getFitness() < solutionMin.getFitness()) {
                solutionMin = sOptimale;
            }
            q.setSolution(sOptimale);
            solutionMin.affiche();
            q.getSolution().affiche();
            System.out.println("--------------------");
        }

        return solutionMin;
    }
    private ArrayList<Solution> removeExistingPermutation(ArrayList<Solution> solutions){
        return solutions.stream()
                .filter(el -> tabouList.stream().noneMatch(match -> match.equals(el.getPermutation()[0]+"-"+el.getPermutation()[1])))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    private void addToTabouList(int[] permutation) {
        if(this.tabouList.size() < this.tabouListSize) {
            this.tabouList.add(permutation[0]+"-"+permutation[1]);
        } else {
            this.tabouList.remove(0);
            this.tabouList.add(permutation[0]+"-"+permutation[1]);
        }
    }

    public int getMaxIter() {
        return maxIter;
    }

    public void setMaxIter(int maxIter) {
        this.maxIter = maxIter;
    }
}
