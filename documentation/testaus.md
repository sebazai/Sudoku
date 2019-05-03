# Testausdokumentti

Ohjelmaa on testattu JUnitilla ja testit on pyritty kattamaan mahdollisimman paljon ja tehdyt testit on tarkoituis vastata todellisia pelitilanteita Sudoku pelissä.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testeillä on testattu pakkauksen [sudoku.domain](https://github.com/sebazai/ot-harjoitustyo/tree/master/sudoku/src/test/java/sudoku/domain) luokkia testeillä. Jokaiselle luokalle on oma [testitiedostonsa](https://github.com/sebazai/ot-harjoitustyo/tree/master/sudoku/src/test/java/sudoku/domain), jolla testataan luokan metodien toiminnallisuutta todellisissa tilanteissa, mitä käyttäjä voisi tehdä käyttöliittymän kautta. Testejä on myös tehty toiminallisuuksiin, joita tarkistetaan taustalla käyttäjien pelatessa peliä.

### DAO-luokka

[DAO-luokka](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/test/java/sudoku/dao/DatabaseSudokuDaoTest.java) on testattu tekemällä oma testitietokanta nimeltään _sudokutestdatabase_ johon lisätään yksi sudoku, millä testataan [DatabaseSudokuDao](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/dao/DatabaseSudokuDao.java) luokan toiminnallisuuksia.

### Testauskattavuus

Käyttöliittymää lukuunottamatta sovelluksen testauksen rivikattavuus on 99 % ja haarautumakattavuus on 95 %.

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/testikattavuus.png" >
