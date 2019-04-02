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

