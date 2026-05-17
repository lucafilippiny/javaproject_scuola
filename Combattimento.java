import java.security.SecureRandom;
import java.util.Scanner;


public class Combattimento {

    // Avvia una partita da terminale: crea Player, sceglie Mondo, combatte nemici e boss.
    // IMPORTANTE: L'interfaccia grafica non passa da questo main, ma riusa i metodi statici sotto.
    public static void main(String[] args){

        //Scanner per input utente e SecureRandom per dado.
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        // Crea il personaggio tramite Player.creaPlayer e gli assegna l'arma iniziale.
        Player player1 = Player.creaPlayer(scanner, 1);
        System.out.print(assegnaArmaIniziale(player1));

        // Crea i cinque mondi disponibili usando le factory della classe Mondo.
        Mondo pyrakor   = Mondo.creaPyrakor();
        Mondo thalassyr = Mondo.creaThalassyr();
        Mondo granmor   = Mondo.creaGranmor();
        Mondo aerathon  = Mondo.creaAerathon();
        Mondo noctyria  = Mondo.creaNoctyria();

        System.out.println("\nSCEGLI UN MONDO:");
        System.out.println("1 - PYRAKOR | 2 - THALASSYR | 3 - GRANMOR | 4 - AERATHON | 5 - NOCTYRIA");
        int sceltaMondo = scanner.nextInt();
        scanner.nextLine();

        Mondo mondoScelto = null;

        do {
  
        if(sceltaMondo == 1)      mondoScelto = pyrakor;
        else if(sceltaMondo == 2) mondoScelto = thalassyr;
        else if(sceltaMondo == 3) mondoScelto = granmor;
        else if(sceltaMondo == 4) mondoScelto = aerathon;
        else if(sceltaMondo == 5)  mondoScelto = noctyria;
        else {
                System.out.println("Numero sbagliato, riprova:");
                sceltaMondo = scanner.nextInt();
                scanner.nextLine();
            }
        } while(sceltaMondo < 1 || sceltaMondo > 5);
      

        System.out.println("\n======================");
        System.out.println("MONDO: " + mondoScelto.nome);
        System.out.println(mondoScelto.lore);
        System.out.println("\n++ Statistiche iniziali ++");
        player1.mostraStatistiche();

        // Ciclo dei nemici normali del mondo.
      
        for(int i = 0; i < mondoScelto.nemici.length; i++){
            Nemico nemico = mondoScelto.nemici[i];
            System.out.println("\n======================");
            System.out.println("NEMICO " + (i+1) + ": " + nemico.nome + " (HP: " + nemico.hp + ")");


            combattiControNemico(player1, nemico, random, scanner); //funzione che inizia il combattimento



            if(player1.hp <= 0){
                System.out.println("\nGAME OVER! " + player1.nome + " sconfitto da " + nemico.nome + ".");
                return;
            }
            System.out.println(nemico.nome + " sconfitto!");
            System.out.print(assegnaDrop(player1, random));
        }

        // Dopo i nemici normali viene caricato il Boss del mondo scelto.
        Boss bossFinale = mondoScelto.boss;
        System.out.println("\n======================");
        System.out.println("BOSS FINALE: " + bossFinale.nome + " (HP: " + bossFinale.hp + ")");


        combattiControNemico(player1, bossFinale, random, scanner);

        if(player1.hp <= 0){
            System.out.println("\nGAME OVER! Sconfitto dal boss " + bossFinale.nome + ".");
        } else {
            System.out.println("\n" + bossFinale.nome + " sconfitto! MONDO COMPLETATO!");
        }
    }

    // Gestisce un combattimento completo da terminale contro un Nemico o un Boss.

    // Usa attaccaPlayer, attaccaNemico e i metodi pozione della stessa classe.

    public static void combattiControNemico(Player player, Nemico nemico, SecureRandom random, Scanner scanner){
        int turno = 1;

        // Continua finche' entrambi hanno HP sopra zero.
        while(player.hp > 0 && nemico.hp > 0){
            System.out.println("\n=== Turno " + turno + " ===");
            System.out.println("HP " + player.nome + ": " + player.hp
                    + " | Pozioni HP: " + player.pozioniHp
                    + " | Pozioni MP: " + player.pozioniMp);
            System.out.println("A - Attacca | U - Pozione HP (+50 HP) | M - Pozione MP (+15 Forza)");
            String scelta = scanner.nextLine();

            // Applica l'azione scelta dal giocatore e poi, se il nemico e' vivo, fa contrattaccare il nemico.
            //le funzioni ignorano se la scelta e' minuscola o maiscuola
            if(scelta.equalsIgnoreCase("U")){

                System.out.print(usaPozioneHp(player));

            } else if(scelta.equalsIgnoreCase("M")){

                System.out.print(usaPozioneMP(player));
                System.out.print(attaccaPlayer(player, nemico, random));

            } else {

                System.out.print(attaccaPlayer(player, nemico, random));
            }

            if(nemico.hp <= 0) break;
            System.out.print(attaccaNemico(nemico, player, random));
            if(player.hp <= 0) break;
            turno++;
        }
    }

    // Assegna un'arma iniziale in base alla classe del Player.
    
    public static String assegnaArmaIniziale(Player player){
        if(player.classe.equals("guerriero")){
            player.arma = "Spada base"; player.forza += 5;
            return "\nArma iniziale: " + player.arma + " (+5 Forza)\n";
        } else if(player.classe.equals("mago")){
            player.arma = "Bastone base"; player.arcano += 5;
            return "\nArma iniziale: " + player.arma + " (+5 Arcano)\n";
        } else {
            player.arma = "Pugnale base"; player.agilita += 5;
            return "\nArma iniziale: " + player.arma + " (+5 Agilità)\n";
        }
    }

    // Decide il premio dopo un nemico sconfitto.
    
    public static String assegnaDrop(Player player, SecureRandom random){
        int drop = random.nextInt(100) + 1;
        if(drop <= 40) return "Nessun oggetto trovato.\n";
        if(drop <= 65){
            player.pozioniHp++;
            return "Trovata Pozione HP! Totale: " + player.pozioniHp + "\n";
        }
        if(drop <= 85){
            player.pozioniMp++;
            return "Trovata Pozione MP! Totale: " + player.pozioniMp + "\n";
        }
        return assegnaArmaDrop(player, random);
    }

    // Applica un drop arma e modifica direttamente le statistiche del Player.
    // E' chiamato solo da assegnaDrop quando l'estrazione casuale supera la soglia delle pozioni.
    public static String assegnaArmaDrop(Player player, SecureRandom random){
        int a = random.nextInt(3);
        if(a == 0){
            player.arma = "Spada del Guerriero"; player.forza += 10;
            return "Arma Trovata: " + player.arma + " (+10 Forza)\n";
        } else if(a == 1){
            player.arma = "Bastone Arcano"; player.arcano += 10;
            return "Arma Trovata: " + player.arma + " (+10 Arcano)\n";
        } else {
            player.arma = "Pugnale del Ladro"; player.agilita += 10;
            return "Arma Trovata: " + player.arma + " (+10 Agilità)\n";
        }
    }

    // ===== POZIONE HP =====
    // Cura il Player e consuma una pozione HP.
    // Nella GUI il nemico attacca comunque subito dopo, gestito da turnoPozioneHp.
    public static String usaPozioneHp(Player player){
        if(player.pozioniHp > 0){
            player.hp += 50; player.pozioniHp--;
            return player.nome + " usa Pozione HP: +50 HP (tot: " + player.hp + ")\n"
                    + "Pozioni HP rimaste: " + player.pozioniHp + "\n";
        }
        return "Non hai Pozioni HP!\n";
    }

    // ===== POZIONE MP: +15 Forza permanente =====
    // Potenzia il Player e consuma una pozione MP.
    
    public static String usaPozioneMP(Player player){
        if(player.pozioniMp > 0){
            player.forza += 15; player.mp += 30; player.pozioniMp--;
            return player.nome + " usa Pozione MP! +15 Forza, +30 MP\n"
                    + "Pozioni MP rimaste: " + player.pozioniMp + "\n";
        }
        return "Non hai Pozioni MP!\n";
    }

    // ===== ATTACCO PLAYER (dado generato internamente) =====
    // Attacco del Player con dado generato qui. Quindi prepara l' attacco
    
    public static String attaccaPlayer(Player attaccante, Nemico difensore, SecureRandom random){
        int dado = random.nextInt(20) + 1;
        return attaccaPlayerConDado(attaccante, difensore, dado, random);
    }

    // ===== ATTACCO PLAYER  =====
    // Calcola l'attacco del Player contro un Nemico/Boss usando un dado gia' deciso.
  

    public static String attaccaPlayerConDado(Player attaccante, Nemico difensore, int dado, SecureRandom random){
        String frase_dado = "🎲 Dado: " + dado + " — ";
        if(dado == 1) return frase_dado + attaccante.nome + " ha MISSATO!\n";

        if(random.nextInt(100) < difensore.agilita) // se esce un numero piu' picccolo della agilita' il difensore schiva l'attacco
            return frase_dado + difensore.nome + " ha schivato!\n"; 

        // Danno base: valore del dado + meta' della forza del Player.
        String extra = "";
        int danno = dado + attaccante.forza / 2;

        if(dado == 20 || (random.nextInt(100) < attaccante.fortuna)){
            danno *= 2;
            extra = "CRITICO! "; // se dado uguale 20 e il numero e' minore della fortuna, allora il danno si duplica e si ha un danno critico
        }

        int dannoFinale = Math.max(0, danno - difensore.difesa); //MAth.max evita danni negativi 

        difensore.hp -= dannoFinale; 

        return frase_dado + extra + attaccante.nome + " infligge " + dannoFinale + " danni. "
                + difensore.nome + " HP: " + Math.max(difensore.hp, 0) + "\n";
    }

    // ===== ATTACCO NEMICO 
    // Calcola il contrattacco del Nemico contro il Player.
  
    public static String attaccaNemico(Nemico attaccante, Player difensore, SecureRandom random){
        int dado = random.nextInt(20) + 1;

        String frase_dado = "";

        if(dado == 1) return attaccante.nome + " ha MISSATO!\n";

        if(random.nextInt(100) < difensore.agilita)
            return difensore.nome + " ha schivato l'attacco!\n";

        // Danno nemico: dado piu' meta' della forza, ridotto dalla difesa del Player.
        int danno = dado + attaccante.forza / 2;

        if(dado == 20){ danno *= 2; frase_dado += "CRITICO! "; }

        int dannoFinale = Math.max(0, danno - difensore.difesa);

        difensore.hp -= dannoFinale;
        
        return frase_dado + attaccante.nome + " infligge " + dannoFinale + " danni. "
                + difensore.nome + " HP: " + Math.max(difensore.hp, 0) + "\n";
    }
}
