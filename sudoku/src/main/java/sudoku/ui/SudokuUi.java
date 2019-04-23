/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sudoku.dao.DatabaseSudokuDao;

/**
 *
 * @author sebserge
 */
public class SudokuUi extends Application {
    
    private DatabaseSudokuDao dao;
    @Override
    public void init() throws Exception {
        dao = new DatabaseSudokuDao("jdbc:h2:./sudoku");
    }
    
    /**
     * Loads the startscreen.fxml UI and sets the start scene.
     * @param stage Stage parameter
     * @throws java.lang.Exception  java exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loadStart = new FXMLLoader(getClass().getResource("/fxml/startscreen.fxml"));
        loadStart.setController(new StartScreenController(dao));
        Pane root = loadStart.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.show();
        
    }
    
    /**
     * Main method to launch the UI
     * @param args  default args
     */
    public static void main(String[] args) {
        launch(SudokuUi.class);
    }
}
