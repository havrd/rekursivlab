class Aapning extends HvitRute{

 public Aapning(int r, int k){
   super(r,k);
 }

 public void gaa(String vei){
   labyrint.leggTilUtvei(vei + ("("+kolonne+","+rad+")"));
 }
}
