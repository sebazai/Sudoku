/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import java.util.Arrays;

/**
 *
 * @author sebserge
 */
public final class Sudoku {
    int[][] solvedSudoku;
    int[][] playableSudoku;
    int[][] initialSudoku;
    int size = 9;
    int removeNumbers;
    public Sudoku(int difficulty) {
        solvedSudoku = new int[9 + 1][9 + 1];
        this.generateSudoku();
        playableSudoku = new int[9 + 1][9 + 1];
        initialSudoku = new int[9 + 1][9 + 1];
        
        initialSudoku = deepCopy(solvedSudoku);
        if (difficulty == 1) {
            removeNumbers = 35;
        } else if (difficulty == 2) {
            removeNumbers = 45;
        } else {
            removeNumbers = 55;
        }
        removeDigitsFromInitialSudoku();
    }
    
    public static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);

        }
        return result;
    }
    
    public int[][] getSolvedSudoku() {
        return solvedSudoku;
    }
    
    public int[][] getPlayableSudoku() {
        return playableSudoku;
    }
    
    public int[][] getInitialSudoku() {
        return initialSudoku;
    }
    
    public void generateSudoku() {
        fillBoxesDiagonally();
        fillEmptySquares();
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
                        if (checkIfSafe(solvedSudoku, i, j, number)) {
                            solvedSudoku[i][j] = number;
                            if (!solveSudoku(solvedSudoku)) {
                                solvedSudoku[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
    }
    
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
    
    public void removeDigitsFromInitialSudoku() {
        int numbersToRemove = removeNumbers; 
        while (numbersToRemove != 0) { 
            int getRandomCell = (int) Math.floor((Math.random() * 81 + 1)); 
  
            int i = (getRandomCell / 9); 
            int j = getRandomCell % 9; 

 
            if (initialSudoku[i][j] != 0) { 
                numbersToRemove--; 
                initialSudoku[i][j] = 0; 
            } 
        } 
    }
    
    public void modifyPlayableSudoku(int val, int row, int col) {
        if (initialSudoku[row][col] == 0) {
            if (val >= 0 && val <= 9) {
                playableSudoku[row][col] = val;
            }
        }
    }
}
