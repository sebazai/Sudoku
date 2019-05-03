# Testausdokumentti

Ohjelmaa on testattu JUnitilla ja testit on pyritty kattamaan mahdollisimman paljon ja tehdyt testit on tarkoituis vastata todellisia pelitilanteita Sudoku pelissä.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testeillä on testattu pakkauksen [sudoku.domain](https://github.com/sebazai/ot-harjoitustyo/tree/master/sudoku/src/test/java/sudoku/domain) luokkia testeillä. Jokaiselle luokalle on oma [testitiedostonsa](https://github.com/sebazai/ot-harjoitustyo/tree/master/sudoku/src/test/java/sudoku/domain), jolla testataan luokan metodien toiminnallisuutta todellisissa tilanteissa, mitä käyttäjä voisi tehdä käyttöliittymän kautta. Testejä on myös tehty toiminallisuuksiin, joita tarkistetaan taustalla käyttäjien pelatessa peliä.

### DAO-luokka

[DAO-luokka](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/test/java/sudoku/dao/DatabaseSudokuDaoTest.java) on testattu tekemällä oma testitietokanta nimeltään _sudokutestdatabase_ johon lisätään yksi sudoku, millä testataan [DatabaseSudokuDao](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/dao/DatabaseSudokuDao.java) luokan toiminnallisuuksia.

### Testauskattavuus

Käyttöliittymää lukuunottamatta sovelluksen testauksen rivikattavuus on 99 % ja haarautumakattavuus on 95 %.

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/testikattavuus.png">

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Toiminallisuudet

Melkein kaikki [määrittelydokumentin](https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminallisuudet on käyty läpi.

Suuri osa apumetodeista mitä sovellus käyttää on testattu erikseen. 

#### DAO

Pelin lataamiset on huonosti testattu, sillä DAO luokka luo vain yhden sudokun ja tällä tavoin kokeilee Sudoku luokan settereitä ja tarkistaa ainoastaan, että solvedSudoku matriisi on kunnossa. DAO luokan create metodia kokeillaan testiluokan setUp vaiheessa, eikä erikseen. Muutenkin DAO luokan testaukset ovat suppeat.

