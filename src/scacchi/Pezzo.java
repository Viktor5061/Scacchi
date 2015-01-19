package scacchi;

import java.awt.*;

public abstract class Pezzo {
    
    protected int or;
    protected boolean promosso = false; // X per l'arrivo in fondo del pedone
    protected boolean spostato = false; // Viene introdotto per re e torre -> arrocco
    protected boolean mangiato = false;
    protected String nome;
    protected int colore; // 1 Per Bianco, 0 Per Nero
    protected int valore;
    protected  Image imm;
    protected  Posizione p;
    protected  Posizione[] permpos = new Posizione[30]; // La Posizione Max è 27 Per La Regina
    
    public Pezzo( String n, int cp, int v, Image i, Posizione pos ){
        
        nome = n;
        colore = cp;
        valore = v;
        imm = i;
        p = pos;
        p.occupata = cp;
    
    } 
    
    public void setPos( Posizione pos ){
        
        p.occupata = -1;
        p = pos;
        p.occupata = colore;
    
    } // Fine setPos
    
    public Posizione getPos(){
        
        return p;
    
    } 

    // Metodo che restituisce un vettore delle posizioni che il pezzo puo assumere
    // Si tratta di un metodo specifico del pezzo che e in grado di descriverne il comportamento
    public abstract Posizione[] mossePermesse( Posizione pos[][] );

    // Metodo usato solo dai pedononi.
    // Deve essere implementato da tutte le classi figlie di Pezzo per non rimanere astratte
    public abstract Posizione[] mossePermesseRe( Posizione pos[][] );
    
    // Valuta Se Una Mossa E Permessa
    public boolean isPermit( int x, int y, Posizione pos[][] ){
        
        if( x < 0 || x > 7 ){
            
            return false;
        
        } // Fine If
        
        if( y < 0 || y > 7 ){
            
            return false;
        
        } // Fine If
        
        if( pos[ x ][ y ].occupata == colore ){
            
            return false;
        
        } // Fine If
        
        return true;
    
    } // Fine isPermit

} // Fine Classe Pezzo