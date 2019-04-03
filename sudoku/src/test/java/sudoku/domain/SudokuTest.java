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
public class SudokuTest {
    Sudoku sudoku;
    public SudokuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sudoku = new Sudoku(1);
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
        assertFalse(sudoku.checkIfSafe(fakeSudoku, 0, 4, 9));
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
        assertTrue(sudoku.checkIfSafe(fakeSudoku, 8, 0, 6));
    }

    @Test
    public void generateAndCheckSudoku() {
        boolean sudokuWorks = true;
        // Go through the whole sudoku 9*9 and see if the generated sudoku is valid.
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Take the number in the generated sudoku
                int number = sudoku.solvedSudoku[i][j];
                sudoku.solvedSudoku[i][j] = 0;
                // Assign it to zero and check if it can be placed there
                if(!sudoku.checkIfSafe(sudoku.solvedSudoku, i, j, number)) {
                    sudokuWorks = false;
                }
                sudoku.solvedSudoku[i][j] = number;
            }
        }
        assertTrue(sudokuWorks);
    }
    @Test
    public void generateSudokuAndCheckIfZeros() {
        assertFalse(sudoku.sudokuContainsZeros(sudoku.solvedSudoku));
    }
}
