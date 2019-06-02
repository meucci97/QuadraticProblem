package com.quadtratic.algorithme;

public abstract class Algorithm {

    protected Quadratic quadratic;
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
