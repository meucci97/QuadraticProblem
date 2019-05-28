package com.quadtratic.algorithme;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private String filename;
    private FileWriter fileWriter;
    private File logFile;

    public Logger(String filename, String algo) {
        this.filename = filename;

        try {
            String file = this.filename + ".txt";
            logFile = new File("./src/main/resources/logs/" + algo + "/" + file);
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

    public void closeFile() {
        try {
            this.fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
