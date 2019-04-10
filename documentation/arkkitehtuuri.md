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

Sovelluksen logiikka muodostuu luokista [Sudoku](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/Sudoku.java), [SudokuSolver](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/SudokuSolver.java) ja [SudokuGenerator](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/SudokuGenerator.java). Alla luokkakaavio sovelluslogiikasta:

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/luokkakaavio.png">

## Tietojen pysyväistallennus

Lisätään myöhemmin...
