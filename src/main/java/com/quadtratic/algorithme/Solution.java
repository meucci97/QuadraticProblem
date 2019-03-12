package com.quadtratic.algorithme;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private int fitness;
    private int[] permutation;

    public Solution() {
        permutation = new int[2];
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int[] getPermutation(){return this.permutation;}

    public void setPermutation(int i , int j){
        this.permutation[0]=i;
        this.permutation[1]=j;
    }
}
