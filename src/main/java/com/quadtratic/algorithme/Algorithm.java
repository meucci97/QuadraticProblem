package com.quadtratic.algorithme;

public abstract class Algorithm {

    protected Quadratic quadratic;
    protected Solution solutionInitiale;
    protected Logger logger;
    protected boolean log;

    public Algorithm(Quadratic quadratic, Solution solutionInitiale, boolean log) {
        this.quadratic = quadratic;
        this.solutionInitiale = solutionInitiale;
        this.log = log;
    }

    public abstract Solution evaluateSolution();
}
