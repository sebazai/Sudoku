# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa kaksitasoista kerrosarkkitehtuuria. Koodin pakkausrakenne on seuraavanlainen:


<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/pakkauskaavio.png">


Pakkaus _sudoku.ui_ sisältää JavaFX ja Scene Builderilla toteutetun käyttöliittymän ja _sudoku.domain_ sisältää sovelluksen logiikan.

## Käyttöliittymä

Käyttöliittymä sisältää kaksi eri näkymää.

* aloitusnäkymä
* sudoku pelin näkymä

Jokainen näkymä on toteutettuna omana Scene-oliona. Apuna Scenejen tekemiseen on käytetty Scene Builder ohjelmaa, jokaisella näkymällä on oma Controller, jolla hallinoidaan näkymän eri toiminnallisuuksia.

Käyttöliittymä on pyritty eristämään sovelluslogiikasta, käyttöliittymä käyttää ainoastaan tarvittaessa sovelluslogiikan metodeja.

Kun sovellus käynnistetään, aloitusnäkymästä voi valita Sudokun vaikeusasteen, joka kutsuu Sudoku-luokkaa ja siirtyy Sudoku pelin näkymään vaikeusasteella määritetyllä Sudokulla. Tämä näkymä renderöi generoidun Sudokun ja tulostaa sen uuteen näkymään.

Sudoku näkymässä voi navigoida eri ruutuihin hiirellä tai nuolinäppäimiä käyttäen. Numeroita syötetään ruudukon tyhjiin ruutuihin.

## Sovelluslogiikka

Sovelluksen logiikka muodostuu luokista [Sudoku](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/Sudoku.java), [SudokuSolver](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/SudokuSolver.java) ja [SudokuGenerator](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/SudokuGenerator.java). Alla luokkakaavio sovelluslogiikasta ja sovelluksen käyttöliittymäluokista, jotka muodostuvat luokista [SudokuUi](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/ui/SudokuUi.java), [StartScreenController](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/ui/StartScreenController.java) ja [SudokuBoardController](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/ui/SudokuBoardController.java):

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/luokkakaavio.png">

## Päätoiminnallisuudet

Seuraavaksi kuvataan sovelluksen toimintalogiikkaa sekvenssikaavioiden avulla.

### Pelin käynnistys ja Sudokun aloittaminen

Kun käyttäjä käynnistää ohjelman hän voi valita vaikeusasteen sudokulle, seuraavaksi kuvataan tämän uuden Sudokun luonnin toimintalogiikka ilman graafisen käyttöliityymän komponentteja.

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/generatingEasySudokuVersion2.png">

Sekvenssikaaviossa on pyritty aukaisemaan mahdollisimman tarkasti kuinka sudoku luodaan, valitettavasti koodissa on suuri määrä rekursiokutsuja, joten sekvenssikaavio on näiden osalta hieman puuttellinen.


## Tietojen pysyväistallennus

Lisätään myöhemmin...
