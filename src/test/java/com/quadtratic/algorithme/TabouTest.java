package com.quadtratic.algorithme;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TabouTest {

    private Quadratic quadratic;
    private int[] solutions;

    @BeforeEach
    void setUp() {
        quadratic = new Quadratic("tai12a.dat");
        solutions = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    }

    @Test
    void tabuSearch193Iterations() {

        Solution s1 = new Solution();
        s1.setSolution(solutions);

        Tabou t = new Tabou(quadratic, 193, 9, true);
        Solution optimale1 = t.tabuSearch(s1);

        int[] expectedSolution1 = {8, 1, 6, 2, 11, 10, 3, 5, 9, 7, 12, 4};

        Assertions.assertEquals(112208.0, optimale1.getFitness());
        Assertions.assertArrayEquals(expectedSolution1, optimale1.getSolution());
    }

    @Test
    void tabuSearch7Iterations() {

        Solution s2 = new Solution();
        s2.setSolution(solutions);

        Tabou t2 = new Tabou(quadratic, 7, 9, true);

        Solution optimale2 = t2.tabuSearch(s2);
        int[] expectedSolution2 = {10, 12, 2, 6, 4, 5, 7, 9, 8, 1, 11, 3};

        Assertions.assertEquals(129268.0, optimale2.getFitness());
        Assertions.assertArrayEquals(expectedSolution2, optimale2.getSolution());
    }

    @Test
    void tabuSearch2000Iterations() {

        Solution s3 = new Solution();
        s3.setSolution(solutions);
        
        Tabou t3 = new Tabou(quadratic, 2000, 9, true);

        Solution optimale3 = t3.tabuSearch(s3);
        int[] expectedSolution3 = {8, 1, 6, 2, 11, 10, 3, 5, 9, 7, 12, 4};

        Assertions.assertEquals(112208.0, optimale3.getFitness());
        Assertions.assertArrayEquals(expectedSolution3, optimale3.getSolution());
    }
}
