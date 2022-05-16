# Ohjelmistotekniikan harjoitustyö

***Lassi Savolainen***

**Black Jack -sovellus**

Sovelluksessa pelataan Blackjack-kasinopeliä suomalaisten pelikasinoiden säännöillä.

## Dokumentaatio

[Työaikakirjanpito](https://github.com/lassisav/BJApp/blob/master/documentation/tuntikirjanpito.md)

[Vaatimusmäärittely](https://github.com/lassisav/BJApp/blob/master/documentation/vaatimusmaarittely.md)

[Changelog](https://github.com/lassisav/BJApp/blob/master/documentation/changelog.md)

[Arkkitehtuuri](https://github.com/lassisav/BJApp/blob/master/documentation/arkkitehtuuri.md)

[Käyttö-ohje](https://github.com/lassisav/BJApp/blob/master/documentation/kayttoohje.md)

## Releaset

[Viikko 5](https://github.com/lassisav/BJApp/releases/tag/viikko5)

[Viikko 7](https://github.com/lassisav/BJApp/releases/tag/viikko7)

## Komentorivitoiminnot

Komennot tulee suorittaa kansiossa BJApp.

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _BJApp-FINAL.jar_.

Tämän jälkeen ohjelman voi suorittaa komennolla 

```
java -jar target/BJApp-FINAL.jar
```

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/lassisav/BJApp/blob/master/BJApp/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
