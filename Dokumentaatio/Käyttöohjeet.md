Ohjelman voi suorittaa demo-kuvaa käytttäen suoraan esim. netbeansissa painamalla "run project". 
Kuvaa voi vaihtaa muuttamalla Main.java tiedoston alussa olevan muuttujan "imageName" arvoa.
Mikäli ohjelmaan haluaa tuoda omia kuvia, tulee kuvat siirtää Splash\src\main\resources kansioon ja antaa muuttujalle arvoksi kuvan nimi.

Käytettäviä algoritmejä voi vaihtaa ImageEditor.java tiedoston metodista "selectAndReplacePixelsByCordinates". 
Tätä ei kuitenkaan suositella muuhun kuin testitarkoitukseen, sillä muut algoritmit toimivat huomattavasti huonommin
kuin oletuksena asetettu FloodFillWithMixedRepeat (ne on tehty lähinnä testausta ja sopivan algoritmin löytämistä varten). 
