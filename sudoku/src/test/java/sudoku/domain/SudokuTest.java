/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import java.time.Instant;
import java.util.Date;
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
    SudokuGenerator generator;
    SudokuSolver solver;
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
        sudoku = new Sudoku(35);
        generator = new SudokuGenerator();
        solver = new SudokuSolver();
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void deepCopyWorks() {
        boolean works = true;
        int[][] sudokuTable = new int[][]{
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
        int[][] copy = sudoku.deepCopy(sudokuTable);
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                if(copy[i][j] != sudokuTable[i][j]) {
                    works = false;
                }
            }
        }
        assertTrue(works);
    }
    
    @Test
    public void stringTo2DIntArray() {
        boolean matchesOriginal = true;
        String test = "[[8, 3, 2, 1, 5, 4, 6, 9, 7, 0], [6, 9, 4, 7, 2, 3, 5, 1, 8, 0], [7, 5, 1, 6, 8, 9, 3, 4, 2, 0], [1, 2, 5, 3, 4, 8, 7, 6, 9, 0], [4, 6, 9, 2, 7, 1, 8, 3, 5, 0], [3, 7, 8, 5, 9, 6, 4, 2, 1, 0], [2, 1, 6, 8, 3, 5, 9, 7, 4, 0], [5, 4, 3, 9, 1, 7, 2, 8, 6, 0], [9, 8, 7, 4, 6, 2, 1, 5, 3, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]";
        int[][] sudokuTable = new int[][]{
            {8, 3, 2, 1, 5, 4, 6, 9, 7, 0},
            {6, 9, 4, 7, 2, 3, 5, 1, 8, 0},
            {7, 5, 1, 6, 8, 9, 3, 4, 2, 0},
            {1, 2, 5, 3, 4, 8, 7, 6, 9, 0},
            {4, 6, 9, 2, 7, 1, 8, 3, 5, 0},
            {3, 7, 8, 5, 9, 6, 4, 2, 1, 0},
            {2, 1, 6, 8, 3, 5, 9, 7, 4, 0},
            {5, 4, 3, 9, 1, 7, 2, 8, 6, 0},
            {9, 8, 7, 4, 6, 2, 1, 5, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] copiedToArray = sudoku.stringTo2DInt(test);
        for (int i = 0; i < sudokuTable.length; i++) {
            for (int j = 0; j < sudokuTable[0].length; j++) {
                if (sudokuTable[i][j] != copiedToArray[i][j]) {
                    matchesOriginal = false;
                }
            }
        }
        assertTrue(matchesOriginal);
    }
    
    @Test
    public void compareToWithSameTime() {
        Instant now = Instant.now();
        sudoku.time = now;
        Sudoku testTime = new Sudoku(35);
        testTime.time = now;
        assertEquals(0, sudoku.compareTo(testTime));
    }
    
    @Test
    public void compareToReturnsPositive() {
        Instant now = Instant.now();
        sudoku.time = now;
        Sudoku testTime = new Sudoku(35);
        Date date = new Date();
        testTime.time = date.toInstant();
        boolean isPositive = false;
        if (sudoku.compareTo(testTime) > 0) {
            isPositive = true;
        }
        assertTrue(isPositive);
    }
    
    @Test
    public void removeAmountOfDigitsFromTable() {
        int amountOfZeros = 0;
        Sudoku testSudoku = new Sudoku(0);
        testSudoku.initialSudoku = new int[][]{
            {1,2,3,4,5,6,7,8,9,0},
            {4,5,6,7,8,9,1,2,3,0},
            {7,8,9,1,2,3,4,5,6,0},
            {3,1,2,6,4,5,9,7,8,0},
            {6,4,5,9,7,8,3,1,2,0},
            {9,7,8,3,1,2,6,4,5,0},
            {2,3,1,5,6,4,8,9,7,0},
            {5,6,4,8,9,7,2,3,1,0},
            {8,9,7,2,3,1,5,6,4,0},
            {0,0,0,0,0,0,0,0,0,0},
        };
        testSudoku.removeDigitsFromInitialSudoku(20);
        for (int i = 0; i < 9; i++ ) {
            for (int j = 0; j < 9; j++) {
                if (testSudoku.initialSudoku[i][j] == 0) {
                    amountOfZeros++;
                }
            }
        }
        assertEquals(amountOfZeros, 20);
    }
    
    @Test
    public void modifyingPlayableSudokuChangesNumberGivenInMatrix() {
        Sudoku testSudoku = new Sudoku(0);
        boolean works;
        testSudoku.playableSudoku = new int[9][9];
        testSudoku.initialSudoku = new int[][]{
            {0,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {3,1,2,6,4,5,9,7,8},
            {6,4,5,9,7,8,3,1,2},
            {9,7,8,3,1,2,6,4,5},
            {2,3,1,5,6,4,8,9,7},
            {5,6,4,8,9,7,2,3,1},
            {8,9,7,2,3,1,5,6,4},
        };
        testSudoku.modifyPlayableSudoku(1, 0, 0);
        if (testSudoku.playableSudoku[0][0] == 1) {
            works = true;
        } else {
            works = false;
        }
        assertTrue(works);
    }
    
    @Test
    public void modifyingPlayableSudokuDoesNotChangeIfNumberAlreadyInInitialSudoku() {
        Sudoku testSudoku = new Sudoku(0);
        boolean works;
        testSudoku.playableSudoku = new int[9][9];
        testSudoku.initialSudoku = new int[][]{
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
        testSudoku.modifyPlayableSudoku(1, 0, 0);
        if (testSudoku.playableSudoku[0][0] == 0) {
            works = true;
        } else {
            works = false;
        }
        assertTrue(works);
    }
    
    @Test
    public void modifyPlayableSudokuAcceptsOnlyValuesBetweenZeroAndNine() {
        Sudoku testSudoku = new Sudoku(0);
        boolean works;
        testSudoku.playableSudoku = new int[9][9];
        testSudoku.initialSudoku = new int[][]{
            {0,0,0,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {3,1,2,6,4,5,9,7,8},
            {6,4,5,9,7,8,3,1,2},
            {9,7,8,3,1,2,6,4,5},
            {2,3,1,5,6,4,8,9,7},
            {5,6,4,8,9,7,2,3,1},
            {8,9,7,2,3,1,5,6,4},
        };
        testSudoku.modifyPlayableSudoku(-1, 0, 0);
        testSudoku.modifyPlayableSudoku(10, 0, 0);
        if (testSudoku.playableSudoku[0][0] == 0) {
            works = true;
        } else {
            works = false;
        }
        assertTrue(works);
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
                if(!solver.checkIfSafe(sudoku.solvedSudoku, i, j, number)) {
                    sudokuWorks = false;
                }
                sudoku.solvedSudoku[i][j] = number;
            }
        }
        assertTrue(sudokuWorks);
    }
    
    @Test
    public void generateSudokuAndCheckIfZeros() {
        assertFalse(solver.sudokuContainsZeros(sudoku.solvedSudoku));
    }
    
    @Test
    public void emptyPlayableSudokuMatrix() {
        sudoku.playableSudoku = new int[][]{
            {0,0,0,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {3,1,2,6,4,5,9,7,8},
            {6,4,5,9,7,8,3,1,2},
            {9,7,8,3,1,2,6,4,5},
            {2,3,1,5,6,4,8,9,7},
            {5,6,4,8,9,7,2,3,1},
            {8,9,7,2,3,1,5,6,4},
        };
        sudoku.emptyPlayableSudokuMatrix();
        boolean isEmpty = true;
        for (int i = 0; i < sudoku.playableSudoku.length; i++) {
            for (int j = 0; j < sudoku.playableSudoku[0].length; j++) {
                if (sudoku.playableSudoku[i][j] != 0) {
                    isEmpty = false;
                }
            }
        }
        assertTrue(isEmpty);
    }
    
    @Test
    public void giveAHintAddsOneNumberToThePlayableSudokuMatrix() {
        sudoku.giveAHintToThePlayer();
        int numbers = 0;
        for (int i = 0; i < sudoku.playableSudoku.length; i++) {
            for (int j = 0; j < sudoku.playableSudoku[i].length; j++) {
                if (sudoku.playableSudoku[i][j] != 0) {
                    numbers++;
                }
            }
        }
        assertEquals(1, numbers);
    }
}
