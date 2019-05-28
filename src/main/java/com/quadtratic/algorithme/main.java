package com.quadtratic.algorithme;

public class main {
    public static void main (String[] args) {

        long timeLaunch = System.currentTimeMillis();
        launchRecuitSimule();
        long timeToExecute = System.currentTimeMillis() - timeLaunch;

        System.out.println("\nTemps d'éxecution : " + timeToExecute + " ms");
    }

    private static void launchTabou() {
        Quadratic q2 = new Quadratic("tai12a.dat");
        q2.affiche();
        Solution s2 = new Solution();
        int[] i= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

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

    private static void launchRecuitSimule() {
        Quadratic q3 = new Quadratic("test.dat");
        q3.affiche();
        Solution s3 = new Solution();

        int[] i= {1, 3, 4, 5, 2};
        s3.setSolution(i);

        RecuitSimule r = new RecuitSimule(q3, true, 0.1, 1000, 100);
        Solution solutionRecuit = r.recuitSimuleSearch(s3, 18);

        System.out.println("Fitness : " + solutionRecuit.getFitness());
        for(int x=0; x < solutionRecuit.getSolution().length;x++){
            System.out.print(solutionRecuit.getSolutionValue(x)+" ");
        }
    }
}
