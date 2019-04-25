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
    
    /**
    * Method to check if the given number is eligible to add into the given row and column spot.
    * 
    * @param    sudoku  any int[][] sudoku array
    * @param    row     given row in the sudoku array
    * @param    column  given column in the sudoku array
    * @param    number  given number to check if it is safe to add into the array
    * 
    * @return   true    If the number can be added to the given row and column spot
    */  
    public boolean checkIfSafe(int[][] sudoku, int row, int column, int number) {
        boolean rowSafe = checkIfRowSafe(sudoku, row, number);
        boolean columnSafe = checkIfColumnSafe(sudoku, column, number);
        boolean boxSafe = checkIfBoxSafe(sudoku, row, column, number);
        if (rowSafe && columnSafe && boxSafe) {
            return true;
        }
        return false;
    }
    
    /**
     * Check if the number can be added to the given row in the array
     * 
     * @param   sudoku  Any given 9x9 sudoku array
     * @param   row     The row between 0-8 of the array
     * @param   number  Number to check if it is in the row
     * 
     * @return  true    If the given row doesn't contain the number given in the parameter
     */
    public boolean checkIfRowSafe(int[][] sudoku, int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == number) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Check if the number can be added to the given column in the array
     * 
     * @param sudoku    Any given 9x9 sudoku array
     * @param column    The column between 0-8 of the array
     * @param number    Number to check if it is in the row
     * 
     * @return true     
     */
    public boolean checkIfColumnSafe(int[][] sudoku, int column, int number) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][column] == number) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if 3x3 box contains given number
     * @param sudoku    the sudoku matrix where to check
     * @param row   start row   
     * @param column    start column
     * @param number    the number to be checked
     * @return  false if the number is in the 3x3 box
     */
    public boolean checkIfBoxSafe(int[][] sudoku, int row, int column, int number) {
        for (int i = row - row % 3; i < row - row % 3 + 3; i++) {
            for (int j = column - column % 3; j < column - column % 3 + 3; j++) {
                if (sudoku[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
    * Check if the given array contains any zeros.
    * 
    * @param    sudoku  any int[][] sudoku array
    * 
    * @return   true    If the array doesn't contain the value zero
    */
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
    
    /**
    * Solve sudoku array with a backtrack algorithm.
    * 
    * @param    sudoku  Any int[][] sudoku array
    *  
    * @return   true    If it is solvable
    */
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
    
    /**
    * Method to check if the generated initial sudoku and playable sudoku combined is a valid sudoku
    * 
    * @param    playableSudoku  User filled sudoku board
    * @param    initialSudoku   Generated sudoku at game start
    * 
    * @return   true    If generated sudoku and the played sudoku combined is solved
    */
    public boolean checkIfFilledSudokuIsValid(int[][] playableSudoku, int[][] initialSudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (playableSudoku[i][j] == 0 && initialSudoku[i][j] != 0) {
                    playableSudoku[i][j] = initialSudoku[i][j];
                }
                int number = playableSudoku[i][j];
                playableSudoku[i][j] = 0;
                if (!checkIfSafe(playableSudoku, i, j, number)) {
                    return false;
                }
                playableSudoku[i][j] = number;
            }
        }
        return true;
    }
    
}
