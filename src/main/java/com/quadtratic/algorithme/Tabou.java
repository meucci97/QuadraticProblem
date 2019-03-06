package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabou {

    private Permutation permutation;

    private int maxIter;

    public Permutation tabuSearch(Permutation permutationInitial) {
        Permutation xMin = permutationInitial;
        int fMin = permutation.getFitness(xMin);
        List<String> T = new ArrayList<>();

        for (int i = 0; i < this.maxIter; i++) {
            // 1. V(xi) = Liste des permutations
            List<Permutation> permutations = permutation.getPermutations();

            // 2. On calcule les fitness de toutes les permutations
            List<Integer> fitness = new ArrayList<>();

            for(int permutation = 0; permutation < permutations.size(); permutation++) {
                fitness.add(permutation.getFitness());
            }

            // 3. On prend la plus petite fitness et on la met dans une valeur(xi+1)
            int fitnessMin = fitness.indexOf(Collections.min(fitness));

            // 4. DeltaF = valeurFitness(xi+1) - valeurFitness(xi)

            // 5. Si deltaF < 0 alors on ajoute m^-1 a T

            // 6. Si f(xi+1) < fMin alors xMin = xi+1 et fMin = f(xi+1)
        }

        return xMin;
    }

    public int getMaxIter() {
        return maxIter;
    }

    public void setMaxIter(int maxIter) {
        this.maxIter = maxIter;
    }
}
