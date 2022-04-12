# Ohjelmistotekniikan harjoitustyö

***Lassi Savolainen***

**Black Jack -sovellus**

Sovelluksessa pelataan Blackjack-kasinopeliä suomalaisten pelikasinoiden säännöillä.

## Dokumentaatio

[Työaikakirjanpito](https://github.com/lassisav/BJApp/blob/master/documentation/tuntikirjanpito.md)

[Vaatimusmäärittely](https://github.com/lassisav/BJApp/blob/master/documentation/vaatimusmaarittely.md)

[Changelog](https://github.com/lassisav/BJApp/blob/master/documentation/changelog.md)

[Arkkitehtuuri](https://github.com/lassisav/BJApp/blob/master/documentation/arkkitehtuuri.md)

## Komentorivitoiminnot

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

generoi hakemistoon _target_ suoritettavan jar-tiedoston _BJApp-1.0-SNAPSHOT.jar_

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/lassisav/BJApp/blob/master/BJApp/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
