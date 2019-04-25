/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;


public class SudokuGenerator {
    int[][] solvedSudoku;
    SudokuSolver solver;

    public SudokuGenerator() {
        solvedSudoku = new int[9 + 1][9 + 1];
        solver = new SudokuSolver();
    }
    
    public SudokuSolver getSolver() {
        return this.solver;
    }
    
    /**
     * Generates a sudoku in the solvedSudoku array
     * 
     * @return int[][] solvedSudoku array 
     */
    public int[][] generateSudoku() {
        fillBoxesDiagonally();
        fillEmptySquares();
        return solvedSudoku;
    }
    
    
    /**
     * Fills the top left, middle and bottom right 3x3 sudoku box
     */
    public void fillBoxesDiagonally() {
        for (int i = 0; i < 9; i = i + 3) {
            fillBox(i);
        }
    }
    
    /**
     * Method used to fill the 3x3 box with numbers 1-9
     * @param rowAndColumn the row and column number of the box top left corner
     */
    
    public void fillBox(int rowAndColumn) {
        int number;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    number = (int) Math.floor(Math.random() * 10);
                } 
                while (!numberNotInBox(rowAndColumn, number));
                solvedSudoku[rowAndColumn + i][rowAndColumn + j] = number;
            }
        }
    }
    /**
     * Checks if the given number is currently not in the 3x3 box
     * 
     * @param rowAndColumn the row and column number of the box top left corner
     * @param number    number to be checked if it is in the 3x3 box
     * @return  true    if the number given is not in the box
     */
    
    public boolean numberNotInBox(int rowAndColumn, int number) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (solvedSudoku[i + rowAndColumn][j + rowAndColumn] == number) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    /**
     * Method used to fill all the empty squares by trial and error after calling fillBoxesDiagonally
     */
    public void fillEmptySquares() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (solvedSudoku[i][j] == 0) {
                    for (int number = 1; number <= 9; number++) {
                        if (solver.checkIfSafe(solvedSudoku, i, j, number)) {
                            solvedSudoku[i][j] = number;
                            if (!solver.solveSudoku(solvedSudoku)) {
                                solvedSudoku[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    
}
