package com.quadtratic.algorithme;

import java.util.ArrayList;

public class main {
    public static void main (String[] args){

        System.out.println("Hello");
        Quadratic q = new Quadratic("test.dat");
        q.affiche();
        Solution s = new Solution();
        int[] i= {2,0,3,4,1};
        s.setSolution(i);
        q.setSolution(s);
        ArrayList<Solution> temp = q.getPossibleSolution();

        temp.stream().forEach(el -> {
            for(int j=0; j<el.getSolution().length;j++){
                System.out.print(el.getSolution()[j]);
            }
            System.out.print(" Fitness: "+ el.getFitness());
            System.out.println();
        });

    }
}
