import java.util.Scanner;


// Viene usato sia da Combattimento (versione console) sia da GiocoGUI/GamePanel (versione grafica).
public class Player {

    // Dati principali del personaggio: nome, classe, punti vita/mana e statistiche di combattimento.
    // Questi valori vengono letti e modificati dai metodi di Combattimento durante attacchi, pozioni e drop.
    String nome;
    String classe;
    int hp;
    int mp;
    int forza;
    int intelligenza;
    int destrezza;       //statistiche per ogni personaggio
    int fortuna;
    int agilita;
    int difesa;
    int arcano;
    int iniziativa;
    String arma;
    int pozioniHp;
    int pozioniMp;

    // Costruisce un Player partendo da nome e classe scelta.
  
    public Player(String nome, String classe){
        this.nome   = nome;
        this.classe = classe;
        this.arma   = "Nessuna";
        this.pozioniHp = 2;
        this.pozioniMp = 1;

        // Imposta statistiche iniziali diverse in base alla classe.
        // Queste statistiche influenzano danno, schivata e critico nei metodi di Combattimento.
        switch(classe){
            case "guerriero":
                // Robusto, danno costante
                hp = 160; mp = 30; forza = 15; intelligenza = 5;
                destrezza = 8; fortuna = 8; agilita = 8; difesa = 14;
                arcano = 2; iniziativa = 6;
                break;
            case "mago":
                // Alto arcano e fortuna — più difficile 
                hp = 145; mp = 160; forza = 17; intelligenza = 22;
                destrezza = 9; fortuna = 18; agilita = 12; difesa = 9;
                arcano = 25; iniziativa = 10;
                break;
            case "ladro":
                // Agile, schiva spesso, critico facile 
                hp = 145; mp = 50; forza = 15; intelligenza = 9;
                destrezza = 18; fortuna = 20; agilita = 20; difesa = 12;
                arcano = 5; iniziativa = 16;
                break;
        }
    }

    // Stampa le statistiche su console.
   
    public void mostraStatistiche(){
        System.out.println("Nome: " + nome + " | Classe: " + classe);
        System.out.println("HP: " + hp + " | MP: " + mp);
        System.out.println("Forza: " + forza + " | Intelligenza: " + intelligenza);
        System.out.println("Destrezza: " + destrezza + " | Fortuna: " + fortuna);
        System.out.println("Agilità: " + agilita + " | Difesa: " + difesa);
        System.out.println("Arcano: " + arcano + " | Iniziativa: " + iniziativa);
        System.out.println("Arma: " + arma);
        System.out.println("Pozioni HP: " + pozioniHp + " | Pozioni MP: " + pozioniMp);
        System.out.println("----------------------------");
    }

    // Crea un player leggendo i dati da Scanner.
    
    public static Player creaPlayer(Scanner scanner, int numeroGiocatore){
        System.out.println("Inserisci nome del Player " + numeroGiocatore + ":");
        String nome = scanner.nextLine();
        System.out.println("Scegli la classe:");
        System.out.println("1 - Guerriero | 2 - Mago | 3 - Ladro");
        int scelta = scanner.nextInt();
        scanner.nextLine();
        String classe = "";

        do {
            if(scelta == 1)      classe = "guerriero";
            else if(scelta == 2) classe = "mago";
            else if(scelta == 3) classe = "ladro";
            else {
                System.out.println("Numero sbagliato, riprova (1/2/3):");
                scelta = scanner.nextInt();
                scanner.nextLine();
            }
        } while(scelta < 1 || scelta > 3);
        return new Player(nome, classe);
    }
}
