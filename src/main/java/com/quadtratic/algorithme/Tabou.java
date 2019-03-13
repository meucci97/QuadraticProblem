package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.Comparator;

public class Tabou {

    private int maxIter;
    private Quadratic q;
    private ArrayList<int[]> tabouList;
    private int tabouListSize;

    public Tabou(Quadratic q, int maxIter, int tabouListSize) {
        this.maxIter = maxIter;
        this.q = q;
        this.tabouListSize = tabouListSize;
        this.tabouList = new ArrayList<>();
    }

    public Solution tabuSearch(Solution solutionInitial) {
        q.setSolution(solutionInitial);

        for (int i = 0; i < this.maxIter; i++) {
            // 1. V(xi) = Liste des permutations
            ArrayList<Solution> solutions = q.getPossibleSolutions();

            // Solution optimale avec fitness
            Solution sOptimale = solutions.stream().min(Comparator.comparing(Solution::getFitness)).get();

            // 4. DeltaF = valeurFitness(xi+1) - valeurFitness(xi)
            double deltaF = sOptimale.getFitness() - q.getSolution().getFitness();

            // 5. Si deltaF < 0 alors on ajoute m^-1 a T
            if (deltaF < 0) {
                this.addToTabouList(sOptimale.getPermutation());
            }

            // 6. Si f(xi+1) < fMin alors xMin = xi+1 et fMin = f(xi+1)
            if(sOptimale.getFitness() < q.getSolution().getFitness()) {
                q.setSolution(sOptimale);
            }
        }

        return q.getSolution();
    }

    private void addToTabouList(int[] permutation) {
        if(this.tabouList.size() < this.tabouListSize) {
            this.tabouList.add(permutation);
        } else {
            this.tabouList.remove(0);
            this.tabouList.add(permutation);
        }
    }

    public int getMaxIter() {
        return maxIter;
    }

    public void setMaxIter(int maxIter) {
        this.maxIter = maxIter;
    }
}
