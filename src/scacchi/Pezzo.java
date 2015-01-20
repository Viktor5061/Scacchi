package scacchi;

import java.awt.*;

public abstract class Pezzo {
    
    protected int or;
    protected boolean promosso = false; // X per l'arrivo in fondo del pedone
    protected boolean spostato = false; // Viene introdotto per re e torre -> arrocco
    protected boolean mangiato = false; // Variabile Booleana Per Sapere Se Un Pezzo E Stato Mangiato
    protected String nome;
    protected int colore; // 1 Per Bianco, 0 Per Nero
    protected int valore;
    protected  Image imm;
    protected  Posizione p;
    protected  Posizione[] impostaMossa = new Posizione[30]; // La Posizione Max E 27 Per La Regina
    
    public Pezzo( String nome, int cheColore, int valore, Image imm, Posizione pos ){
        
        this.nome = nome;
        this.colore = cheColore;
        this.valore = valore;
        this.imm = imm;
        p = pos;
        p.casellaOccupata = cheColore;
    
    } // Fine Costruttore Pezzo

    // Valuta Se Una Mossa E Permessa
    public boolean mossaPermessa( int orizzontale, int verticale, Posizione pos[][] ){
        
        if( orizzontale < 0 || orizzontale > 7 ){
            
            return false;
        
        } // Fine If
        
        if( verticale < 0 || verticale > 7 ){
            
            return false;
        
        } // Fine If
        
        if( pos[ orizzontale ][ verticale ].casellaOccupata == colore ){
            
            return false;
        
        } // Fine If
        
        return true;
    
    } // Fine mossaPermessa
    
    public void setPos( Posizione pos ){
        
        p.casellaOccupata = -1;
        p = pos;
        p.casellaOccupata = colore;
    
    } // Fine setPos
    
    public Posizione getPos(){
        
        return p;
    
    } // Fine getPos

    // Metodo che restituisce un vettore delle posizioni che il pezzo puo assumere
    // Si tratta di un metodo specifico del pezzo che e in grado di descriverne il comportamento
    public abstract Posizione[] mossePermesse( Posizione pos[][] );

    // Metodo usato solo dai pedononi.
    // Deve essere implementato da tutte le classi figlie di Pezzo per non rimanere astratte
    public abstract Posizione[] mossePermesseRe( Posizione pos[][] );

} // Fine Classe Pezzo