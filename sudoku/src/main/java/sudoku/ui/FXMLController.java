package sudoku.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sudoku.domain.Sudoku;

public class FXMLController implements Initializable {
    
    @FXML Button solve;
    @FXML Button save;
    @FXML Canvas canvas;
    
    Sudoku gameboard;
    int selected_row;
    int selected_col;
    
    @Override
    public void initialize(URL argument0, ResourceBundle argument1) {
        gameboard = new Sudoku(1);
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
        selected_row = 0;
        selected_col = 0;
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int pos_x = j * 50 + 20;
                int pos_y = i * 50 + 30;
                context.setFill(Color.BLACK);
                context.setFont(new Font(20));
                if(startSudoku[i][j] != 0) {
                    context.fillText(Integer.toString(startSudoku[i][j]), pos_x, pos_y);
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
				int mouse_x = (int) event.getX();
				int mouse_y = (int) event.getY();

				selected_row = (int) (mouse_y / 50);
				selected_col = (int) (mouse_x / 50); 

				drawOnCanvas(canvas.getGraphicsContext2D());
			}
		});
	}
    public void buttonPressed(KeyEvent event) {
       String character = event.getText();     
       if (event.getCode().isDigitKey()) {
           gameboard.modifyPlayableSudoku(Integer.parseInt(character), selected_row, selected_col);
           drawOnCanvas(canvas.getGraphicsContext2D());
       } 
        
    }
}
