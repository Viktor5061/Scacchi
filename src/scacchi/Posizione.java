package scacchi;

// Classe che identifica una posizione sulla scacchiera
public class Posizione {
    
    private int colorecasella; // 0 Se Nero, 1 Se Bianco
    protected int px; // Coordinate In Pixel
    protected int py; // Coordinate In Pixel
    protected int cx; // Coordinate Delle Caselle ( 0, 1 ... 7 )x
    protected int cy; // Coordinate Delle Caselle ( 0, 1 ... 7 )y
    protected int occupata = -1; // Vale -1 Se Libera, 0 Se Occupata Da Un Pezzo Nero, 1 Se Occupata Da Un Pezzo Bianco

    // Genera la posizione in pixel a partire dalle coordinate normali
    public Posizione( int x, int y, float k ){
        
        cx = x;
        cy = y;

        // Assegnamento della posizione in pixel calcolata in funzione della casella
        px = ( int )( ( 35 + ( x ) * ( 440 - 49 ) / 8 ) * 1.1 * k );
        py = ( int )( ( 30 + ( 7 - y ) * ( (438 - 46 ) / 8 ) ) * 1.1 * k );

        // Il colore della casella e nero se la somma degli indici e pari, bianco se dispari
        colorecasella = ( x + y ) % 2;
    
    } // Fine Costruttore Posizione

    // Metodo Che Restituisce Le Coordinate Secondo La Convenzione ( Esempio C7 )
    public String coord(){
        
        String ascissa = null;
        
        switch( cx ){
            
            case ( 0 ) : ascissa = "A"; break;
            case ( 1 ) : ascissa = "B"; break;
            case ( 2 ) : ascissa = "C"; break;
            case ( 3 ) : ascissa = "D" ; break;
            case ( 4 ) : ascissa = "E"; break;
            case ( 5 ) : ascissa = "F"; break;
            case ( 6 ) : ascissa = "G"; break;
            case ( 7 ) : ascissa = "H"; break;
        
        } // Fine Switch
        
        int ordinata = cy + 1;
        return ascissa + ordinata;
    
    } // Fine Coord

} // Fine Classe Posizione