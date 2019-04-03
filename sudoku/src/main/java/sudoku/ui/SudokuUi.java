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

/**
 *
 * @author sebserge
 */
public class SudokuUi extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sudokuboard.fxml"));
        loader.setController(new FXMLController());
        Pane root = loader.load();
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Sudoku");
        scene.getRoot().requestFocus();
    }
    
    public static void main(String[] args) {
        launch(SudokuUi.class);
    }
}
