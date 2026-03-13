import java.security.SecureRandom;
import java.util.Scanner;

public class Combattimento { //classe principale motore del gioco

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom(); 

        Player player1 = Player.creaPlayer(scanner, 1); //Questa parte è molto importante perché crea i due oggetti Player usando un metodo statico,
        Player player2 = Player.creaPlayer(scanner, 2);//chiama il metodo creaPlayer che si trova dentro la classe Player
                                                                        // È statico, quindi può essere chiamato direttamente dalla classe senza creare prima un oggetto.

        System.out.println("\n--- Statistiche iniziali ---");
        player1.mostraStatistiche(); //Chiama il metodo che stampa tutte le statistiche del personaggio.
        player2.mostraStatistiche();

        int turno = 1;

        while(player1.hp > 0 && player2.hp > 0){
            System.out.println("\n=== Turno " + turno + " ===");

            attacca(player1, player2, random); //chiamo il metodo attacca
            if(player2.hp <= 0) break;

            attacca(player2, player1, random);
            if(player1.hp <= 0) break;

            turno++;
        }

        System.out.println("\n--- Risultato finale ---");
        if(player1.hp <= 0 && player2.hp <= 0) System.out.println("Pareggio!");
        else if(player1.hp <= 0) System.out.println(player2.nome + " vince!");
        else System.out.println(player1.nome + " vince!");
    }

    public static void attacca(Player attaccante, Player difensore, SecureRandom random){
        int dado = random.nextInt(20) + 1; //Simula un dado da 20 (1–20).

        if(dado == 1) System.out.println(attaccante.nome + " ha MISSATO!"); // attaccante colpisce
        else if(dado == 20){
            int danno = 40; // se dado = 20, fa 40 di danno
            difensore.hp -= danno;
            System.out.println("CRITICAL HIT! " + attaccante.nome + " infligge " + danno);
        }
        else{
            difensore.hp -= dado;
            System.out.println(attaccante.nome + " infligge " + dado + " danni"); 
        }

       if(difensore.hp > 0){
        System.out.println(difensore.nome + " HP rimasti: " + difensore.hp); //stampo gli hp rimasti del difensore
       }else{
        System.out.println(difensore.nome + " HP rimasti: 0"); //se va sotto lo zero scrive zero
       }
    }
}
