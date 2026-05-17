// Modello base di ogni avversario.

public class Nemico {

    // Statistiche essenziali del nemico.
  
    String nome;
    int hp;
    int forza;
    int difesa;
    int agilita;

    // Costruttore usato da Mondo per definire tutti i nemici standard dei cinque mondi.
    public Nemico(String nome, int hp, int forza, int difesa, int agilita){

        this.nome = nome;
        this.hp = hp;
        this.forza = forza;
        this.difesa = difesa;
        this.agilita = agilita;
    }
}
