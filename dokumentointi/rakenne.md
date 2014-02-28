Rakenteen kuvausta
==================

GameWindow:ssa on sisällä 2 eri "luokkaa" (?), PlayArea ja Menu.

Menu on oletuksena näkyvissä, josta valitaan pelimoodi, joka on tallennettu
Enumiksi.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
LETTER(Letter.class), NUMBER(Number.class), WORD(Word.class);
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Kaikki nuo toteuttavat rajapinnan PlayObject, eli seuraavat metodit (2
alimmaista on lisätty sanatukea varten)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public void move();
public boolean shouldDie();
public Object getContent();
public int getValue();
public int getWidth();
public int getX();
public int getY();
public void setX(int x);
public void setY(int y);
public void setArea(PlayAreaGUI area);
public void addTypedLetter(char input);
public Object getTypedContent();
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Tämä mahdollistaa pelin "suhteellisen" modulaarisen rakenteen, eli noita voisi
lisätä rajattomasti, kuten esimerkiksi. erikoismerkit (!#@?=%& jne).



Valittuasi pelin, Menu lähettää kutsun GameWindowiin joka päivittää GameArean
moden oikeaksi ja vaihtaa sen aktiiviseksi näkyville.

Näppäinkuuntelija kuuntelee painalluksia, ja reagoi niihin oikein. Välilyönti on
erikoisnäppäin, josta peli menee tauolle, eli kaikki pysähtyvät (Käytännössä
Timer pysäytetään).



Pelin loputtua näyttöä vain klikataan, joka tällöin tuo Menu:n taas
aktiiviseksi, ja näkyväksi.
