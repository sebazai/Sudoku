# Käyttöohje

Lataa tiedosto [sudoku.jar](https://github.com/sebazai/ot-harjoitustyo/releases/download/2.2/sudoku-2.2.jar)

## Ohjelman käynnistäminen

Avaa ladattu tiedosto. Linuxilla terminaalin kautta komennolla
```
java -jar sudoku-2.2.jar
```

## Käyttöliittymän ohjeet

### Menu näkymä
Sovellus käynnistyy menu näkymään, josta voit valita vaikeusasteen sudokulle tai ladata vanhan tallennetun pelin.

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/menu.png">

Kun vaikeusaste on valittuna, aukeaa uusi Sudoku peli.

### Sudoku näkymä

Sudoku näkymässä voit hiirellä valita ruudun tai käyttäen nuolinäppäimiä. Ruudut ottavat vain numeroita 0-9 välillä. Missä 0 tyhjentää valitun ruudun.

<img src ="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/sudoku.png">

Pelin voi ratkaista suoraan "Solve sudoku" -painiketta painamalla, tallettaa keskeneräisenä "Save and quit" -painiketta painamalla.

Mikäli Sudoku on hankala, voit myös pyytää vihjettä, joka tuo yhden numeron ruudulle, tämä tapahtuu painamalla "HINT".

"Empty board" -painike tyhjentää sinun lisäämäsi numerot ruudulta.

"Return to menu" vie takaisin menu ruutuun, tallentamatta peliä.

### Load game näkymä

<img src="https://github.com/sebazai/ot-harjoitustyo/blob/master/documentation/kuvat/loadscreen.png">

Kyseisessä ruudussa voit valita vanhan pelin, jonka olet tallettanut. Viimeiseksi tallennettu peli on ensimmäisessä, pelit ovat järjestetty aikaleiman mukaan uusimmasta vanhimpaan. 
Kun lataat pelin ja jatkat pelaamista, niin ladattu peli katoaa listalta, joten muista tallettaa peli, mikäli se vielä jää keskeneräiseksi.
Lista näyttää 10 vanhinta peliä.
