/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import sudoku.domain.Sudoku;

/**
 *
 * @author sebserge
 */
public class DatabaseSudokuDaoTest {
    
    DatabaseSudokuDao dao;
    Connection conn;
    String solvedSudoku;
    String initialSudoku;
    String playableSudoku;
    
    @Before
    public void setUp() throws SQLException {
        String dbPath = "jdbc:h2:./sudokutestdatabase";
        dao = new DatabaseSudokuDao(dbPath);
        conn = DriverManager.getConnection(dbPath, "sa", "");
        conn.prepareStatement("DROP TABLE Sudoku IF EXISTS;").executeUpdate();
        conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Sudoku ("
                        + "id INTEGER AUTO_INCREMENT PRIMARY KEY, "
                        + "initialboard VARCHAR(333), "
                        + "playableboard VARCHAR(333), "
                        + "solvedboard VARCHAR(333), "
                        + "savedstamp TIMESTAMP WITH TIME ZONE"
                        + ");").executeUpdate();

        conn.close();
        solvedSudoku = "[[8, 3, 2, 1, 5, 4, 6, 9, 7, 0], [6, 9, 4, 7, 2, 3, 5, 1, 8, 0], [7, 5, 1, 6, 8, 9, 3, 4, 2, 0], [1, 2, 5, 3, 4, 8, 7, 6, 9, 0], [4, 6, 9, 2, 7, 1, 8, 3, 5, 0], [3, 7, 8, 5, 9, 6, 4, 2, 1, 0], [2, 1, 6, 8, 3, 5, 9, 7, 4, 0], [5, 4, 3, 9, 1, 7, 2, 8, 6, 0], [9, 8, 7, 4, 6, 2, 1, 5, 3, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]";
        initialSudoku = "[[8, 0, 2, 0, 5, 0, 6, 0, 7, 0], [6, 0, 4, 0, 2, 0, 5, 0, 8, 0], [7, 0, 1, 0, 8, 0, 3, 0, 2, 0], [1, 0, 5, 0, 4, 0, 7, 0, 9, 0], [4, 0, 9, 0, 7, 0, 8, 0, 5, 0], [3, 0, 8, 0, 9, 0, 4, 0, 1, 0], [2, 0, 6, 0, 3, 0, 9, 0, 4, 0], [5, 0, 3, 0, 1, 0, 2, 0, 6, 0], [9, 0, 7, 0, 6, 0, 1, 0, 3, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]";
        playableSudoku = "[[0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]";
        Sudoku testSudoku = new Sudoku(0, initialSudoku, playableSudoku, solvedSudoku, Instant.now());
        dao.create(testSudoku);;
    }

    @Test
    public void sudokuDatabaseContainsAddedEntry() throws SQLException {
        assertEquals(1, dao.list().size());
    }
    
    @Test
    public void solvedSudokuIsEqualInDatabase() throws SQLException {
        boolean isEqual = true;
        Sudoku test = dao.list().get(0);
        int[][] solvedSudokuArray = test.stringTo2DInt(solvedSudoku, false);
        for (int i = 0; i < solvedSudokuArray.length; i++) {
            for (int j = 0; j < solvedSudokuArray[0].length; j++) {
                if (solvedSudokuArray[i][j] != test.getSolvedSudoku()[i][j]) {
                    isEqual = false;
                }
            }
        }
        assertTrue(isEqual);
    }

    @Test
    public void deletingRowRemovesEntry() throws SQLException {
        int idToRemove = dao.list().get(0).getId();
        dao.delete(idToRemove);
        assertEquals(0, dao.list().size());
    }

    @Test
    public void listIsSortedWithLatestSavedGameAsFirst() throws SQLException {
        Sudoku anotherSudoku = new Sudoku(0, initialSudoku, playableSudoku, solvedSudoku, Instant.now());
        dao.create(anotherSudoku);
        List<Sudoku> twoSudokus = dao.list();
        assertTrue(twoSudokus.get(0).getSavedTime().toEpochMilli() > twoSudokus.get(1).getSavedTime().toEpochMilli());
    }

    @After
    public void tearDown() {
        try {
            conn.close();
        } catch (SQLException e) {
        }
    }
}
