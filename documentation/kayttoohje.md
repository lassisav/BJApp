# Käyttöohje

Lataa tiedosto [BJApp.jar](https://github.com/lassisav/BJApp/releases/tag/viikko7)

## Ohjelman käynnistäminen

Ohjelma käynnistetään kansiosta BJApp, komennolla 

```
java -jar BJApp-FINAL.jar
```

## Aloitusnäkymä

Aloitusnäkymästä peli aloitetaan painamalla NEW GAME -näppäintä.

Pelaajalla on 1000 rahayksikköä, kun pelaaminen aloitetaan.

## Ennen kierroksen alkua

Pelaaja asettaa panoksen koon. panoksen koon tulee olla kymmenellä jaollinen, pienempi tai yhtäsuuri kuin pelaajan senhetkinen rahamäärä, sekä korkeintaan 500 rahayksikköä.

## Perusjako

Peli jakaa pelaajalle kaksi korttia ja jakajalle yhden kortin.

Jos jakajan ensimmäinen kortti on ässä, eli käden arvo on 1/11, pelaaja voi vakuuttaa (INSURANCE) kätensä. Pelaaja ei voi vakuuttaa kättänsä, jos hänellä on blackjack.

Ohjeen kohdassa Blackjack kerrotaan mitä tapahtuu jos pelaajalla on blackjack.

Jos pelaajalla ei ole blackjackiä, hän päättää lisäkortin (HIT), jäämisen (STAND), tuplauksen (DOUBLE) ja luovutuksen (SURRENDER) väliltä.

### Lisäkortti (HIT)

Peli jakaa pelaajalle kortin. Jos pelaajan käden arvo on tämän kortin jälkeen enemmän kuin 21, pelaaja häviää. Jos käden arvo on 21 tai vähemmän, voi hän valita lisäkortin ja jäämisen väliltä.

### Jää (STAND)

Pelaajan vuoro päättyy, ja jakajan vuoro alkaa.

### Tuplaus (DOUBLE)

Pelaajan panos kaksinkertaistuu, ja pelaajalle jaetaan yksi kortti. Jos pelaajan käden arvo on yli 21, pelaaja häviää. Muussa tapauksessa pelaajan vuoro päättyy ja jakajan vuoro alkaa.

### Luovutus (SURRENDER)

Pelaaja häviää puolet panoksestaan ja kierros päättyy.

### Blackjack

Blackjack on perusjaossa saatava käsi jossa on ässä, sekä kortti jonka arvo on 10. Blackjack on pelin arvokkain käsi.

Jos pelaajalla on blackjack, selvitetään saako jakajakin blackjackin. Jos jakaja ei saa blackjackia, pelaaja voittaa 1,5-kertaisesti panoksena. Jos jakaja saa blackjackin, peli on tasan.

Jos jakaja saa blackjackin, pelaaja häviää, ellei hänellä ole myöskin blackjackia. Jos pelaaja on pelannut vakuutuksen (INSURANCE), voittaa hän vakuutuspanoksen kaksinkertaisena.

### Jakajan vuoro

Kun pelaaja ei enää halua tai saa ottaa lisäkortteja, mutta hänellä on käsi jolla on mahdollista voittaa (käden arvo 21 tai vähemmän), alkaa jakajan vuoro.

Jakaja ottaa kortteja, kunnes hänen kätensä arvo on 17 tai enemmän.

Jos jakajan käden arvo on yli 21, pelaaja voittaa. Jos jakajan käden arvo on 21 tai vähemmän, voittaa se osapuoli jonka kädellä on suurempi arvo.

Arvojen ollessa samansuuruiset peli on tasan.

### Vakuutus

Jos jakajan ensimmäinen kortti on ässä, voi pelaaja vakuuttaa kätensä asettamalla haluamansa kokoisen panoksen (enintään puolet kädelle asetetusta panoksesta).

Jos jakaja saa blackjackin, pelaaja voittaa vakuutuspanoksen kaksinkertaisena, mutta kuitenkin häviää kädelle asetetun panoksen.

### Jaon jälkeen

Kun jako on päättynyt, pelin tuloksen näyttävästä tilasta jatketaan eteenpäin CONTINUE-näppäimellä.

### Jakojen välissä

Pelaajalla on kolme vaihtoehtoa:

 - QUIT, sulkee sovelluksen
 - NEW GAME, aloittaa uuden jaon
 - ADD FUNDS, antaa pelaajan nostaa rahamäärää haluamallaan summalla

### Optimipelistrategia

Pelin pelaamiseen optimaalisella pelistrategialla löytyy [ohje](https://wizardofodds.com/games/blackjack/expected-return-infinite-deck/) Wizard of Odds:n sivuilta.

Optimipeliohjeessa olevan taulu parikäsien pelaamisesta on tässä sovelluksessa tarpeetonta, sillä pelissä ei ole jakamisen vaihtoehtoa.
