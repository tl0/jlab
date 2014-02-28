Testausdokumentaatio
====================

Testaus valitettavasti jäi tämän työn osalta aika suppeahkoksi (Automaattiset
JUnit testit) :(

Kokoajan kuitenkin projektin edetessä tuli kirjaimellisesti lähes jokaisen
muutoksen jälkeen suoritettua ohjelma, ja testattua käsin, toimiiko kaikki vielä
oikein, ja auttoiko tekemäni muutokset. Mikäli ei, on helpompi päässä ajatella
missä mahdollinen virhe, kuin ruveta tekemään JUnitteja ja niistä päätellä
mitään.

Mielestäni käsintestauksesta on ollut 100% enemmän hyötyä kuin automaattisista
JUnit testeistä, jotka lähinnä ärsyttävät :( Etenkin kun kyse on
käyttöliittymällisestä ohjelmasta, koen JUnitit jotenkin turhaksi.



Bugit, jotka vielä elävät \>:(
------------------------------

-   Peli-ikkunan koon muuttaminen ei vaikuta oikein peliolioiden sijaintiin ja
    fysiikkaan (jälkimmäiseen ei ollenkaan valitettavasti (nopeuteen), eli nyt
    suurentamalla ikkunaa, voi helpottaa peliä..)

    -   Helppo ratkaisu olisi: (Mutta haluan pitää koon muuttamisen
        mahdollisena, tosin kesken vielä.. joskus fix?)

    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    setResizable(false);
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

-   Menussa olevat highscore -pisteet eivät päivity kun palataan pelistä
    päävalikkoon
