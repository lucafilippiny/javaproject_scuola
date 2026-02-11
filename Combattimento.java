import java.security.SecureRandom;

public class Combattimento {

    public static void main(String[] args){

        SecureRandom random= new SecureRandom();

        int lp_P1 = 100;
        int lp_P2 = 100;

        int turno = 1;
        int dado;
        //System.out.println("Il dado maggiore sceglierà lo sfidante che inizierà la lotta ( premere invio ) > ")


        while (lp_P1 > 0 && lp_P2 >0 ){

            System.out.println("turno ---> "+turno);

            //inizia la lotta il P1

            int dado1 = random.nextInt(20 )+ 1;
            //System.out.println(" P1 infligge "+dado1+" di danno a P2");

            if(dado1 == 1){
                System.out.println("Il P1 ha missato!");
            }else if( dado1 == 20 ){
                int danno = 40; //danno critico, moltiplica x2 il numero del dado
                lp_P2 -=danno;
            } else {
                lp_P2-=dado1;
                System.out.println(" P1 infligge "+dado1+" danni");
            }
            if (lp_P2 <= 0){
                break;
            }

            System.out.println("lp_P2 ---> "+ lp_P2);

            int dado2 = random.nextInt(20 )+ 1;
            //System.out.println(" P2 infligge "+dado2+" di danno a P1");

            if(dado2 == 1){
                System.out.println("Il P2 ha missato!");
            }else if( dado2 == 20 ){
                int danno = 40; //danno critico, moltiplica x2 il numero del dado
                lp_P2 -=danno;
            } else {
                lp_P1-=dado2;
                System.out.println(" P2 infligge "+dado2+" danni");
            }
            if (lp_P1 <= 0){
                break;
            }

            System.out.println("lp P1 ---> "+ lp_P1);
            
            turno++;
         }

           if(lp_P1 <= 0 && lp_P2 <= 0){
            System.out.println("lo scontro finisce in pareggio");
         }else if(lp_P1 <= 0){
            System.out.println("il P2 vince il fight!");
         }else {
            System.out.println(" il P1 vince il fight!");
         }

    }


}
