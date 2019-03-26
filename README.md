# Ohjelmistotekniikka - SUDOKU

Sovelluksen avulla käyttäjä pystyy pelaamaan sudoku peliä omalla tietokoneellaan. Käyttäjä pystyy tallettaa keskeneräisen pelin ja jatkamaan myöhemmin kyseistä peliä. Pelien vaikeusasteet ovat helppo, kohtalainen ja vaikea. Käyttäjä pystyy halutessaan ratkaista pelin nappia painamalla.

## Dokumentaatio

[Vaatimuusmäärittely](https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/tuntikirjanpito.md)

## Komentorivitoiminnot

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

Tiedoston [checkstyle.xml](https://github.com/sebazai/ot-harjoitustyo/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan kansiossa sudoku komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

## Tehtävät

### Viikko 1

[gitlog.txt](https://github.com/sebazai/ot-harjoitustyo/blob/master/laskarit/viikko1/gitlog.txt)

[komentorivi.txt](https://github.com/sebazai/ot-harjoitustyo/blob/master/laskarit/viikko1/komentorivi.txt)

### Viikko 2
[Koodikansio](https://github.com/sebazai/ot-harjoitustyo/blob/master/laskarit/viikko2/)

[JUnit test kuva](https://github.com/sebazai/ot-harjoitustyo/blob/master/laskarit/viikko2/laskari-viikko2-kuva.png)
