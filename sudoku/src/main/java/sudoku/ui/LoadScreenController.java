/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku.dao.SudokuDao;
import sudoku.domain.Sudoku;

/**
 *
 * @author sebserge
 */
public class LoadScreenController implements Initializable {

    FXMLLoader loader;
    @FXML Text info;
    @FXML GridPane grid;
    List<Sudoku> games;
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
    
    public void setInfoText(String text) {
        info.setText(text);
    }
    
    public void setLoadableGamesList(List<Sudoku> games) {
        this.games = games;
    }
    
    public void setLoadableGamesButtons() {
        for (int i = 0; i < games.size(); i++) {
            Button button = new Button("Game " + (1 + i));
            button.setOnAction(loadGame);
            grid.add(button, 0, i);
            grid.add(new Label("Saved: " + games.get(i).getSavedTime().toString()), 1, i);
        }
    }
    
    EventHandler<ActionEvent> loadGame = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            try {
                String game = ((Button) e.getSource()).getText();
                String[] split = game.split(" ");
                Sudoku sudoku = games.get(Integer.parseInt(split[1])-1);
                
                System.out.println("ID: " + sudoku.getId());
                dao.delete(sudoku.getId());
                
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                loader.setController(new SudokuBoardController(sudoku));
                Pane pane = null;
                try {
                    pane = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(LoadScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene loadScene = new Scene(pane);
                window.setScene(loadScene);
                window.show();
            } catch (SQLException ex) {
                Logger.getLogger(LoadScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
}
