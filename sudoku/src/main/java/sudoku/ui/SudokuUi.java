/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import sudoku.domain.Sudoku;

/**
 *
 * @author sebserge
 */
public class SudokuUi {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(1);
        sudoku.generateSudoku();
        sudoku.printSudoku();
    }
}
