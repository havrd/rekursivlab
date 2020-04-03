import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.StringBuilder;

class Labyrint{
  Rute[][] ruteArray;
  int antRader;
  int antKolonner;
  Lenkeliste<String> utveier;
  String kortesteVei;
  int antallVeier;

  public static Labyrint lesFraFil(File fil) throws FileNotFoundException{
    Labyrint lab;
    int xKoord = 0;
    int yKoord = 0;
    Scanner inp = new Scanner(fil);
    int antR = inp.nextInt();
    int antK = inp.nextInt();
    Rute[][] ruteArr = new Rute[antR][antK];
    inp.nextLine();

    while(inp.hasNextLine()){
      String tegn = inp.nextLine();
      for(char ch:tegn.toCharArray()){
        if(ch == '.'){
          if(sjekkAapning(antR, antK, yKoord, xKoord)){
            Aapning a = new Aapning(yKoord, xKoord);
            ruteArr[yKoord][xKoord]=a;
          }
          else{
            HvitRute hr = new HvitRute(yKoord, xKoord);
            ruteArr[yKoord][xKoord]=hr;
          }
        }
        else if(ch == '#'){
          SortRute sr = new SortRute(yKoord, xKoord);
          ruteArr[yKoord][xKoord]=sr;
        }
        xKoord++;
      }
      xKoord = 0;
      yKoord++;
    }
    lab = new Labyrint(ruteArr, antR, antK);
    return oppdaterNabo(lab);
  }

  private Labyrint(Rute[][] arr, int radr, int kol){
    ruteArray = arr;
    antRader = radr;
    antKolonner = kol;
    utveier = new Lenkeliste<String>();
    for(int r = 0; r < antRader; r++){
      for(int k = 0; k < antKolonner; k++){
        ruteArray[r][k].setLabyrint(this);
      }
    }
  }

  public String toString(){
    StringBuilder lab = new StringBuilder();
    for(int r = 0; r < antRader; r++){
      for(int k = 0; k < antKolonner; k++){
        lab.append(ruteArray[r][k].charTilTegn());
      }
      lab.append("\n");
    }
    return lab.toString();
  }

  public Rute hentRute(int r, int k){
    return ruteArray[r][k];
  }

  public int hentRader(){
    return antRader;
  }

  public int hentKolonner(){
    return antKolonner;
  }

  public Lenkeliste<String> finnUtveiFra(int kol, int rad){
    utveier = new Lenkeliste<String>();
    antallVeier = 0;
    kortesteVei = "ingen utveier";
    for(int r = 0; r < antRader; r++){
      for(int k = 0; k < antKolonner; k++){
        if(ruteArray[r][k] instanceof HvitRute || ruteArray[r][k] instanceof Aapning)
          ruteArray[r][k].deaktiver();
      }
    }
    ruteArray[rad][kol].finnUtvei();
    System.out.println("\nAntall veier ut: "+antallVeier);
    System.out.println("\nKorteste vei: "+kortesteVei);
    System.out.println("\nAlle veier: \n");
    return utveier;
  }

  public void leggTilUtvei(String ut){
    utveier.leggTil(ut);
    antallVeier++;
    if(kortesteVei.equals("ingen utveier") || kortesteVei.length() > ut.length()){
      kortesteVei = ut;
    }
  }

  private static Boolean sjekkAapning(int r, int k, int y, int x){
    if(y==r-1 || y == 0 || x == k-1 || x == 0){
      return true;
    }
    else{
      return false;
    }
  }

  private static Boolean sjekkOmKant(Rute r){
    Labyrint lab = r.hentLabyrint();
    int rader = lab.hentRader();
    int kolonner = lab.hentKolonner();

    if(r.hentKolonne()==0 || r.hentRad()==0 || r.hentRad()==lab.hentRader()-1 || r.hentKolonne()==lab.hentKolonner()-1){
      return true;
    }
    else{
      return false;
    }
  }

  private static Labyrint oppdaterNabo(Labyrint l){
    for(int r = 0; r < l.hentRader(); r++) {
			for(int k = 0; k < l.hentKolonner(); k++) {
				Rute rute = l.hentRute(r, k);

				if(sjekkOmKant(rute)) {
					if(r>0) {
						rute.setNaboNord(l.hentRute(r-1,k));
					}else {
						rute.setNaboNord(null);
					}

					if(k<l.hentKolonner()-1){
            rute.setNaboOst(l.hentRute(r,k+1));
          }
          else{
            rute.setNaboOst(null);
          }

          if(r<l.hentRader()-1) {
						rute.setNaboSor(l.hentRute(r+1,k));
					}else {
						rute.setNaboSor(null);
					}

					if(k>0){
            rute.setNaboVest(l.hentRute(r,k-1));
          }
          else{
            rute.setNaboVest(null);
          }
				}else {
					rute.setNaboNord(l.hentRute(r-1,k));
          rute.setNaboOst(l.hentRute(r,k+1));
					rute.setNaboSor(l.hentRute(r+1,k));
					rute.setNaboVest(l.hentRute(r,k-1));
				}
			}

		}
		return l;
  }
}
