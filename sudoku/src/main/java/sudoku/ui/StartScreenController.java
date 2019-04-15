package sudoku.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class StartScreenController implements Initializable {
    
    @FXML Button hard;
    @FXML Button medium;
    @FXML Button easy;
    @FXML Button load;
    FXMLLoader loader;
    
    @Override
    public void initialize(URL argument0, ResourceBundle argument1) {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/sudokuboard.fxml"));
        loader.setController(new SudokuBoardController());
    }    
    
    public void startHardGame(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        startGame(window, 55);
    }
    
    public void startMediumGame(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        startGame(window, 45);
    }
    
    public void startEasyGame(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        startGame(window, 35);
    }
    
        
    public void startGame(Stage window, int difficulty) throws IOException {
        SudokuBoardController controller = loader.getController();
        controller.setDifficulty(difficulty);
        Pane pane = loader.load();
        
        Scene sudokuScene = new Scene(pane);

        window.setScene(sudokuScene);
        window.show();  
    }
    
    public void loadGame() {
        
    }
    
}
