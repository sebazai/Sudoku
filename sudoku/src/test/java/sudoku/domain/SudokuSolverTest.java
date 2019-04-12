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
    int[][] fakeSudoku;
    int[][] solvedSudoku;
    
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
        fakeSudoku = new int[][]{
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
        solvedSudoku = new int[][]{
            {1,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {3,1,2,6,4,5,9,7,8},
            {6,4,5,9,7,8,3,1,2},
            {9,7,8,3,1,2,6,4,5},
            {2,3,1,5,6,4,8,9,7},
            {5,6,4,8,9,7,2,3,1},
            {8,9,7,2,3,1,5,6,4},
        };
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void checkIfSafeMethodWorksAsFalse() {
        assertFalse(solver.checkIfSafe(fakeSudoku, 0, 4, 9));
    }
    
    @Test
    public void checkIfSafeMethodReturnsTrue() {
        assertTrue(solver.checkIfSafe(fakeSudoku, 8, 0, 6));
    }
    
    @Test
    public void checkIfRowSafeMethodReturnsFalse() {
        assertFalse(solver.checkIfRowSafe(fakeSudoku, 4, 9));
    }
    
    @Test
    public void checkIfRowSafeMethodReturnsTrue() {
        assertTrue(solver.checkIfRowSafe(fakeSudoku, 8, 9));
    }
    
    @Test
    public void checkIfColumnSafeMethodReturnsFalse() {
        assertFalse(solver.checkIfColumnSafe(fakeSudoku, 1, 9));
    }
    
    @Test
    public void checkIfColumnSafeMethodReturnsTrue() {
        assertTrue(solver.checkIfColumnSafe(fakeSudoku, 8, 1));
    }
    
    @Test
    public void checkIfBoxSafeMethodReturnsFalse() {
        assertFalse(solver.checkIfBoxSafe(fakeSudoku, 4, 0, 9));
    }
    
    @Test
    public void checkIfBoxSafeMethodReturnsTrue() {
        assertTrue(solver.checkIfBoxSafe(fakeSudoku, 4, 0, 8));
    }
    
    @Test
    public void sudokuContainsZeros() {
        assertTrue(solver.sudokuContainsZeros(fakeSudoku));
    }
    
    @Test
    public void sudokuContainsNoZeros() {
        assertFalse(solver.sudokuContainsZeros(solvedSudoku));
    }
    
    @Test
    public void solveSudokuWorks() {
        int[][] solvableSudoku = new int[][]{
            {1,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };
        assertTrue(solver.solveSudoku(solvableSudoku));
    }
    
    @Test
    public void solveSudokuReturnsFalse() {
        int[][] unSolvableSudoku = new int[][]{
            {0,2,0,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {3,1,2,6,4,5,9,7,8},
            {6,4,5,9,7,8,3,1,2},
            {9,7,8,3,1,2,6,4,5},
            {1,3,0,5,6,4,8,9,7},
            {5,6,4,8,9,7,2,3,1},
            {8,9,7,2,3,1,5,6,4},
        };
        assertFalse(solver.solveSudoku(unSolvableSudoku));
    }
    
    @Test
    public void checkIfFilledSudokuIsValiWithAValidSudokuBoard() {
        assertTrue(solver.checkIfFilledSudokuIsValid(solvedSudoku, new int[9][9]));
    }
    
    @Test
    public void checkIfFilledSudokuIsValidWithAnInvladinBoard() {
        int[][] playableSudoku = new int[][]{
            {0,2,0,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {3,1,2,6,4,5,9,7,8},
            {6,4,5,9,7,8,3,1,2},
            {9,7,8,3,1,2,6,4,5},
            {1,3,0,5,6,4,8,9,7},
            {5,6,4,8,9,7,2,3,1},
            {8,9,7,2,3,1,5,6,4},
        };
        int[][] initialSudoku = new int[][]{
            {2,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };
        assertFalse(solver.checkIfFilledSudokuIsValid(playableSudoku, initialSudoku));
    }
}
