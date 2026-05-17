

// Specializzazione di Nemico per il boss finale di ogni mondo.

public class Boss extends Nemico {

    // Riusa il costruttore di Nemico tramite super, per mantenere le stesse statistiche di combattimento.
    public Boss(String nome, int hp, int forza, int difesa, int agilita){

        super(nome, hp, forza, difesa, agilita);
    }
}
