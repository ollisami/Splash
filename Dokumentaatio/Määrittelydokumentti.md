# Määrittelydokumentti

Projektin tarkoituksena on luoda algoritmi, joka kykenee nopeasti ja tehokkaasti poistamaan kuvasta käyttäjän valitseman esineen.

## Toimintaperjaate:
- Käyttäjä valitsee poistettavan esineen
- Algoritmi valitsee poistettavat pikselit värin perusteella (Huom! Lopullisen algoritmin tulee kyetä valitsemaan myös hieman erisävyiset pikset sekä poistettavan esineen sisällä olevat pikselit, kuten demokuvassa oleva teksti "What ever it takes").
- algoritmi korvaa valitut pikset ympäristön värisiksi, jolloin esine "katoaa"

## Jatkokehitys:
- Esineen poiston tehokkuuden ja tarkkuuden parantaminen
- Algoritmi kykenee poistamaan esineen myös kuvista, joissa ei ole tasavärinen tausta (esim. kuvioidut tapetit, osaksi lattialla sijaitsevat esineet yms (demo kuvasarja 2))
- Algoritmin tehokkuuden kasvattaminen niin että sitä voidaan käyttää vidoissa / live streamissa.

## Käyttötarkoitus:
Algoritmista olisi tarkoitus saada niin tehokas, että sitä voitaisiin hyödyntää AR sovelluksissa. Tämä avaisi uusia mahdollisuuksia lisätyn todellisuuden sovellusten suunnittelussa, sillä virtuaali esineiden lisäämisen lisäksi pystyttäisiin poistamaan oikean maailman esineitä.
Esimerkkinä AR sisustus sovellus, jossa käyttäjä voi aluksi "poistaa" jo olemassa olevan sohvansa olohuoneesta ja asettaa tilalle virtuaali sohvan.

## Speksit:
- Ohjelmointikiele: Java
- Syötteet: Kuva (pixel data)
- Käytettävät algoritmit (saattaa muuttua toteutuksen edetessä):
  * -*Flood fill* (https://en.wikipedia.org/wiki/Flood_fill) 
  * -*Connected-component labeling* (https://en.wikipedia.org/wiki/Connected-component_labeling)
  * -*Blob detection* (https://en.wikipedia.org/wiki/Blob_detection)
- Tavoite aika ja tilavaativuudet: 
  * -*Tila:* O(n)
  * -*Aika:* 0(n^2)

![Demokuva 1](https://github.com/ollisami/Splash/blob/master/Dokumentaatio/Kuvat/Demo_1.jpg)
![Demokuva 2](https://github.com/ollisami/Splash/blob/master/Dokumentaatio/Kuvat/Demo_2.jpg)
![Demokuva 3](https://github.com/ollisami/Splash/blob/master/Dokumentaatio/Kuvat/Demo_3.jpg)
![Demokuva 4](https://github.com/ollisami/Splash/blob/master/Dokumentaatio/Kuvat/Demo_4.jpg)
![Demokuva 5](https://github.com/ollisami/Splash/blob/master/Dokumentaatio/Kuvat/Demo_5.jpg)
![Demokuva 6](https://github.com/ollisami/Splash/blob/master/Dokumentaatio/Kuvat/Demo_6.jpg)
