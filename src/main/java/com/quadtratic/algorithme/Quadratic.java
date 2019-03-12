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

    public Quadratic(String fileName){
        try{

            File file = new File("./src/main/resources/data/"+fileName);
            Scanner scnr = new Scanner(file);

            int size = Integer.parseInt(scnr.nextLine().trim());
            connectionsTab = new int[size][size];
            distancesTab = new int[size][size];
            scnr.nextLine();

            for(int i = 0; i<size; i++){
                String readline[] = (scnr.nextLine()).split("\\s+");
                System.out.println(readline);
                for(int j = 1 ; j<size+1;j++){
                    connectionsTab[i][j-1] = Integer.parseInt(readline[j]);
                }
            }
            scnr.nextLine();

            for(int i = 0; i<size; i++){
                String readline[] = (scnr.nextLine()).split("\\s+");
                System.out.println(readline);
                for(int j = 1 ; j<size+1;j++){
                    distancesTab[i][j-1] = Integer.parseInt(readline[j]);
                }
            }

            this.size = size;

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

        return new ArrayList<Solution>();
    }
}
