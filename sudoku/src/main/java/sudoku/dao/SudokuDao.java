/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface used by the Controllers in sudoku.ui package, except the SudokuUi
 * @author sebserge
 */

public interface SudokuDao<O, K> {
    void create(O object) throws SQLException;
    void delete(K key) throws SQLException;
    List<O> list() throws SQLException;
}
