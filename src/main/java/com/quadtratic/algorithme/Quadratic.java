package com.quadtratic.algorithme;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quadratic {

    int size;
    private int [][] connectionsTab;
    private int [][] distancesTab;
    private Solution solution;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public int getSize() {
        return size;
    }

    public Quadratic(String fileName){
        try{
            this.fileName=fileName;
            File file = new File("./src/main/resources/data/"+this.fileName);
            Scanner scnr = new Scanner(file);

            int size = Integer.parseInt(scnr.nextLine().trim());
            connectionsTab = new int[size][size];
            distancesTab = new int[size][size];
            scnr.nextLine();

            for(int i = 0; i<size; i++){
                String readline[] = (scnr.nextLine()).split("\\s+");
                for(int j = 1 ; j<size+1;j++){
                    distancesTab[i][j-1] = Integer.parseInt(readline[j]);
                }
            }
            scnr.nextLine();

            for(int i = 0; i<size; i++){
                String readline[] = (scnr.nextLine()).split("\\s+");
                for(int j = 1 ; j<size+1;j++){
                    connectionsTab[i][j-1] = Integer.parseInt(readline[j]);
                }
            }

            this.size = size;
            scnr.close();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }

    public void affiche() {
        System.out.println("data");
        for(int i= 0 ; i<this.size ; i++){
            for(int j= 0 ; j<this.size ; j++){
                System.out.print(connectionsTab[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        for(int i = 0 ; i<this.size ; i++){
            for(int j = 0 ; j<this.size ; j++){
                System.out.print(distancesTab[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getConnectionsTab(int i, int y) {
        return connectionsTab[i][y];
    }

    public int getDistancesTab(int i, int y) {
        return distancesTab[i][y];
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public ArrayList<Solution> getPossibleSolutions(){
        ArrayList<Solution> possibleSolution = new ArrayList();
        for(int i=0; i<this.solution.getSolution().length;i++){
            for(int j=i+1; j<this.solution.getSolution().length;j++){

                if(i != j){
                        Solution s = new Solution(this.solution);
                        s.setPermutation(i,j);
                        s.setFitness(this.solution.getFitness()+ getDelta(s));
                        possibleSolution.add(s);
                }
            }
        }
        return possibleSolution;
    }

    public double calculateSolutionFitness(Solution s){
        int fitness = 0;
        for(int i=0; i<s.getSolution().length;i++){
            for(int j=i+1; j<s.getSolution().length;j++){

                int solutionValue = s.getSolutionValue(i)-1;
                int solutionValue2 = s.getSolutionValue(j)-1;

                fitness += this.connectionsTab[solutionValue][solutionValue2] * this.distancesTab[i][j];
            }
        }
        return fitness;
    }

    public double getDelta(Solution s){
        int delta = 0;
        
        int p1 = s.getPermutation()[0];
        int p2 = s.getPermutation()[1];

        for(int i=0; i<s.getSolution().length;i++){
            if(i != p1 && i !=p2){
                int permutationValue1 = s.getSolutionValue(p1)-1;
                int permutationValue2 = s.getSolutionValue(p2)-1;
                int solutionValueK = s.getSolutionValue(i)-1;

                delta += (this.connectionsTab[permutationValue1][solutionValueK] - this.connectionsTab[permutationValue2][solutionValueK]) * (this.distancesTab[p1][i] - this.distancesTab[p2][i]);

            }
        }

        return delta;
    }
}
