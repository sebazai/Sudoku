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
    SudokuGenerator generator;
    public Sudoku(int difficulty) {
        generator = new SudokuGenerator();
        solvedSudoku = generator.generateSudoku();
        playableSudoku = new int[9 + 1][9 + 1];
        initialSudoku = new int[9 + 1][9 + 1];
        initialSudoku = deepCopy(solvedSudoku);
        removeDigitsFromInitialSudoku(difficulty);
    }
    
    public Sudoku() {
        
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
        
    public int[][] deepCopy(int[][] original) {
        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);

        }
        return result;
    }
    
    
    public void removeDigitsFromInitialSudoku(int difficulty) {
        removeNumbers = difficulty;
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
