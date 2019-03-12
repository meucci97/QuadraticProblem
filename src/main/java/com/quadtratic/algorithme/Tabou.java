package com.quadtratic.algorithme;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabou {

    private int maxIter;
    private Quadratic q;

    public Tabou( Quadratic q, int maxIter) {
        this.maxIter = maxIter;
        this.q = q;
    }

    public Solution tabuSearch(Solution solutionInitial) {
        q.setSolution(solutionInitial);
        int fMin = q.getSolution().getFitness();
        List<String> T = new ArrayList<>();

        for (int i = 0; i < this.maxIter; i++) {
            // 1. V(xi) = Liste des permutations
            ArrayList<Solution> solutions = q.getPossibleSolution();

            // 3. On prend la plus petite fitness et on la met dans une valeur(xi+1)

            //Solution sOptimale = // Recuperer la solution avec la fitness minimal

            // 4. DeltaF = valeurFitness(xi+1) - valeurFitness(xi)

            // 5. Si deltaF < 0 alors on ajoute m^-1 a T

            // 6. Si f(xi+1) < fMin alors xMin = xi+1 et fMin = f(xi+1)
        }

        return q.getSolution();
    }

    public int getMaxIter() {
        return maxIter;
    }

    public void setMaxIter(int maxIter) {
        this.maxIter = maxIter;
    }
}
