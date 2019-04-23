/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import java.time.Instant;
import java.util.Arrays;

/**
 *
 * @author sebserge
 */
public final class Sudoku implements Comparable<Sudoku> {
        
    int id;
    Instant time;
    int[][] solvedSudoku;
    int[][] playableSudoku;
    int[][] initialSudoku;
    int removeNumbers;
    SudokuGenerator generator;
    
    @Override
    public int compareTo(Sudoku o) {
        return o.time.compareTo(this.time);
    }
    
    public Sudoku(int difficulty) {
        generator = new SudokuGenerator();
        solvedSudoku = generator.generateSudoku();
        playableSudoku = new int[9 + 1][9 + 1];
        initialSudoku = new int[9 + 1][9 + 1];
        initialSudoku = deepCopy(solvedSudoku);
        removeDigitsFromInitialSudoku(difficulty);
    }
    
    public Sudoku(int id, String initial, String playable, String solved, Instant time) {
        this.id = id;
        generator = new SudokuGenerator();
        this.setInitialSudoku(initial);
        this.setPlayableSudoku(playable);
        this.setSolvedSudoku(solved);
        this.time = time;
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
    
    public Instant getSavedTime() {
        return this.time;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setSolvedSudoku(String solved) {
//        String test = "[[8, 3, 2, 1, 5, 4, 6, 9, 7, 0], [6, 9, 4, 7, 2, 3, 5, 1, 8, 0], [7, 5, 1, 6, 8, 9, 3, 4, 2, 0], [1, 2, 5, 3, 4, 8, 7, 6, 9, 0], [4, 6, 9, 2, 7, 1, 8, 3, 5, 0], [3, 7, 8, 5, 9, 6, 4, 2, 1, 0], [2, 1, 6, 8, 3, 5, 9, 7, 4, 0], [5, 4, 3, 9, 1, 7, 2, 8, 6, 0], [9, 8, 7, 4, 6, 2, 1, 5, 3, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]";
        this.solvedSudoku = stringTo2DInt(solved);
//        System.out.println(Arrays.deepToString(testSudoku));
    }
    
    public void setInitialSudoku(String initial) {
        this.initialSudoku = stringTo2DInt(initial);
    }
    
    public void setPlayableSudoku(String playable) {
        this.playableSudoku = stringTo2DInt(playable);
    }
    
    
    /**
     * Convert a Arrays.deepToString() sudoku string into a 2D int array. Used to set the boards from the database
     * @param sudoku deepToString string of an int[10][10] array
     * @return int[][] array converted from the string
     */
    public int[][] stringTo2DInt(String sudoku) {
        String[] strings = sudoku.replace("[", "").split("], ");
        int[][] sudokuReturn = new int[10][10];
        for (int i = 0; i < strings.length; i++) {
            String[] row = strings[i].replace("]", "").split(", ");
            for (int j = 0; j < row.length; j++) {
                sudokuReturn[i][j] = Integer.parseInt(row[j]);
            }
        }
        return sudokuReturn;
    }
        
    /**
     * Copy an 2D array to a new 2D array
     * @param original  The 2D array to be copied
     * @return  a copy of the original array
     */
    public int[][] deepCopy(int[][] original) {
        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);

        }
        return result;
    }
    
    
    /**
     * Removes x number of digits from the solved sudoku array and adds them to initialSudoku array
     * @param difficulty    number of digits to be removed
     */
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
