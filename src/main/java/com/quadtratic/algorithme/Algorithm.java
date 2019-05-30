package com.quadtratic.algorithme;

public abstract class Algorithm {

    protected Quadratic quadratic;
    protected Solution solutionInitiale;
    protected Logger logger;
    protected boolean log;

    public Algorithm(Quadratic quadratic, Solution solutionInitiale) {
        this.quadratic = quadratic;
        this.solutionInitiale = solutionInitiale;
    }

    public abstract Solution evaluateSolution();
}
