package com.quadtratic.algorithme;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private String filename;
    private FileWriter fileWriter;
    private File logFile;

    public Logger(String filename, String algo, String logType) {
        this.filename = filename;

        try {

            if("log".equals(logType)) {
                String file = this.filename + ".txt";
                logFile = new File("./src/main/resources/logs/" + algo + "/" + file);
            } else {
                String file = this.filename + ".csv";
                logFile = new File("./src/main/resources/results/" + algo + "/" + file);
            }

            fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLogInFile(Solution solutionMin, Solution solutionOptimale) throws IOException {

            fileWriter.write("\nSolution min : ");
            for (int i = 0; i < solutionMin.getSolution().length; i++) {
                fileWriter.write(solutionMin.getSolutionValue(i) + " ");
            }
            fileWriter.write("Fitness : " + solutionMin.getFitness() + "\n");

            fileWriter.write("Solution opt : ");
            for (int j = 0; j < solutionOptimale.getSolution().length; j++) {
                fileWriter.write(solutionOptimale.getSolutionValue(j) + " ");
            }
            fileWriter.write("Fitness : " + solutionOptimale.getFitness() + "\n\n");

            fileWriter.flush();
    }

    public void writeLogInFileTabou(Solution solutionMin, Solution solutionOptimale, int iteration) {
        try {
            fileWriter.write("-------- Iteration " + iteration + " -------- \n");
            this.writeLogInFile(solutionMin, solutionOptimale);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLogInFileRecuitSimule(Solution solutionMin, Solution solutionOptimale, int temp, int move) {
        try {
            fileWriter.write("-------- Temp = " + temp + ", move = " + move + "--------\n");

            this.writeLogInFile(solutionMin, solutionOptimale);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResultsInFileTabou() {

    }

    public void writeResultsInFileRecuitSimule() {

    }

    public void initializeFirstLineResultsTabou() {
        try {
            fileWriter.write("Id; nom_fichier; taille_tabou; iteration; fitness; fitness_optimisee\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeFirstLineResultsRecuitSimule() {
        try {
            fileWriter.write("Id; nom_fichier; coefficient_temp; changement_temp; mouvements_temp; temperature_initiale; fitness; fitness_optimisee\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        try {
            this.fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
