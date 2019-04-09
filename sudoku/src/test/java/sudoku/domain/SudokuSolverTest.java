/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebserge
 */
public class SudokuSolverTest {
    
    SudokuSolver solver;
    
    public SudokuSolverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        solver = new SudokuSolver();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void checkIfSafeMethodWorksAsFalse() {
        int[][] fakeSudoku = new int[][]{
            {1,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {0,0,0,0,0,0,0,0,0},
            {0,9,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };
        assertFalse(solver.checkIfSafe(fakeSudoku, 0, 4, 9));
    }
    
    @Test
    public void checkIfSafeMethodReturnsTrue() {
        int[][] fakeSudoku = new int[][]{
            {1,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {0,0,0,0,0,0,0,0,0},
            {0,9,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };
        assertTrue(solver.checkIfSafe(fakeSudoku, 8, 0, 6));
    }
}
