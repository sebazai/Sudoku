# Ohjelmistotekniikka - SUDOKU

Sovelluksen avulla käyttäjä pystyy pelaamaan sudoku peliä omalla tietokoneellaan. Käyttäjä pystyy tallettaa keskeneräisen pelin ja jatkamaan myöhemmin kyseistä peliä. Pelien vaikeusasteet ovat helppo, kohtalainen ja vaikea. Käyttäjä pystyy halutessaan ratkaista pelin nappia painamalla.

## Dokumentaatio

[Vaatimuusmäärittely](https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/tuntikirjanpito.md)

## Komentorivitoiminnot

### Ohjelman ajaminen
Lataa ZIP tiedosto ja pura se koneellesi. Voit aukaista projektin Netbeans:ssa tai ajaa seuraavan komennon kansiossa "sudoku":

```
mvn compile exec:java -Dexec.mainClass=sudoku.ui.SudokuUi
```

### Testaus
Testit suoritetaan kansiossa sudoku, komennolla

```
mvn test
```

Kattavuusraportti testeistä luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraportin voit aukaista selaimella, tiedosto sijaitsee _target/site/jacoco/index.html_

#### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/sebazai/ot-harjoitustyo/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan kansiossa sudoku komennolla, komennon jälkeen tiedosto löytyy kansiosta _/sudoku/target/site/_ nimellä _checkstyle.html_

```
mvn jxr:jxr checkstyle:checkstyle
```

