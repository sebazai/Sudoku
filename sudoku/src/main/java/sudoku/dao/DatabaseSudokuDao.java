/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import sudoku.domain.Sudoku;
/**
 *
 * @author sebserge
 */
public class DatabaseSudokuDao implements SudokuDao<Sudoku, Integer> {
    private final String databasePath;

    public DatabaseSudokuDao(String databasePath) throws SQLException {
        this.databasePath = databasePath;

        Connection conn = DriverManager.getConnection(databasePath, "sa", "");
        conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Sudoku ("
                        + "id INTEGER AUTO_INCREMENT PRIMARY KEY, "
                        + "initialboard VARCHAR(333), "
                        + "playableboard VARCHAR(333), "
                        + "solvedboard VARCHAR(333), "
                        + "savedstamp TIMESTAMP WITH TIME ZONE"
                        + ");").executeUpdate();

        conn.close();
    }
    
    /**
     * Save a Sudoku object data into database
     * @param sudoku    Sudoku() object
     * @throws SQLException 
     */
    @Override
    public void create(Sudoku sudoku) throws SQLException {
        Connection conn = DriverManager.getConnection(databasePath, "sa", "");

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Sudoku "
                        + " (initialboard, playableboard, solvedboard, savedstamp) "
                        + " VALUES (?, ?, ?, ?)");
        stmt.setString(1, Arrays.deepToString(sudoku.getInitialSudoku()));
        stmt.setString(2, Arrays.deepToString(sudoku.getPlayableSudoku()));
        stmt.setString(3, Arrays.deepToString(sudoku.getSolvedSudoku()));
        stmt.setTimestamp(4, Timestamp.from(ZonedDateTime.now(ZoneOffset.systemDefault()).toInstant()));
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    /**
     * Delete a database entry
     * @param key   database id
     * @throws SQLException 
     */
    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection(databasePath, "sa", "");

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE Sudoku WHERE id = ?");
        stmt.setInt(1, key);

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    /**
     * Returns the 10 first entries from the database
     * @return  List of the 10 first Sudoku objects
     * @throws SQLException 
     */
    @Override
    public List<Sudoku> list() throws SQLException {
        List<Sudoku> loadedSudokus = new ArrayList<>();
        Connection conn = DriverManager.getConnection(databasePath, "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Sudoku LIMIT 10");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Sudoku sudokuLoad = new Sudoku(
                    rs.getInt("id"),
                    rs.getString("initialboard"),
                    rs.getString("playableboard"),
                    rs.getString("solvedboard"),
                    rs.getTimestamp("savedstamp").toInstant());

            loadedSudokus.add(sudokuLoad);
        }

        stmt.close();
        conn.close();
        Collections.sort(loadedSudokus);
        return loadedSudokus;
    }
}
