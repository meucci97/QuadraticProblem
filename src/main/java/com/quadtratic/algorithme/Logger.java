package com.quadtratic.algorithme;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private String filename;
    private FileWriter fileWriter;
    private File logFile;
    private File resultsFile;

    public String getFilename() {
        return filename;
    }

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

    public Logger(Quadratic quadratic, int maxIter, int tabouListSize) {
        this.filename = "tabou_" + quadratic.getSize() + "_" + maxIter + "_" + tabouListSize;

        try {
            String file = this.filename + "_.csv";
            resultsFile = new File("./src/main/resources/results/tabou/" + file);

            fileWriter = new FileWriter(resultsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger(Quadratic quadratic, double temperatureDecreaseCoeff, int changesOfTemp, int movesAtTemp,
                  double initialTemperature) {
        this.filename = "recuit_" + quadratic.getSize()+ "_" + String.format("%.2f",temperatureDecreaseCoeff) + "_" +
                changesOfTemp + "_" + movesAtTemp + "_" + initialTemperature;

        try {
            String file = this.filename + ".csv";
            resultsFile = new File("./src/main/resources/results/recuit/" + file);

            fileWriter = new FileWriter(resultsFile);
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

    public void writeResultsInFileTabou(String filename, int tabouSize, int iteration, double fitness, double fitnessOpt) {
        try {
            fileWriter.write("\n" + filename + ";" + tabouSize + ";" + iteration + ";" + String.format("%.3f", fitness) + ";" +String.format("%.3f", fitnessOpt));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResultsInFileRecuitSimule(String filename, double coeffInit, double coeff, int changesOfTemps,
                                               int temp, int movesAtTemp, int move, double tempInit,int iteration,double fitness,
                                               double fitnessOpt) {
        try {
            fileWriter.write("\n'" + filename + "';" +  String.format("%.2f", coeffInit) + ";" + String.format("%.2f",coeff) + ";" + changesOfTemps + ";" + temp
                    + ";" + movesAtTemp + ";" + move + ";" + String.format("%.2f", tempInit) + ";" + iteration + ";" + String.format("%.0f", fitness) + ";" + String.format("%.0f", fitnessOpt));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeFirstLineResultsTabou() {
        try {
            fileWriter.write("nom_fichier;taille_tabou;iteration;fitnessMin;fitness");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeFirstLineResultsRecuitSimule() {
        try {
            fileWriter.write("nom_fichier;coefficient_temp_init;coefficient_temp;changement_temp_init;changement_temp;mouvements_temp_init;mouvement_temp;temperature_initiale;iteration;fitnessMin;fitness");
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
