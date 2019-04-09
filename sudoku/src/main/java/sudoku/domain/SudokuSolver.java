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
public class SudokuSolver {
        
    public boolean checkIfSafe(int[][] sudoku, int row, int column, int number) {
        //Check row and column if it contains same number
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == number) {
                return false;
            }
            if (sudoku[i][column] == number) {
                return false;
            }
        }
        //Check 3x3 box if it contains same number
        for (int i = row - row % 3; i < row - row % 3 + 3; i++) {
            for (int j = column - column % 3; j < column - column % 3 + 3; j++) {
                if (sudoku[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //If sudoku matrix contains zero, there's no solution
    public boolean sudokuContainsZeros(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // Go through the sudoku matrix with backtracking and solve it.
    public boolean solveSudoku(int[][] sudoku) {
        boolean noEmptySquares = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    noEmptySquares = false;
                    for (int number = 1; number <= 9; number++) {
                        if (checkIfSafe(sudoku, i, j, number)) {
                            sudoku[i][j] = number;
                            solveSudoku(sudoku);
                            if (sudokuContainsZeros(sudoku)) {
                                sudoku[i][j] = 0;
                            }
                        }
                    }
                    break;
                }
            }
            if (!noEmptySquares) {
                break;
            }
        }
        return !sudokuContainsZeros(sudoku);
    }
}
