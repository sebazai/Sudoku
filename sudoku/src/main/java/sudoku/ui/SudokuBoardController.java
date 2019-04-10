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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    int selectedRow;
    int selectedCol;
    int difficulty;
    boolean solved;
    
    @Override
    public void initialize(URL argument0, ResourceBundle argument1) {
        gameboard = new Sudoku(difficulty);
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
        selectedRow = 0;
        selectedCol = 0;
        solved = false;
    }
    
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    public void drawSudokuSquaresOnCanvas(GraphicsContext context) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int posY = i * 50 + 3;
                int posX = j * 50 + 3;
                
                int width = 44;
                context.setFill(Color.LIGHTBLUE);
                context.fillRoundRect(posX, posY, width, width, 7, 7);
            }
        }
        for (int i = 1; i < 3; i++) {
            context.strokeLine(0, i * 150, 450, i * 150);
            context.strokeLine(i * 150, 0, i * 150, 450);
        }
    }
    
    public void drawGeneratedSudokuOnCanvas(GraphicsContext context) {
        int[][] startSudoku = gameboard.getInitialSudoku();
        drawSudokuNumbers(context, startSudoku);
    }
    
    public void solveSudokuPressed(ActionEvent event) throws IOException {
        if (solved) {
            return;
        }
        this.drawSolvedSudokuOnCanvas(canvas.getGraphicsContext2D());
        solved = true;
    }
    
    public void drawSolvedSudokuOnCanvas(GraphicsContext context) {
        int[][] solvedSudoku = gameboard.getSolvedSudoku();
        context.clearRect(0, 0, 450, 450);
        drawSudokuSquaresOnCanvas(context);
        drawSudokuNumbers(context, solvedSudoku);
    }
    
    private void drawSudokuNumbers(GraphicsContext context, int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int posX = j * 50 + 20;
                int posY = i * 50 + 30;
                context.setFill(Color.BLACK);
                context.setFont(new Font(20));
                if (sudoku[i][j] != 0) {
                    context.fillText(Integer.toString(sudoku[i][j]), posX, posY);
                }
            }
        }
    }
    
    public void drawOnCanvas(GraphicsContext context) {
        context.clearRect(0, 0, 450, 450);

        drawSudokuSquaresOnCanvas(context);
        drawGeneratedSudokuOnCanvas(context);
        
        context.strokeRoundRect(selectedCol * 50 + 2, selectedRow * 50 + 2, 44, 44, 7, 7);

        int[][] sudoku = gameboard.getPlayableSudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int posY = i * 50 + 30;
                int posX = j * 50 + 20;
                context.setFill(Color.PURPLE);
                context.setFont(new Font(20));
                if (sudoku[i][j] != 0) {
                    context.fillText(Integer.toString(sudoku[i][j]), posX, posY);
                }
            }
        }
    }
    
    public void canvasMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
                @Override
                public void handle(MouseEvent event) {
                    if (solved) {
                        return;
                    }
                    int mouseX = (int) event.getX();
                    int mouseY = (int) event.getY();

                    selectedRow = (int) (mouseY / 50);
                    selectedCol = (int) (mouseX / 50); 

                    drawOnCanvas(canvas.getGraphicsContext2D());
                }
        });
    }
    
    public void navigateWithArrowKeys(KeyCode code) {
        if (code == KeyCode.UP) {
            if (selectedRow > 0) {
                selectedRow--;
            }
        } else if (code == KeyCode.DOWN) {
            if (selectedRow < 8) {
                selectedRow++;
            }
        } else if (code == KeyCode.LEFT) {
            if (selectedCol > 0) {
                selectedCol--;
            }
        } else if (code == KeyCode.RIGHT) {
            if (selectedCol < 8) {
                selectedCol++;
            }
        }
    }
    
    public void sudokuSolvedAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("SUDOKU SOLVED - CONGRATULATIONS!");
        alert.setHeaderText("SOLVED CORRECTLY!");
        alert.setContentText("You can close the application by pressing ESC.");
        alert.showAndWait();
    }
    
    public boolean checkIfSudokuCanvasFilledAndSolved(int[][] playableSudoku, int[][] initialSudoku) {
        SudokuSolver solver = new SudokuSolver();
        if (solver.checkIfFilledSudokuIsValid(playableSudoku, initialSudoku)) {
            return true;
        }
        return false;
    }
    
    public void keyboardButtonPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            Runtime.getRuntime().exit(0);
        }
        if (solved) {
            return;
        }
        String character = event.getText();     
        if (event.getCode().isDigitKey()) {
            gameboard.modifyPlayableSudoku(Integer.parseInt(character), selectedRow, selectedCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
            if (checkIfSudokuCanvasFilledAndSolved(gameboard.getPlayableSudoku(), gameboard.getInitialSudoku())) {
                solved = true;
                sudokuSolvedAlert();
            }
        } else if (event.getCode().isNavigationKey()) {
            navigateWithArrowKeys(event.getCode());
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }
}
