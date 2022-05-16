# Vaatimusmäärittely

## Tarkoitus

Sovelluksessa pelataan [Blackjack](https://www.casinotampere.fi/pelit/blackjack) -kasinopeliä.

## Käyttöliittymä

Käyttäjä pelaa peliä graafisessa käyttöliittymässä.

## Toiminnalisuus

Perustoiminnallisuutena on yksittäisen Blackjack -kierroksen pelaaminen. Kierrosten välissä käyttäjä voi muuttaa panoskokoja tai kassassa olevan rahan määrää.

Kierros alka perusjaolla, joka määrittää kierroksen lähtötilanteen.

Kierroksen aikana eri vaiheissa käyttäjä päättää ottaako hän lisäkortin vai ei, tuplaako, vakuuttaako tai luovuttaako.

Sovelluksen pelin säännöt ovat sovelluksessa samat kuin Helsingin ja Tampereen kasinoilla seuraavin poikkeuksin:
 - Peliä pelataan ns. loputtomalla pakalla, missä jokaista korttia on ääretön määrä.
 - Samanarvoisten korttien jakaminen kahdeksi kädeksi ei ole sallittua.
 - Blackjackistä ei ole mahdollista saada tasarahamaksua.

## Kehitys

Pelin toiminnalisuuksia luotiin yksitellen.
Ensimmäiseen versioon tuli pelaajalle vaihtoehdot joko ottaa lisäkortti tai jäädä.
Tämän jälkeen sovellukseen lisättiin panostus, sitten muut pelitoiminnot (tuplaus, luovutus, vakuutus).
Lopullinen versio on ensimmäinen, jossa on käytössä graafinen käyttöliittymä. Pelin sovelluslogiikka rakennettiin ensin tekstikäyttöliittymän kautta.
