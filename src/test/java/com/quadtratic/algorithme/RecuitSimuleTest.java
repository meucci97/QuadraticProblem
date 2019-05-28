package com.quadtratic.algorithme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecuitSimuleTest {

    private Quadratic quadratic;
    private int[] solutions;

    @BeforeEach
    void setUp() {
        quadratic = new Quadratic("taillard12.dat");
        solutions = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    }
}
