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
    private boolean log;
    private Logger logger = null;

    public Tabou(Quadratic q, int maxIter, int tabouListSize) {
        this.maxIter = maxIter;
        this.q = q;
        this.tabouListSize = tabouListSize;
        this.tabouList = new ArrayList();
        this.log = false;
    }

    public Tabou(Quadratic q, int maxIter, int tabouListSize, boolean log) {
        this.maxIter = maxIter;
        this.q = q;
        this.tabouListSize = tabouListSize;
        this.tabouList = new ArrayList();
        this.log = log;
        this.logger = new Logger(q.getFileName(), "tabou");
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

            //Solution sOptimale = solutions.stream().min(Comparator.comparingDouble(Solution::getFitness)).get();

            // 5. Si deltaF < 0 alors on ajoute m^-1 a T
            if (sOptimale.getFitness() >  q.getSolution().getFitness()) {
                this.addToTabouList(sOptimale.getPermutation());
            }

            // 6. Si f(xi+1) < fMin alors xMin = xi+1 et fMin = f(xi+1)
            if(sOptimale.getFitness() < solutionMin.getFitness()) {
                solutionMin = sOptimale;
            }
            q.setSolution(sOptimale);

            if(log) {
                // On écrit les logs dans un fichier
                this.logger.writeLogInFileTabou(solutionMin, q.getSolution(), i);
            } else {
                System.out.println("-------- Iteration " + i + "--------");
                System.out.print("Solution min : ");
                solutionMin.affiche();
                System.out.print("Solution opt : ");
                q.getSolution().affiche();
            }

        }

        this.logger.closeFile();

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

}
