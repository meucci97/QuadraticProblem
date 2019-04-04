package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private double fitness;
    private int[] permutation;
    private int[] solution;

    public int[] getSolution() {
        return solution;
    }

    public void setSolution(int[] solution) {
        this.solution = solution;
    }

    public Solution() {
        permutation = new int[2];
    }
    public Solution(Solution s) {
        this.fitness= s.fitness;
        this.solution = new int[s.getSolution().length];
        for(int i = 0 ; i<this.solution.length; i++){
            this.solution[i] = s.getSolutionValue(i);
        }
        permutation = new int[2];
    }

    public double  getFitness() {
        return fitness;
    }

    public void affiche(){

        for (int i = 0; i<this.solution.length ;i++){
            System.out.print(this.solution[i]+" ");
        }
        System.out.println(" Fitness :"+ this.fitness);
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int[] getPermutation(){return this.permutation;}

    public void setPermutation(int i , int j){
        this.permutation[0]=i;
        this.permutation[1]=j;
        int temp = this.solution[i];
        this.solution[i]=this.solution[j];
        this.solution[j]=temp;
    }

    public int getSolutionValue(int i) {
        return this.solution[i];
    }
}
