package scacchi;

import java.io.*;

// Rappresenta le caratteristiche di una situazione della partita
// Di fatti una situazione e l'insieme dei caratteri di una mossa
public class Situazione implements Serializable{
    
    protected String peNome;
    protected int peColore;
    protected int turnoIniziale, or;
    protected int posIcx, posIcy;
    protected int posFcx, posFcy;
    protected String lastmove;
    protected String prom;

    // Salva negli attributi le caratteristiche della mossa
    public Situazione( Pezzo pezzo, int g, Posizione posizioneIniziale, Posizione posizioneFinale, int turnoIniziale, String pr ){
        
        this.turnoIniziale = turnoIniziale;
        prom = pr;
        peColore = pezzo.colore;
        peNome = "" + pezzo.nome;
        posIcx = posizioneIniziale.coordX;
        posIcy = posizioneIniziale.coordY;
        posFcx = posizioneFinale.coordX;
        posFcy = posizioneFinale.coordY;
        or = g;
        lastmove = posizioneIniziale.coord() + " -> " + posizioneFinale.coord();
    
    } // Fine Costruttore Situazione

    // Ritorno la stringa che rappresenta la mossa
    @Override
    public String toString(){
        
        if( turnoIniziale == 0 ){
            
            return "Nero: " + lastmove + '\n';
        
        } else {
            
            return "Bianco: " + lastmove + "\n";
        
        } // Fine If Else
    
    } // Fine toString

} // Fine Classe Situazione