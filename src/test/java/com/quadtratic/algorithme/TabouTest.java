package com.quadtratic.algorithme;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TabouTest {

    @org.junit.jupiter.api.Test
    void tabuSearch() {
        int[] i = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        //test Tabou 193 Iteration
        Quadratic q1 = new Quadratic("tai12a.dat");
        Solution s1 = new Solution();
        s1.setSolution(i);
        Tabou t = new Tabou(q1, 193, 9, true);
        Solution optimale1 = t.tabuSearch(s1);
        int[] expectedSolution1 = {8,1,6,2,11,10,3,5,9,7,12,4};
        assertEquals(112208.0, optimale1.getFitness(), "For 193 Iterations");
        assertArrayEquals(expectedSolution1, optimale1.getSolution());


        //test Tabou 6 Iteration
        Quadratic q2 = new Quadratic("tai12a.dat");
        Solution s2 = new Solution();
        s2.setSolution(i);
        Tabou t2 = new Tabou(q2, 7, 9, true);
        Solution optimale2 = t2.tabuSearch(s2);
        int[] expectedSolution2 = {10,12,2,6,4,5,7,9,8,1,11,3};
        assertEquals(129268.0, optimale2.getFitness(), "For 7 Iterations");
        assertArrayEquals(expectedSolution2, optimale2.getSolution());

        //test Tabou 2000 Iteration
        Quadratic q3 = new Quadratic("tai12a.dat");
        Solution s3 = new Solution();
        s3.setSolution(i);
        Tabou t3 = new Tabou(q3, 2000, 9, true);
        Solution optimale3 = t3.tabuSearch(s3);
        int[] expectedSolution3 = {8,1,6,2,11,10,3,5,9,7,12,4};

        assertEquals(112208.0, optimale3.getFitness(), "For 193 Iterations");
        assertArrayEquals(expectedSolution3, optimale3.getSolution());

    }
}