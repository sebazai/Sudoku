package sudoku.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sudoku.domain.Sudoku;
import sudoku.domain.SudokuSolver;

public class SudokuBoardController implements Initializable {
    
    @FXML Button solve;
    @FXML Button save;
    @FXML Canvas canvas;
    
    Sudoku gameboard;
    int selected_row;
    int selected_col;
    int difficulty;
    boolean solved;
    
    @Override
    public void initialize(URL argument0, ResourceBundle argument1) {
        gameboard = new Sudoku(difficulty);
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
        selected_row = 0;
        selected_col = 0;
        solved = false;
    }
    
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    public void drawSudokuSquaresOnCanvas(GraphicsContext context) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int pos_y = i * 50 + 3;
                int pos_x = j * 50 + 3;
                
                int width = 44;
                context.setFill(Color.LIGHTBLUE);
                context.fillRoundRect(pos_x, pos_y, width, width, 7, 7);
            }
        }
        for(int i = 1; i < 3; i++) {
            context.strokeLine(0, i*150, 450, i*150);
            context.strokeLine(i*150, 0, i*150, 450);
        }
    }
    
    public void drawGeneratedSudokuOnCanvas(GraphicsContext context) {
        int[][] startSudoku = gameboard.getInitialSudoku();
        drawSudokuNumbers(context, startSudoku);
    }
    
    public void solveSudokuPressed(ActionEvent event) throws IOException {
        if (solved) return;
        this.drawSolvedSudokuOnCanvas(canvas.getGraphicsContext2D());
        solved = true;
    }
    
    public void drawSolvedSudokuOnCanvas(GraphicsContext context) {
        int[][] solvedSudoku = gameboard.getSolvedSudoku();
        context.clearRect(0,0,450,450);
        drawSudokuSquaresOnCanvas(context);
        drawSudokuNumbers(context, solvedSudoku);
    }
    
    private void drawSudokuNumbers(GraphicsContext context, int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int pos_x = j * 50 + 20;
                int pos_y = i * 50 + 30;
                context.setFill(Color.BLACK);
                context.setFont(new Font(20));
                if(sudoku[i][j] != 0) {
                    context.fillText(Integer.toString(sudoku[i][j]), pos_x, pos_y);
                }
            }
        }
    }
    
    public void drawOnCanvas(GraphicsContext context) {
        context.clearRect(0,0,450,450);

        drawSudokuSquaresOnCanvas(context);
        drawGeneratedSudokuOnCanvas(context);
        
        context.strokeRoundRect(selected_col * 50 + 2, selected_row * 50 + 2, 44, 44, 7, 7);

        int[][] sudoku = gameboard.getPlayableSudoku();
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int pos_y = i * 50 + 30;
                int pos_x = j * 50 + 20;
                context.setFill(Color.PURPLE);
                context.setFont(new Font(20));
                if(sudoku[i][j] != 0) {
                    context.fillText(Integer.toString(sudoku[i][j]), pos_x, pos_y);
                }
            }
        }
    }
    
    public void canvasMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
                @Override
                public void handle(MouseEvent event) {
                    if (solved) return;
                    int mouse_x = (int) event.getX();
                    int mouse_y = (int) event.getY();

                    selected_row = (int) (mouse_y / 50);
                    selected_col = (int) (mouse_x / 50); 

                    drawOnCanvas(canvas.getGraphicsContext2D());
                }
        });
    }
    
    public boolean checkIfSudokuCanvasFilledAndSolved(int[][] playableSudoku, int[][] initialSudoku) {
        SudokuSolver solver = new SudokuSolver();
        if(solver.checkIfFilledSudokuIsValid(playableSudoku, initialSudoku)) {
            return true;
        }
        return false;
    }
    
    public void buttonPressed(KeyEvent event) {
       if(solved) {
           return;
       }
       String character = event.getText();     
       if (event.getCode().isDigitKey()) {
           gameboard.modifyPlayableSudoku(Integer.parseInt(character), selected_row, selected_col);
           drawOnCanvas(canvas.getGraphicsContext2D());
           if(checkIfSudokuCanvasFilledAndSolved(gameboard.getPlayableSudoku(), gameboard.getInitialSudoku())) {
               solved = true;
           }
       } else if (event.getCode().isNavigationKey()) {
           KeyCode code = event.getCode();
           if (code == KeyCode.UP) {
               if (selected_row > 0) {
                   selected_row--;
               }
           } else if (code == KeyCode.DOWN) {
               if (selected_row < 8) {
                   selected_row++;
               }
           } else if (code == KeyCode.LEFT) {
               if (selected_col > 0) {
                   selected_col--;
               }
           } else if (code == KeyCode.RIGHT) {
                if (selected_col < 8) {
                   selected_col++;
               }
           }
           drawOnCanvas(canvas.getGraphicsContext2D());
       } else if (event.getCode() == KeyCode.ESCAPE) {
           Runtime.getRuntime().exit(0);
       }
    }
}
