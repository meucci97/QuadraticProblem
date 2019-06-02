package com.quadtratic.algorithme;

import java.util.concurrent.Callable;

public abstract class Algorithm implements Callable<Solution> {

    protected Quadratic quadratic;

    @Override
    public Solution call() throws Exception {

        long timeLaunch = System.currentTimeMillis();

        Solution result = this.evaluateSolution();
        long timeToExecute = System.currentTimeMillis() - timeLaunch;

        System.out.println("\nTemps d'éxecution : " + timeToExecute + " ms");

        return result;
    }

    protected Solution solutionInitiale;
    protected Logger logger;
    protected boolean log;
    protected Solution solutionMin;
    protected Logger loggerResults;

    public Algorithm(Quadratic quadratic, Solution solutionInitiale, boolean log) {
        this.quadratic = quadratic;
        this.solutionInitiale = solutionInitiale;
        this.solutionMin = solutionInitiale;
        this.log = log;
    }

    public abstract Solution evaluateSolution();
}
