package com.quadtratic.algorithme;

public class main {
    public static void main (String[] args){
        /*
        Quadratic q = new Quadratic("test.dat");
        q.affiche();
        Solution s = new Solution();
        int[] i= {1,3,4,5,2};
        s.setSolution(i);
        q.setSolution(s);
        ArrayList<Solution> temp = q.getPossibleSolutions();

        temp.stream().forEach(el -> {
            for(int j=0; j<el.getSolution().length;j++){
                System.out.print(el.getSolution()[j]);
            }
            System.out.print(" Fitness: "+ el.getFitness());
            System.out.println();
        });
        */

        Quadratic q2 = new Quadratic("tai15a.dat");
        q2.affiche();
        Solution s2 = new Solution();
        int[] i= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,14,15};

        //8,1,6,2,11,10,3,5,9,7,12,4 224416.0
        s2.setSolution(i);


        Tabou t = new Tabou(q2,1000, 9, true);
        Solution optimale2 = t.tabuSearch(s2);

        System.out.println("Fitess: "+optimale2.getFitness()*2);
        System.out.print("Solution: ");
        for(int x=0; x < optimale2.getSolution().length;x++){
            System.out.print(optimale2.getSolutionValue(x)+" ");
        }
    }
}
