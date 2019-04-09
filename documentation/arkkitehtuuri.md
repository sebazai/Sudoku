# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa kaksitasoista kerrosarkkitehtuuria. Koodin pakkausrakenne on seuraavanlainen:


<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/pakkauskaavio.png">


Pakkaus _sudoku.ui_ sisältää JavaFX ja Scene Builderilla toteutetun käyttöliittymän ja _sudoku.domain_ sisältää sovelluksen logiikan.

## Käyttöliittymä

Täydennetään myöhemmin kun saatu kaikki näkymät tehtyä.

## Sovelluslogiikka

Sovelluksen logiikka muodostuu luokista [Sudoku](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/Sudoku.java), [SudokuSolver](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/SudokuSolver.java) ja [SudokuGenerator](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/SudokuGenerator.java). Alla luokkakaavio sovelluslogiikasta:

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/luokkakaavio.png">
