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
public class SudokuGeneratorTest {
    SudokuGenerator generator;
    SudokuSolver solver;
    public SudokuGeneratorTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        generator = new SudokuGenerator();
        solver = new SudokuSolver();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void generateSudokuContainsHasNoEmptyValues() {
        int[][] sudoku = generator.generateSudoku();
        assertFalse(solver.sudokuContainsZeros(sudoku));
    }

    @Test
    public void fillBoxesDiagonallyWorks() {
        generator.fillBoxesDiagonally();
        boolean noZeros = true;
        for (int diagonal = 0; diagonal < 9; diagonal = diagonal + 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (generator.solvedSudoku[i+diagonal][j+diagonal] == 0) {
                        noZeros = false;
                    }
                }
            }
        }
        assertTrue(noZeros);
    }
    
    @Test
    public void fillBoxMethodFillsBox() {
        generator.fillBox(0);
        boolean noZeros = true;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(generator.solvedSudoku[i][j] == 0) {
                    noZeros = false;
                }
            }
        }
        assertTrue(noZeros);
    }
    
    @Test
    public void numberNotInBoxWithWorkingSudoku() {
        generator.solvedSudoku = new int[][] {
            {1,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,0,1,2,3,4,5,6},
            {0,0,0,0,0,0,0,0,0},
            {0,9,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };
        assertTrue(generator.numberNotInBox(0, 9));
    }
    
    @Test
    public void numberNotInBoxShouldReturnFalse() {
        generator.solvedSudoku = new int[][] {
            {1,2,3,4,5,6,7,8,9},
            {4,0,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {0,0,0,0,0,0,0,0,0},
            {0,9,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };
        assertFalse(generator.numberNotInBox(0, 9));
    }
    
    @Test
    public void fillEmptySquaresFillsEmptySquares() {
        generator.fillEmptySquares();
        boolean emptySquares = false;
        for (int i = 0; i < 9 ; i++) {
            for (int j = 0; j < 9; j++) {
                if (generator.solvedSudoku[i][j] == 0) {
                    emptySquares = true;
                }
            } 
        }
        assertFalse(emptySquares);
    }

}
