
// Contiene i dati di un mondo: nome, descrizione, nemici normali e boss finale.

public class Mondo {

   
    String nome;
    String lore;
    Nemico[] nemici;
    Boss boss;

    // Costruisce un mondo completo con tutti dati
    public Mondo(String nome, String lore, Nemico[] nemici, Boss boss){
        this.nome   = nome;
        this.lore   = lore;
        this.nemici = nemici;
        this.boss   = boss;
    }

    // DATi del mondo PYRAKOR.
    public static Mondo creaPyrakor(){

        // Nemici normali affrontati prima del boss.
        Nemico cavaliereCarbonizzato = new Nemico("Cavaliere Carbonizzato", 55,  9,  5,  6);
        Nemico serpenteLava          = new Nemico("Serpente di Lava",        45, 10,  3,  9);
        Nemico forgiaDemone          = new Nemico("Forgia-Demone",           60, 11,  6,  4);
        Nemico pipistrelloInc        = new Nemico("Pipistrello Incandescente",40,  7,  3, 11);
        Nemico golemOssidiana        = new Nemico("Golem d'Ossidiana",       75, 12,  9,  3);

        // Boss caricato quando tutti i nemici dell'array sono stati sconfitti.
        Boss tyrannos = new Boss("Tyrannos, Drago del Sole Nero", 120, 16, 10,  9);

        return new Mondo(
                "PYRAKOR",
                "Il Regno della Cenere Eterna. Vulcani vivi, pioggia di scintille e citta forgiate nel magma.",
                new Nemico[]{cavaliereCarbonizzato, serpenteLava, forgiaDemone, pipistrelloInc, golemOssidiana},
                tyrannos
        );
    }

    // dati del mondo THALASSYR
    public static Mondo creaThalassyr(){

       
        Nemico sirenaOscura      = new Nemico("Sirena Oscura",       50, 10,  4,  7);
        Nemico medusaElettrica   = new Nemico("Medusa Elettrica",    44, 11,  3,  9);
        Nemico guerrieroAnnegato = new Nemico("Guerriero Annegato",  65, 10,  8,  4);
        Nemico squaloCieco       = new Nemico("Squalo Cieco",        58, 12,  5,  7);
        Nemico coralloVivente    = new Nemico("Corallo Vivente",     70,  8, 10,  2);

        Boss monacoMaree = new Boss("Il Monaco delle Maree", 115, 14, 10, 11);

        return new Mondo(
                "THALASSYR",
                "Il Regno degli Abissi Cantanti. Oceani infiniti e templi sommersi.",
                new Nemico[]{sirenaOscura, medusaElettrica, guerrieroAnnegato, squaloCieco, coralloVivente},
                monacoMaree
        );
    }

    //mondo GRANMOR.
    public static Mondo creaGranmor(){

     
        Nemico vermiMarci      = new Nemico("Vermi Marci",              50,  7,  3,  4);
        Nemico lupiMuschio     = new Nemico("Lupi di Muschio",          55,  9,  4,  8);
        Nemico golemMinerale   = new Nemico("Golem Minerale",           80, 12, 10,  2);
        Nemico scarabeoCrist   = new Nemico("Scarabeo di Cristallo",    45,  8,  6,  9);
        Nemico fungoGigante    = new Nemico("Fungo Parassita Gigante",  65,  9,  8,  3);

    
        Boss rePietra = new Boss("Il Re di Pietra", 130, 13, 12,  4);

        return new Mondo(
                "GRANMOR",
                "Il Regno delle Montagne Vive. Foreste immense e rovine scolpite nella pietra.",
                new Nemico[]{vermiMarci, lupiMuschio, golemMinerale, scarabeoCrist, fungoGigante},
                rePietra
        );
    }

    //mondo AERATHON.
    public static Mondo creaAerathon(){

        
        Nemico arpiaCorazzata  = new Nemico("Arpia Corazzata",          55,  9,  5, 10);
        Nemico spiritoVento    = new Nemico("Spirito del Vento",        42, 11,  3, 12);
        Nemico mantaElettrica  = new Nemico("Manta Elettrica Volante",  50, 10,  4, 11);
        Nemico cavaliereAlato  = new Nemico("Cavaliere Alato",          65, 11,  7,  7);
        Nemico nuvolaVivente   = new Nemico("Nuvola Vivente",           75,  8,  8,  7);

        
        Boss raikarn = new Boss("Raikarn il Signore dei Fulmini", 118, 16,  9, 13);

        return new Mondo(
                "AERATHON",
                "Il Regno del Cielo Spezzato. Isole fluttuanti e tempeste eterne.",
                new Nemico[]{arpiaCorazzata, spiritoVento, mantaElettrica, cavaliereAlato, nuvolaVivente},
                raikarn
        );
    }

    //mondo NOCTYRIA.
    public static Mondo creaNoctyria(){


        Nemico cavaliereFantasma = new Nemico("Cavaliere Fantasma",      60, 10,  6,  7);
        Nemico angeloCorrotto    = new Nemico("Angelo Corrotto",         52, 10,  4,  9);
        Nemico ombraVivente      = new Nemico("Ombra Vivente",           42,  9,  3, 12);
        Nemico corvoCremisi      = new Nemico("Corvo Cremisi",           38,  8,  3, 13);
        Nemico mietitore         = new Nemico("Mietitore Senza Volto",   70, 11,  8,  8);

       
        Boss velkan = new Boss("Velkan il Re Eclissato", 135, 15, 10, 11);

        return new Mondo(
                "NOCTYRIA",
                "Il Regno del Sole Morente. Eclissi eterna e castelli neri.",
                new Nemico[]{cavaliereFantasma, angeloCorrotto, ombraVivente, corvoCremisi, mietitore},
                velkan
        );
    }
}
