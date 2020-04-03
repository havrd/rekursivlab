abstract class Rute{
  Labyrint labyrint;
  int rad;
  int kolonne;
  Rute naboNord, naboOst, naboSor, naboVest;

  public Rute(int r, int k){
    rad = r;
    kolonne = k;
  }

  abstract public void gaa(String vei);

  abstract protected Boolean aktivert();

  abstract public String charTilTegn();

  abstract protected void deaktiver();

  public void finnUtvei(){
    gaa("");
  }

  public Labyrint hentLabyrint(){
    return labyrint;
  }

  public int hentRad(){
    return rad;
  }

  public int hentKolonne(){
    return kolonne;
  }

  public void setNaboNord(Rute r){
    naboNord = r;
  }

  public void setNaboOst(Rute r){
    naboOst = r;
  }

  public void setNaboSor(Rute r){
    naboSor = r;
  }

  public void setNaboVest(Rute r){
    naboVest = r;
  }

  public void setLabyrint(Labyrint l){
    labyrint = l;
  }
}
