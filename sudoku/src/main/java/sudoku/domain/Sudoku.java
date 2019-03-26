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
public class Sudoku {
    int[][] sudoku;
    int size = 9;
    int removeNumbers;
    public Sudoku(int difficulty) {
        sudoku = new int[9][9];
        if(difficulty == 1) {
            removeNumbers = 25;
        } else if (difficulty == 2) {
            removeNumbers = 35;
        } else {
            removeNumbers = 45;
        }
    }
    
    public void generateSudoku() {
        fillBoxesDiagonally();
        fillEmptySquares();
    }
    
    private void fillBoxesDiagonally() {
        for(int i = 0; i < 9; i=i+3) {
            for(int j = 0; j < 9; j=j+3) {
                fillBox(i, j);
            }
        }
    }
    
    private void fillBox(int row, int column) {
        int number;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                do {
                    number = (int) Math.floor(Math.random()*10);
                } 
                while (!numberNotInBox(i, j, number));
                sudoku[row+i][column+j] = number;
            }
        }
    }
    
    private boolean numberNotInBox(int row, int column, int number) {
        for (int i = row; i < row+3; i++) {
            for (int j = column; j < column+3; j++) {
                if(sudoku[i][j] == number) return false;
            }
        }
        return true;
    }
    
    private void fillEmptySquares() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(sudoku[i][j] == 0) {
                    for(int number = 1; number <= 9; number++) {
                        if(checkIfSafe(i, j, number)) {
                            sudoku[i][j] = number;
                            if(!solveSudoku()) {
                                sudoku[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean checkIfSafe(int row, int column, int number) {
        for(int i = 0; i < 9; i++) {
            if(sudoku[row][i] == number) return false;
            if(sudoku[i][column] == number) return false;
        }
        for(int i = row - row % 3; i < row - row % 3 + 3; i++) {
            for (int j = column - column % 3; j < column - column % 3 + 3; j++) {
                if (sudoku[i][j] == number) return false;
            }
        }
        return true;
    }
    
    public boolean noSolution() {
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudoku[i][j] == 0) return true;
            }
        }
        return false;
    }
    
    public boolean solveSudoku() {
        boolean noEmptySquares = true;
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudoku[i][j] == 0) {
                    noEmptySquares = false;
                    for(int number = 1; number <= 9; number++) {
                        if(checkIfSafe(i, j ,number)) {
                            sudoku[i][j] = number;
                            solveSudoku();
                            if(noSolution()) {
                                sudoku[i][j] = 0;
                            }
                        }
                    }
                    break;
                }
            }
            if(!noEmptySquares) break;
        }
        if(noSolution()) {
            return false;
        } else {
            return true;
        }
    }
    
    public void printSudoku() {
        for(int i = 0; i < 9; i++) {
//            
            for(int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
                if(j == 2 || j == 5) {
                    System.out.print("|");
                }
            }   
            if(i == 2 || i ==5) {
                System.out.println("");
                System.out.println("-------------------");
            } else {
                System.out.println("");
            }
            
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(1);
        sudoku.generateSudoku();
        sudoku.printSudoku();
    }
    
}
