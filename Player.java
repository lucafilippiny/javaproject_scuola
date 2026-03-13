import java.util.Scanner;

public class Player {

    String nome;
    String classe;
    int hp;
    int mp;
    int forza;
    int intelligenza;
    int destrezza;   // statistiche
    int fortuna;
    int agilita;
    int difesa;
    int arcano;
    int iniziativa;

    public Player(String nome, String classe){
        this.nome = nome;
        this.classe = classe;

        switch(classe){
            case "guerriero":
                hp = 120; mp = 20; forza = 15; intelligenza = 5;
                destrezza = 8; fortuna = 6; agilita = 7; difesa = 12;
                arcano = 2; iniziativa = 6;
                break;
            case "mago":
                hp = 70; mp = 120; forza = 3; intelligenza = 16;
                destrezza = 7; fortuna = 10; agilita = 6; difesa = 5;
                arcano = 15; iniziativa = 8;
                break;
            case "ladro":
                hp = 90; mp = 40; forza = 8; intelligenza = 8;
                destrezza = 15; fortuna = 12; agilita = 14; difesa = 7;
                arcano = 4; iniziativa = 13;
                break;
        }
    }

    public void mostraStatistiche(){
        System.out.println("Nome: " + nome + " | Classe: " + classe);
        System.out.println("HP: " + hp + " | MP: " + mp);
        System.out.println("Forza: " + forza + " | Intelligenza: " + intelligenza);
        System.out.println("Destrezza: " + destrezza + " | Fortuna: " + fortuna);
        System.out.println("Agilità: " + agilita + " | Difesa: " + difesa);
        System.out.println("Arcano: " + arcano + " | Iniziativa: " + iniziativa);
        System.out.println("----------------------------");
    }

    // Metodo statico per creare un Player
    public static Player creaPlayer(Scanner scanner, int numeroGiocatore){

        System.out.println("Inserisci nome del Player " + numeroGiocatore + ":");
        String nome = scanner.nextLine();

        System.out.println("Scegli la classe Player " + numeroGiocatore + ":");
        System.out.println("1 - Guerriero");
        System.out.println("2 - Mago");  //scelta classe
        System.out.println("3 - Ladro");
        int scelta = scanner.nextInt();
        scanner.nextLine(); // pulizia buffer
        String classe = "";
        do{
            if (scelta == 1){classe = "guerriero";

            }else if (scelta == 2){
                classe = "mago";
            }else if (scelta == 3){ classe = "ladro";
            }else{
                System.out.println("hai inserito un numero sbagliato");
                System.out.println("riprova");
                System.out.println("Scegli la classe Player " + numeroGiocatore + ":");
                System.out.println("1 - Guerriero");
                System.out.println("2 - Mago");
                System.out.println("3 - Ladro");
                scelta = scanner.nextInt();
                scanner.nextLine(); // pulizia buffer

            }
        }while(scelta < 0 || scelta > 3);

        return new Player(nome, classe);
    }
}
