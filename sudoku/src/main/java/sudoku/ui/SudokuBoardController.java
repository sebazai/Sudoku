package sudoku.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sudoku.dao.SudokuDao;
import sudoku.domain.Sudoku;
import sudoku.domain.SudokuSolver;

public class SudokuBoardController implements Initializable {
    
    @FXML Button solve;
    @FXML Button save;
    @FXML Button quit;
    @FXML Button hint;
    @FXML Button emptyboard;
    @FXML Canvas canvas;
    
    Sudoku gameboard;
    int selectedRow;
    int selectedCol;
    int difficulty;
    boolean solved;
    SudokuDao dao;
    FXMLLoader loader;

    SudokuBoardController(Sudoku sudoku, SudokuDao dao) {
        this.gameboard = sudoku;
        this.dao = dao;
    }
    
    @Override
    public void initialize(URL argument0, ResourceBundle argument1) {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/startscreen.fxml"));
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
        selectedRow = 0;
        selectedCol = 0;
        solved = false;
    }
    
    /**
     * Hint button gives a random number to help the player.
     * @param event Hint button pressed event
     */
    public void giveAHint(ActionEvent event) {
        if (solved) {
            return;
        }
        if (!gameboard.giveAHintToThePlayer()) {
            if (gameboard.getGenerator().getSolver().checkIfFilledSudokuIsValid(gameboard.getPlayableSudoku(), gameboard.getInitialSudoku())) {
                this.solved = true;
                this.drawOnCanvas(canvas.getGraphicsContext2D());
                this.sudokuSolvedAlert();
            }
        }
        this.drawOnCanvas(canvas.getGraphicsContext2D());
    }
    
    /**
     * Return to menu button, changes the scene to main menu.
     * @param event Return to main menu button pressed
     * @throws IOException  IOException
     */
    public void quitGameWithoutSaving(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader.setController(new StartScreenController(dao));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.show();
    }
    
    /**
     * Empties all the numbers that the user has given on the board.
     * @param event Empty sudoku matrix button pressed
     */
    public void emptyPlayableSudokuMatrix(ActionEvent event) {
        if (solved) {
            return;
        }
        gameboard.emptyPlayableSudokuMatrix();
        this.drawOnCanvas(canvas.getGraphicsContext2D());
    }
    
    /**
     * Save the current sudoku game
     * @param event Save and quit button pressed event
     * @throws IOException  IOException
     * @throws SQLException SQL error
     */
    public void saveGame(ActionEvent event) throws IOException, SQLException {
        if (solved) {
            return;
        }
        dao.create(gameboard);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sudoku saved successfully");
        alert.setHeaderText("Sudoku saved successfully");
        alert.setContentText("Press OK or close this windows to quit game.");
        alert.showAndWait();
        Runtime.getRuntime().exit(0);
    }
    
    /**
     * Number of digits to remove from the gameboard
     * @param difficulty  number of digits to remove
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    
    /**
     * Draw the 9x9 board on the 450x450 px canvas
     * @param context   Canvas GraphicsContext2D
     */
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
    
    
    /**
     * Draw the initialSudoku array numbers into the canvas
     * @param context Canvas GraphicsContext2D
     */
    public void drawGeneratedSudokuOnCanvas(GraphicsContext context) {
        int[][] startSudoku = gameboard.getInitialSudoku();
        drawSudokuNumbers(context, startSudoku);
    }
    
    /**
     * Event handler for solve sudoku button. Solves the sudoku.
     * @param event Solve sudoku button
     * @throws IOException IO exception
     */
    public void solveSudokuPressed(ActionEvent event) throws IOException {
        if (solved) {
            return;
        }
        this.drawSolvedSudokuOnCanvas(canvas.getGraphicsContext2D());
        solved = true;
    }
    
    /**
     * Draws the solved sudoku on the 450x450 px canvaas board
     * @param context Canvas GraphicsContext2D
     */
    public void drawSolvedSudokuOnCanvas(GraphicsContext context) {
        int[][] solvedSudoku = gameboard.getSolvedSudoku();
        context.clearRect(0, 0, 450, 450);
        drawSudokuSquaresOnCanvas(context);
        drawSudokuNumbers(context, solvedSudoku);
    }
    
    /**
     * Method to draw the numbers 1-9 from the array in the parameter on the canvas
     * @param context   Canvas GraphicsContext2D
     * @param sudoku    The 2D array of numbers to draw
     */
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
    
    /**
     * The main method to build/update the gameboard by adding the 9x9 squares and drawing the initialSudoku array and the user added numbers playableSudoku array
     * @param context   Canvas GraphicsContext2D
     */
    
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
    
    /**
     * Event handler to check if playing by mouse
     */
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
    
    /**
     * Method to navigate using arrow keys.
     * @param code  KeyCode UP, DOWN, LEFT, RIGHT
     */
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
    
    /**
     * Alert user if he won the game
     */
    public void sudokuSolvedAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("SUDOKU SOLVED - CONGRATULATIONS!");
        alert.setHeaderText("SOLVED CORRECTLY!");
        alert.setContentText("You can close the application by pressing ESC.");
        alert.showAndWait();
    }
    
    /**
     * Checks if the initialSudoku + playableSudoku together is a valid solved sudoku
     * @param playableSudoku    The user filled numbers on the board
     * @param initialSudoku     The generated sudoku
     * @return  true    if playableSudoku and initialSudoku arrays combined is a valid sudoku
     */
    public boolean checkIfSudokuCanvasFilledAndSolved(int[][] playableSudoku, int[][] initialSudoku) {
        SudokuSolver solver = new SudokuSolver();
        if (solver.checkIfFilledSudokuIsValid(playableSudoku, initialSudoku)) {
            return true;
        }
        return false;
    }
    
    /**
     * Event handler to see if any keyboard button is pressed. Used keys are ESCAPE to quit game, digit keys to add number into the square and arrow keys to navigate the squares.
     * @param event    KeyEvent from keyboard
     */
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
