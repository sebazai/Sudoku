# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuuria. Koodin pakkausrakenne on seuraavanlainen:


<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/pakkauskaavio.png">


Pakkaus _sudoku.ui_ sisältää JavaFX ja Scene Builderilla toteutetun käyttöliittymän ja _sudoku.domain_ sisältää sovelluksen logiikan ja _sudoku.dao_ sisältää tietokantaa vaadittavia toiminnallisuuksia.

## Käyttöliittymä

Käyttöliittymä sisältää kolme eri näkymää.

* aloitusnäkymä
* sudoku pelin näkymä
* pelin latausnäkymä

Jokainen näkymä on toteutettuna omana [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-oliona. Apuna Scenejen tekemiseen on käytetty [Scene Builder](https://gluonhq.com/products/scene-builder/) ohjelmaa, jokaisella näkymällä on oma [Controller](https://github.com/sebazai/ot-harjoitustyo/tree/master/sudoku/src/main/java/sudoku/ui), jolla hallinoidaan näkymän eri toiminnallisuuksia. Näkymät jotka on luotu Scene Builderilla sijaitsevat [/sudoku/src/main/resources/fxml/](https://github.com/sebazai/ot-harjoitustyo/tree/master/sudoku/src/main/resources/fxml) kansiossa.

Käyttöliittymä on pyritty eristämään sovelluslogiikasta, käyttöliittymä käyttää ainoastaan tarvittaessa sovelluslogiikan metodeja ja DAO luokkia.

Kun sovellus käynnistetään, aloitusnäkymästä voi valita Sudokun vaikeusasteen, joka kutsuu Sudoku-luokkaa ja siirtyy Sudoku pelin näkymään vaikeusasteella määritetyllä Sudokulla. Tämä näkymä renderöi generoidun Sudokun ja tulostaa sen uuteen näkymään. Menu näkymässä voit myös valita ladata vanhan keskeneräisen pelin, jonka olet tallettanut. Latausnäkymä näyttää vain 10 vanhinta tallennettua peliä.

Sudoku näkymässä voi navigoida eri ruutuihin hiirellä tai nuolinäppäimiä käyttäen. Numeroita välillä 0-9 syötetään ruudukon tyhjiin ruutuihin.

## Sovelluslogiikka

Luokkakaavio ja/sekä pakkauskaavio on kuvattuna alla olevissa kuvissa.

### Luokkakaavio

Sovelluksen logiikka muodostuu luokista [Sudoku](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/Sudoku.java), [SudokuSolver](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/SudokuSolver.java) ja [SudokuGenerator](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/domain/SudokuGenerator.java). 

Alla luokkakaavio sovelluslogiikasta, DAO-luokista ja sovelluksen käyttöliittymäluokista, jotka muodostuvat luokista [SudokuUi](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/ui/SudokuUi.java), [StartScreenController](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/ui/StartScreenController.java), [SudokuBoardController](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/ui/SudokuBoardController.java), [LoadScreenContoller](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/ui/LoadScreenController.java). 

DAO-luokat ovat [SudokuDao](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/dao/SudokuDao.java) ja [DatabaseSudokuDao](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/dao/DatabaseSudokuDao.java).

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/luokkakaavio.png">

### Rakenne

Sovelluksen pakkaus- sekä luokkakaavio kuvattuna alla. Sovelluksen käyttöliittymä SudokuUi luo tietokantayhteyden ja vie DAO-objektia eteenpäin muille näkymille, jotka hakevat tarvittavan datan rajapinnan kautta.

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/pakkausluokka.png">


## Päätoiminnallisuudet

Seuraavaksi kuvataan sovelluksen toimintalogiikkaa sekvenssikaavioiden avulla.

### Pelin käynnistys ja Sudokun aloittaminen

Kun käyttäjä käynnistää ohjelman hän voi valita vaikeusasteen sudokulle, seuraavaksi kuvataan tämän uuden Sudokun luonnin toimintalogiikka ilman graafisen käyttöliityymän komponentteja.

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/generatingEasySudokuVersion2.png">

Sekvenssikaaviossa on pyritty aukaisemaan mahdollisimman tarkasti kuinka sudoku luodaan, valitettavasti koodissa on suuri määrä rekursiokutsuja, joten sekvenssikaavio on näiden osalta hieman puuttellinen.

### Sudoku pelin lataaminen

Koska Sudoku pelin lataaminen tapahtuu suurin osin graafisen käyttöliittymän kautta, niin seuraava sekvenssikaavio on yleismalkaisesti tehty, missä ei ole läheskään otettu huomioon kaikkia JavaFX ja FXML:n tarvittavia metodeja. Seuraava sekvenssikaavio kuvaa siis pelin käynnistymisestä pelin lataamiseen, olettaen, että yksi peli on tallennettuna ja se on ainoa listassa indeksissä 0. 

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/loadgamesequence.png">

Oleellista sekvenssikaaviossa on se, että tietokannasta haetaan lista tallennetuista peleistä, näistä luodaan näkymään painikkeita nimellä "Game x", missä x on numero. Pelejä voi maksimissaan olla 10, sillä tietokannan palauttama lista on rajoitettu 10 ensimmäiseen riviin.

## Tietojen pysyväistallennus

Pakkauksen sudoku.dao luokka DatabaseSudokuDao huolehtii tietojen tallettamisesta ja hakemisesta tietokannasta. SudokuUi.java luo tietokantayhteyden ja muut luokat käyttävät SudokuDao rajapintaa.

Luokat noudattavat Data Access Object -suunnittelumallia ja ne on tarvittaessa mahdollista korvata esim. tallettamalla muualle kuin tietokantaan, kuten tiedostoon. Luokka on eristetty rajapinnan SudokuDao taakse ja sovelluslogiikka ei ole suoraan yhteydessä tietokantaan.


## Ohjelmaan jääneet heikkoudet

Virheilmoituksia ei käsitellä lainkaan fiksusti, jos näitä ilmaantuisi käyttäjälle. Mikäli löydät jonkin bugin ohjelmasta, jota ei ole mainittu tässä dokumentaatiossa, voit avata [issuen](https://github.com/sebazai/ot-harjoitustyo/issues) tässä repossa. Kuvailethan ongelman mahdollisimman tarkasti ja tarvittaessa käytät kuvia.

### Sovelluslogiikka

Mikäli generoituisi sudoku joka voisi sisältää kaksi tai useamman ratkaisun, niin vihjeen antaminen perustuu yhteen ratkaisuun ja täten vihje voi olla huono tai väärä, tällä hetkellä vihjeen antaminen perustuu generoituun valmiiseen sudokuun, joka on tallennettu solvedSudoku kaksiuloitteiseen listaan ja poimii täältä numeron jota ei ole pelilaudalla. 

### Tietokanta

Mikäli tietokannan poistaa kun peli on pyörimässä tulostuu virheilmoitukset konsoliin. 

### Käyttöliittymä

Koko pelilauta piirretään [SudokuBoardController](https://github.com/sebazai/ot-harjoitustyo/blob/master/sudoku/src/main/java/sudoku/ui/SudokuBoardController.java) tiedostossa aina kun lisätään numero tai siirrytään ruudusta toiseen, eli hitaalla koneella tämä voisi aiheuttaa hidastusta. Tämä olisi fiksuinta tehdä kahdella päällekkäisellä Canvaksella, jossa alimmassa olisi ruudukot ja ylempi olisi numerot ja ympyrä mikä ruutu on valittuna, jolloin piirtyisi vähemmän ja voisi nopeuttaa piirtämistä ruudulle.
