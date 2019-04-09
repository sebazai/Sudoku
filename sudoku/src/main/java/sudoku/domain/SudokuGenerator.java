/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

/**
 *
 * @author sebserge
 */
public class SudokuGenerator {
    int[][] solvedSudoku;
    SudokuSolver solver;
    public SudokuGenerator() {
        solvedSudoku = new int[9 + 1][9 + 1];
        solver = new SudokuSolver();
    }
    
    public int[][] generateSudoku() {
        fillBoxesDiagonally();
        fillEmptySquares();
        return solvedSudoku;
    }
    
    private void fillBoxesDiagonally() {
        for (int i = 0; i < 9; i = i + 3) {
            fillBox(i);
        }
    }
    
    private void fillBox(int rowAndColumn) {
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
    
    private boolean numberNotInBox(int rowAndColumn, int number) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (solvedSudoku[i + rowAndColumn][j + rowAndColumn] == number) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Fill empty squares in sudoku after filling 3x3 boxes diagonally.
    private void fillEmptySquares() {
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
