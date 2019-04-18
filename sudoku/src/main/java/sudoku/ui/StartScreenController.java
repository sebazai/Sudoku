package sudoku.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import sudoku.dao.SudokuDao;
import sudoku.domain.Sudoku;


public class StartScreenController implements Initializable {
    
    @FXML Button hard;
    @FXML Button medium;
    @FXML Button easy;
    @FXML Button load;
    FXMLLoader loader;
    SudokuDao dao;
    
    @Override
    public void initialize(URL argument0, ResourceBundle argument1) {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/sudokuboard.fxml"));
        try { 
            dao = new SudokuDao("jdbc:h2:./sudoku");
        } catch (SQLException ex) {
            Logger.getLogger(LoadScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /**
     *  Event handler for UI Hard button.
     * @param event
     * @throws IOException 
     */
    public void startHardGame(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        startGame(window, 55);
    }
    
    /**
     * Event handler for UI Medium button
     * @param event
     * @throws IOException 
     */
    public void startMediumGame(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        startGame(window, 45);
    }
    
    /**
     * Event handler for UI Easy button
     * @param event
     * @throws IOException 
     */
    
    public void startEasyGame(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        startGame(window, 35);
    }
    
    /**
     * Start the game with the chosen difficulty
     * @param window    The current stage where to change scene
     * @param difficulty    the number of digits to remove from the 9x9 sudoku
     * @throws IOException 
     */
    public void startGame(Stage window, int difficulty) throws IOException {
        loader.setController(new SudokuBoardController(new Sudoku(difficulty)));
        SudokuBoardController controller = loader.getController();
        controller.setDifficulty(difficulty);
        Pane pane = loader.load();
        
        Scene sudokuScene = new Scene(pane);

        window.setScene(sudokuScene);
        window.show();  
    }
    
    public void loadGame(ActionEvent event) throws IOException, SQLException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader.setLocation(getClass().getResource("/fxml/loadscreen.fxml"));
        loader.setController(new LoadScreenController());
        LoadScreenController controller = loader.getController();
        List<Sudoku> list = dao.list();
        
        Pane pane = loader.load();
        
        Scene loadScene = new Scene(pane);
        
        window.setScene(loadScene);
        window.show();
        if (list.isEmpty()) {
            controller.setInfoText("No saved games");
        } else {
            Stage window2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/fxml/sudokuboard.fxml"));
            load.setController(new SudokuBoardController(list.get(0)));
            Pane pane2 = load.load();
            Scene loadScene2 = new Scene(pane2);
            window.setScene(loadScene2);
            window.show();
        }
    }
    
}
