## Adventure Zone.

Adventure zone e' un gioco RPG single palyer diviso in 5 mondi con 6 nemici ciascuno, di cui l'ultimo e' il boss finale.
Il giocatore combattera' i nemici a turni completando ogni mondo.

---------

## Descrizione del gioco

il programma una volta avviato apre una semlice interfaccia grafica, ponendo all'utente la scelta il nome, la classe del suo personaggio e il mondo che vuole giocare.
Il giocatore premendo il bottone **INIZIA**, affrontera' 5 nemici comuni e il boss finale del mondo, tramite i tre bottoni **ATTACCA**, **POZIONE HP** o **POZIONE MP**.
Una volta sconfitto il mondo verra' completato e il personaggio potra' scegliere se riaffrontarlo o iniziare un nuovo mondo. 
Il combattimento viene effettuato tramite un dado che va da 1 a 20 e che esegue dei tiri randomici.
In base al numero del dado si avranno dei danni extra effettuati o nessun danno effettuato.

----------

## Le 3 classi

| Classe    | HP  | Punti di forza                           |
|-----------|-----|------------------------------------------|
| Guerriero | 160 | Alta difesa, danno costante              |
| Mago      | 145 | Alta fortuna, tanti colpi critici        |
| Ladro     | 145 | Alta agilità, schiva spesso gli attacchi |

---

## I 5 mondi

| Mondo     | Tema                        | Boss finale                    |
|-----------|-----------------------------|--------------------------------|
| PYRAKOR   | Vulcani e lava              | Tyrannos, Drago del Sole Nero  |
| THALASSYR | Abissi oceanici             | Il Monaco delle Maree          |
| GRANMOR   | Montagne vive e rovine      | Il Re di Pietra                |
| AERATHON  | Isole fluttuanti e tempeste | Raikarn il Signore dei Fulmini |
| NOCTYRIA  | Eclissi eterna              | Velkan il Re Eclissato         |

---

## Il sistema di combattimento

Ogni turno viene tirato un **dado virtuale da 20**:

| Risultato dado | Effetto                                         |
|----------------|-------------------------------------------------|
| 1              | MISS — l'attacco fallisce                       |
| 2 – 19         | Danno normale: `dado + Forza/2 - Difesa nemico` |
| 20             | CRITICO — il danno viene raddoppiato            |

Anche la **Fortuna** del personaggio aumenta la probabilità di colpo critico.
L'**Agilità** permette di schivare gli attacchi nemici.

---

## Le 3 azioni disponibili

| Bottone    | Effetto                                                |
|------------|--------------------------------------------------------|
| ATTACCA    | Tira il dado e infligge danno al nemico                |
| POZIONE HP | Recupera +50 HP — il nemico attacca comunque           |
| POZIONE MP | +15 Forza permanente, poi attacca subito con il bonus  |

---

## Drop dopo ogni nemico

Dopo ogni nemico sconfitto il gioco genera un oggetto casuale:

- **40%** — nessun oggetto
- **25%** — Pozione HP
- **20%** — Pozione MP
- **15%** — Arma casuale con bonus permanente (+10 Forza, +10 Arcano o Agilità)

---
## Struttura del progetto


- interfacciaGrafica.java   — finestra principale Swing con tutta la logica UI
- Combattimento.java        — motore del gioco: attacchi, pozioni, drop
- Player.java               — il personaggio con tutte le statistiche
- Nemico.java               — i nemici con HP, forza, difesa, agilità
- Boss.java                 — estende Nemico, usato per i boss finali
- Mondo.java                — i 5 mondi con i loro nemici e boss


---

## Tecnologie usate

- **Java** — linguaggio principale
- **Java Swing** — interfaccia grafica (JFrame, JPanel, JButton, JProgressBar, JTextArea)
- **SecureRandom** — generazione casuale sicura per il dado
- **OOP** — ereditarietà: `Boss extends Nemico`

---

## Autore

Progetto scolastico sviluppato da Luca Filippini e Andrea Fiumi.
