 class HvitRute extends Rute{
   Boolean aktivert;

  public HvitRute(int r, int k){
    super(r,k);
    aktivert = false;
  }

  public void gaa(String vei){
    aktivert = true;
    if(naboNord != null && !naboNord.aktivert()){
      naboNord.gaa(vei + ("("+kolonne+","+rad+")"+"--> "));
    }
    if(naboOst != null && !naboOst.aktivert()){
      naboOst.gaa(vei + ("("+kolonne+","+rad+")"+"--> "));
    }
    if(naboSor != null && !naboSor.aktivert()){
      naboSor.gaa(vei + ("("+kolonne+","+rad+")"+"--> "));
    }
    if(naboVest != null && !naboVest.aktivert()){
      naboVest.gaa(vei + ("("+kolonne+","+rad+")"+"--> "));
    }
    deaktiver();
  }


  protected Boolean aktivert(){
    return aktivert;
  }

  protected void deaktiver(){
    aktivert = false;
  }

  public String charTilTegn(){
    return ".";
  }
}
