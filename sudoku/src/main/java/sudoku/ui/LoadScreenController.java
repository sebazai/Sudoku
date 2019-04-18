/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku.dao.SudokuDao;

/**
 *
 * @author sebserge
 */
public class LoadScreenController implements Initializable {

    FXMLLoader loader;
    @FXML Text info;

    @Override
    public void initialize(URL argument0, ResourceBundle argument1) {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/sudokuboard.fxml"));
//        loader.setController(new SudokuBoardController());
        
    }   
    public void setInfoText(String text) {
        info.setText(text);
    }
}
