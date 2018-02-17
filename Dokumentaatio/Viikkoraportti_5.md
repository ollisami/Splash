

Mitä olen tehnyt tällä viikolla?

    Tällä viikolla aikaa kului todella paljon erillaisten image inpainting tapojen tutkimiseen. En onnistunut löytämään yhtään
    pseudo koodia tai konkreettista algoritmiä aiheeseen liittyen, joten päätin jatkaa omien algoritmien kehittelyä. Loin oman 
    algoritmini, MixedRepeatPixelReplace, joka kopio korvattavaan alueeseen pikselit jokaisesta ilmansuunnasta ja lopuksi laskee
    niiden keskiarvon ja käyttää sitä. Algoritmi tuntuu toimivan suht nopeasti, eikä se loppupeleissä vie edes kovinkaan paljon
    tilaa (maksimissaan 4 * valitun alueen koko). Algoritmin ongelmana tosin on ettei se toimi kovin hyvin kuvissa joissa on paljon
    erillaisia kuvioita, mutta täyttää MVP:n määritelmän (eli poistaa yksittäisen esineen joka on sijoitettu tasaita tausta väriä
    vasten) melko hyvin. Lisäsin lisäksi Connected-component labeling algoritmin jolla valitaan pikseleitä (se ei tosin soveltunut
    tähän tarkoitukseen kovinkaan tehokkaasti) ja parantelin testejä.

Miten ohjelma on edistynyt?

    Jokseenkin hyvin, tosin hyvän image inpainting algoritmin löytäminen osoittautui huomattavasti haastavammaksi kuin olin 
    kuvitellut. Luulen että keskityn jatkossa vain oman algoritmini viilaamiseen.

Mitä opin tällä viikolla / tänään?

    Opin että kaikkea ei löydä edes googlesta, nimittäin image inpainting algoritmin pseudo koodia. Löysin monta kirjaa ja artikkelia
    aiheesta, mutta missään niistä ei esitetty varsinaista algoritmia ongelman ratkaisemiseksi. Ongelma ratkaiseminen osoittautui
    huomattavasti hankalammaksi kuin olin kuvitellut ja vaatisi huomattavasti enemmän tutkimistyötä ja aikaa kuin mitä tähän
    kurssiin on varattu.

Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

    Oikean image inpainting algoritmin löytäminen / toteuttaminen. Mistään ei löytynyt pseudokoodia jonka mukaan algoritmia
    olisi voinut alkaa rakentaa, joten päätin tehdä sen kokonaan itse.

Mitä teen seuraavaksi?

    Parantelen pikseleiden korvaamis algoritmiäni ja keskityn dokumentaation/testien tekemiseen.

Aikaa käytetty: 18h
