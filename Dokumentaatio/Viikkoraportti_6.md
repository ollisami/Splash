

Mitä olen tehnyt tällä viikolla?

    Tällä viikolla olen parantanut algoritmien suorituskykyä sekä lopputulosta. Sain MixedRepeat algoritmin toimimaan jo jokseenkin hyvin.
    Valitun alueen kartoittaminen ei aina onnistu toivotulla tavalla, jolloi lopputulos saattaa olla melko kamala, mutta aika usein algoritmi
    onnistuu poistamaan kuvista haastaviakin osia parin yrityksen jälkeen. Olen suhteellisen tyytyväinen nykytilanteeseen, sillä algoritmin
    painopiste oli nopeudessa eikä pixel perfect kuvanmuokkaamisessa. Monet muut image inpainting tavat vaativat satoja, tuhansia tai jopa
    miljoonia looppeja, jolloin lopputulos on luonnollisesti parempi, mutta tässä tavoite oli ennemmin saada "nopeasti jotain".
    Aiheesta on ollut aika mielenkiintoista lukea ja tutkia, mutta vaatisi todella paljon enemmän syventymistä jos lopputuloksesta haluaisi
    täydellisen.
    Lisäksi tein suorituskykytestejä ja kirjoitin testausdokumentaation (parantelen sitä ensi viikkolla).

Miten ohjelma on edistynyt?

    Aika hyvin. Olen melko tyytyväinen ohjelman nykyiseen suoritukseen. Ensi viikolla keskityn enemmän dokumentaation, kommenttien, testien
    yms. kirjoittamiseen.

Mitä opin tällä viikolla / tänään?

    Opin paljon uutta image inpainting:istä ja teoriasta sen taustalla. 

Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

    Kirjoitetaanko aika/tilavaatimuksissa esim. O(2n) vai O(n) jos algoritmi esim. looppaa kahdesti datan läpi? Tirassahan nuo ylimääräiset
    kertoimet jätettiin usein pois, mutta tässä kertoimet voisi selkeyttää algoritmien välisiä eroja.
    Jätin lisäksi vielä joihinkin koodeihin javan valmiita tietorakenteita, sillä ne ovat tässä vaiheessa tarkoitettu lähinnä testikäyttöön.
    Oikeastaan Selection algoritmeistä vain FloodFill ja Replace algoritmeistä MixedRepeat (tai niiden yhdistelmä "FloodFillWithMixedRepeat")
    ovat sellaisia mitä sovellus oikeasti käyttää. Selatessani muiden projekteja huomasin että omassani on aika paljon erillaisia algoritmejä
    (3x Select, 3x replace ja 1x multi function + kaikki apuluokat), joten käykö jos keskityn nyt siihen että siivoan nämä oikeasti
    käytössä olevat luokat siisteiksi sekä kommentoin ne hyvin, mutta jätän käyttämättömät luokat projektiin jotta niitäkin voi demota
    ja vertailla, vai poistanko suosiolla kaikki ylimääräiset algoritmit pois jättäen vain parhaat jäljelle?

Mitä teen seuraavaksi?

    Keskityn koodin kommentoitiin, siivoamiseen, refactorointiin, dokumentaatioon yms.

Aikaa käytetty: 18h
