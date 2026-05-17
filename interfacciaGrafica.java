import javax.swing.*; //serve per le classi jframe,jpanel etc
import java.awt.*; // serve per i layout del frame e la parte estetica
import java.security.SecureRandom;

public class interfacciaGrafica extends JFrame {

    // variabili del gioco
    private Player player;
    private Mondo mondo;
    private Nemico nemicoAttuale;
    private final SecureRandom random = new SecureRandom();
    private int indiceNemico, turno, hp_del_personaggio, hp_del_NEMICO;
    private boolean bossFight, attivo;

    // componenti grafici
    private JTextArea pannello_centrale;
    private JProgressBar barraPlayer, barraNemico;
    private JButton Bottone_ATTACCA, bottone_HP, bottone_MP;

    // etichette sopra le barre HP e pannello info sinistro
    private JLabel labelHpPlayer, labelHpNemico, infoStats;

    public interfacciaGrafica() {

        // impostazioni della finestra
        setTitle("Adventure Zone"); // titolo della finestra
        setSize(860, 540); // dimensioni della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // funzione che crea la x quando si chiude il programma
        setLocationRelativeTo(null); // Centra la finestra.
        setLayout(new BorderLayout(6, 6)); // aggiunge un layout che divide la finestra in varie sezioni

        //pannello superiore: nome, classe, mondo, bottone inizia 
        JPanel Pannello_superiore = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        JTextField spazio_nome       = new JTextField("Eroe", 10);

        JComboBox<String> menuClasse = new JComboBox<>(new String[]{"guerriero", "mago", "ladro"});

        JComboBox<String> menuMondo  = new JComboBox<>(new String[]{"PYRAKOR", "THALASSYR", "GRANMOR", "AERATHON", "NOCTYRIA"});

        JButton bottone_Inizio = new JButton("INIZIA");

        // inserisco le cose date dall utente nei rispettivi label
        Pannello_superiore.add(new JLabel("Nome:"));
        Pannello_superiore.add(spazio_nome);

        Pannello_superiore.add(new JLabel("Classe:"));
        Pannello_superiore.add(menuClasse);

        Pannello_superiore.add(new JLabel("Mondo:"));
        Pannello_superiore.add(menuMondo);

        Pannello_superiore.add(bottone_Inizio);
        add(Pannello_superiore, BorderLayout.NORTH);

        // pannello sinistro: barre HP + tutte le info di gioco
        
        JPanel Pannello_sinistro = new JPanel();
        Pannello_sinistro.setLayout(new BoxLayout(Pannello_sinistro, BoxLayout.Y_AXIS));// BoxLayout.Y_AXIS dispone gli elementi in colonna (uno sotto l'altro)
        Pannello_sinistro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Pannello_sinistro.setPreferredSize(new Dimension(230, 0)); // larghezza fissa del pannello

        // creo le etichette che mostrano nome e hp sopra ogni barra
        labelHpPlayer = new JLabel("Player HP: -");
        labelHpNemico = new JLabel("Nemico HP: -");

        // creo le barre HP. setStringPainted mostra il numero dentro la barra
        barraPlayer = new JProgressBar();
        barraPlayer.setStringPainted(true);
        barraNemico = new JProgressBar();
        barraNemico.setStringPainted(true);

        // etichetta unica con tutte le info. aggiornata dal metodo aggiornaBarreHP()
        // usa <html> per poter andare a capo con <br> e usare grassetto con <b>
        infoStats = new JLabel("<html>-</html>");
        infoStats.setVerticalAlignment(SwingConstants.TOP);

        // aggiungo tutto al pannello sinistro
        Pannello_sinistro.add(labelHpPlayer);
        Pannello_sinistro.add(barraPlayer);
        Pannello_sinistro.add(Box.createVerticalStrut(6));  // spazio vuoto verticale
        Pannello_sinistro.add(labelHpNemico);
        Pannello_sinistro.add(barraNemico);
        Pannello_sinistro.add(Box.createVerticalStrut(10)); // spazio vuoto verticale
        Pannello_sinistro.add(infoStats); // sezione con personaggio, oggetti, azioni, partita
        add(Pannello_sinistro, BorderLayout.WEST);

        // pannello CENTRALE: pannello_centrale del combattimento con scroll 
        // JScrollPane aggiunge la barra di scorrimento automatica quando il testo è troppo lungo
        pannello_centrale = new JTextArea();
        pannello_centrale.setEditable(false);      // il giocatore non può scrivere nel pannello_centrale
        pannello_centrale.setLineWrap(true);       // va a capo automaticamente
        pannello_centrale.setWrapStyleWord(true);  // va a capo sulle parole, non a metà parola
        add(new JScrollPane(pannello_centrale), BorderLayout.CENTER);

        // pannello in basso: bottoni azione 
        JPanel pannelo_inferiore = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        Bottone_ATTACCA = new JButton("ATTACCA");
        bottone_HP      = new JButton("POZIONE HP");
        bottone_MP      = new JButton("POZIONE MP");

        // disabilitati finché non si preme INIZIA . diventeranno grigi
        Bottone_ATTACCA.setEnabled(false);
        bottone_HP.setEnabled(false);
        bottone_MP.setEnabled(false);

        pannelo_inferiore.add(Bottone_ATTACCA);
        pannelo_inferiore.add(bottone_HP);
        pannelo_inferiore.add(bottone_MP);
        add(pannelo_inferiore, BorderLayout.SOUTH);

        // azione bottone INIZIA 
        // crea il Player e il Mondo usando le classi già esistenti
        bottone_Inizio.addActionListener(e -> 
                    {

                    // legge il nome scritto dall'utente — se vuoto usa "Eroe"
                    String nome;
                    if (spazio_nome.getText().trim().isEmpty()) {
                        nome = "Eroe";
                    } else {
                        nome = spazio_nome.getText().trim();
                    }

                    // crea il personaggio con nome e classe scelti — usa Player.java
                    player = new Player(nome, (String) menuClasse.getSelectedItem());

                    // carica il mondo scelto usando i metodi statici di Mondo.java
                    String scelta = (String) menuMondo.getSelectedItem();
                    if      (scelta.equals("PYRAKOR"))   mondo = Mondo.creaPyrakor();
                    else if (scelta.equals("THALASSYR")) mondo = Mondo.creaThalassyr();
                    else if (scelta.equals("GRANMOR"))   mondo = Mondo.creaGranmor();
                    else if (scelta.equals("AERATHON"))  mondo = Mondo.creaAerathon();
                    else                                 mondo = Mondo.creaNoctyria();

                    // resetta tutte le variabili di gioco per una nuova partita
                    pannello_centrale.setText("");
                    indiceNemico = 0;
                    bossFight    = false;
                    attivo       = true;
                    turno        = 1;

                    // assegna l'arma iniziale usando il metodo di Combattimento.java
                    pannello_centrale.append(Combattimento.assegnaArmaIniziale(player));

                    // carica il primo nemico dall'array di Mondo.java
                    nemicoAttuale = mondo.nemici[0];
                    hp_del_personaggio   = player.hp;
                    hp_del_NEMICO   = nemicoAttuale.hp;

                    pannello_centrale.append("Mondo: " + mondo.nome + "\nPrimo nemico: " + nemicoAttuale.nome + "\n");

                    // aggiorna barre HP e le info del pannello sinistro
                    aggiornaBarreHP();

                    // abilita i bottoni ora che la partita è iniziata
                    Bottone_ATTACCA.setEnabled(true);
                    bottone_HP.setEnabled(true);
                    bottone_MP.setEnabled(true);
                } 
            );




        // azione bottone ATTACCA ───
        // usa i metodi statici di Combattimento.java — non riscrive nessuna logica
        Bottone_ATTACCA.addActionListener(e -> {
            if (!attivo) return; // sicurezza: non fare niente se la partita non è attiva

            pannello_centrale.append("\n-- Turno " + turno + " --\n");

            // tira il dado da 20 e attacca il nemico
            int dado = random.nextInt(20) + 1;
            pannello_centrale.append(Combattimento.attaccaPlayerConDado(player, nemicoAttuale, dado, random));

            // controlla se il nemico è stato sconfitto
            if (nemicoAttuale.hp <= 0) {
                nemicoSconfitto();
                return;
            }

            // il nemico contrattacca
            pannello_centrale.append(Combattimento.attaccaNemico(nemicoAttuale, player, random));
            fineTurno();
        });

        // azione bottone POZIONE HP 
        // cura il player di 50 HP — il nemico attacca comunque nello stesso turno
        bottone_HP.addActionListener(e -> {
            if (!attivo) return;

            pannello_centrale.append("\n-- Turno " + turno + " --\n");
            pannello_centrale.append(Combattimento.usaPozioneHp(player));                         // usa Combattimento.java
            pannello_centrale.append(Combattimento.attaccaNemico(nemicoAttuale, player, random)); // il nemico attacca comunque
            fineTurno();
        });

        // azione bottone POZIONE MP 
        // dà +15 Forza permanente al player, poi attacca subito con il bonus
        bottone_MP.addActionListener(e -> {
            if (!attivo) return;

            pannello_centrale.append("\n-- Turno " + turno + " --\n");
            pannello_centrale.append(Combattimento.usaPozioneMP(player)); // +15 forza permanente — usa Combattimento.java

            // attacca subito con la forza potenziata
            int dado = random.nextInt(20) + 1;
            pannello_centrale.append(Combattimento.attaccaPlayerConDado(player, nemicoAttuale, dado, random));

            // controlla se il nemico è stato sconfitto
            if (nemicoAttuale.hp <= 0) {
                nemicoSconfitto();
                return;
            }

            // il nemico contrattacca
            pannello_centrale.append(Combattimento.attaccaNemico(nemicoAttuale, player, random));
            fineTurno();
        });

        setVisible(true); // mostra la finestra — sempre per ultimo
    }





    // aggiorna le barre HP e tutto il pannello sinistro 
    private void aggiornaBarreHP() {

        // aggiorna etichetta e barra HP del player
        labelHpPlayer.setText(player.nome + " HP: " + Math.max(player.hp, 0));
        barraPlayer.setMaximum(hp_del_personaggio);
        barraPlayer.setValue(Math.max(player.hp, 0));
        barraPlayer.setString(Math.max(player.hp, 0) + " / " + hp_del_personaggio);

        // aggiorna etichetta e barra HP del nemico
        labelHpNemico.setText(nemicoAttuale.nome + " HP: " + Math.max(nemicoAttuale.hp, 0));
        barraNemico.setMaximum(hp_del_NEMICO);
        barraNemico.setValue(Math.max(nemicoAttuale.hp, 0));
        barraNemico.setString(Math.max(nemicoAttuale.hp, 0) + " / " + hp_del_NEMICO);

        

    
        // fase attuale della partita
        String fase;
        if (bossFight) {
            fase = "Boss finale";
        } else {
            fase = "Nemico " + (indiceNemico + 1) + " / " + mondo.nemici.length;
        }

        // aggiorna il testo del pannello sinistro 
        // usa <html> per andare a capo con <br> — legge i dati direttamente da Player.java e Nemico.java
        infoStats.setText("<html>"
            + "— Personaggio —<br>"
            + "Classe: " + player.classe + "<br>"          // da Player.java
            + "Arma: " + player.arma + "<br>"              // da Player.java
            + "Forza: " + player.forza +"<br>"
            + "Difesa: " + player.difesa + "<br>"  // da Player.java
            + "<br>"
            + "— Oggetti —<br>"
            + "Pozioni HP: " + player.pozioniHp + "<br>"  // da Player.java
            + "Pozioni MP: " + player.pozioniMp + "<br>"   // da Player.java
            + "<br>"
            + "Mondo: " + mondo.nome + "<br>"
            + "Fase: " + fase + "<br>"
            + "Turno: " + turno
            + "</html>");

        // porta lo scroll del pannello_centrale sempre in fondo
        pannello_centrale.setCaretPosition(pannello_centrale.getDocument().getLength());
    }



    // controlla se il player è morto, altrimenti avanza il turno ──
    private void fineTurno() {
        if (player.hp <= 0) {
            // player morto: termina la partita e disabilita i bottoni
            attivo = false;
            pannello_centrale.append("GAME OVER!\n");
            Bottone_ATTACCA.setEnabled(false);
            bottone_HP.setEnabled(false);
            bottone_MP.setEnabled(false);
        } else {
            // player vivo: passa al turno successivo
            turno++;
        }
        aggiornaBarreHP();
        pannello_centrale.setCaretPosition(pannello_centrale.getDocument().getLength());
    }






    // passa al nemico successivo, al boss, oppure dichiara vittoria 
    private void nemicoSconfitto() {
        nemicoAttuale.hp = 0;
        pannello_centrale.append(nemicoAttuale.nome + " sconfitto!\n");

        // se era il boss, la partita è finita con vittoria
        if (bossFight) {
            attivo = false;
            pannello_centrale.append("MONDO COMPLETATO!\n");
            Bottone_ATTACCA.setEnabled(false);
            bottone_HP.setEnabled(false);
            bottone_MP.setEnabled(false);
            aggiornaBarreHP();
            return;
        }

        // assegna il drop usando il metodo di Combattimento.java
        pannello_centrale.append(Combattimento.assegnaDrop(player, random));

        // passa al nemico successivo nell'array di Mondo.java
        indiceNemico++;
        if (indiceNemico < mondo.nemici.length) {
            // ci sono ancora nemici normali
            nemicoAttuale = mondo.nemici[indiceNemico];
            pannello_centrale.append("Nuovo nemico: " + nemicoAttuale.nome + "\n");
        } else {
            // tutti i nemici sconfitti: carica il boss da Mondo.java
            bossFight = true;
            nemicoAttuale = mondo.boss;
            pannello_centrale.append("BOSS FINALE: " + nemicoAttuale.nome + "\n");
        }

        // aggiorna il massimo HP del nuovo nemico/boss e resetta il turno
        hp_del_NEMICO = nemicoAttuale.hp;
        turno  = 1;
        aggiornaBarreHP();
    }



    public static void main(String[] args) {
        new interfacciaGrafica();
    }
}