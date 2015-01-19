package scacchi;

import java.io.*;

// Rappresenta le caratteristiche di una situazione della partita
// Di fatti una situazione � l'insieme dei caratteri di una mossa
public class Situazione implements Serializable{
    
    protected String peNome;
    protected int peColore;
    protected int turnoIniz, or;
    protected int posIcx, posIcy;
    protected int posFcx, posFcy;
    protected String lastmove;
    protected String prom;

    // Salva negli attributi le caratteristiche della mossa
    public Situazione( Pezzo pe, int g, Posizione posI, Posizione posF, int turnoIniz, String pr ){
        
        this.turnoIniz = turnoIniz;
        prom = pr;
        peColore = pe.colore;
        peNome = "" + pe.nome;
        posIcx = posI.cx;
        posIcy = posI.cy;
        posFcx = posF.cx;
        posFcy = posF.cy;
        or = g;
        lastmove = posI.coord() + " -> " + posF.coord();
    
    } // Fine Costruttore Situazione

    // Ritorno la stringa che rappresenta la mossa
    @Override
    public String toString(){
        
        if( turnoIniz == 0 ){
            
            return "NERO: "+lastmove + '\n';
        
        } else {
            
            return "BIANCO: "+lastmove + "\n";
        
        } 
    
    } 

} 