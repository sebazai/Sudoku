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
        SudokuBoardController controller = loader.getController();
        controller.setDifficulty(55);
        Pane hard = loader.load();
        
        Scene hardSudokuScene = new Scene(hard);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(hardSudokuScene);
        window.show();   
    }
    
    public void startMediumGame(ActionEvent event) throws IOException {
        SudokuBoardController controller = loader.getController();
        controller.setDifficulty(45);
        Pane hard = loader.load();
        
        Scene hardSudokuScene = new Scene(hard);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(hardSudokuScene);
        window.show(); 
    }
    
    public void startEasyGame(ActionEvent event) throws IOException {
        SudokuBoardController controller = loader.getController();
        controller.setDifficulty(1);
        Pane hard = loader.load();
        
        Scene hardSudokuScene = new Scene(hard);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(hardSudokuScene);
        window.show(); 
    }
    
    public void loadGame() {
        
    }
    
}
