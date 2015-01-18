package scacchi;

import java.awt.*;
// La classe pedone eredita dalla classe pezzo.
// Descrive gli attribuiti e i metodi di un pedone sulla scacchiera
public class Pedone extends Pezzo{
    
    private Pezzo pA[];
    private int i, s, t, u;
    
    public Pedone( int cp, Image imm, Posizione p, Pezzo[] pAvv ){
        
        super( "pedone", cp, 1, imm, p );
        pA = pAvv;
    
    } // Fine Costruttore Pedone
    
    @Override
    public Posizione[] mossePermesse( Posizione pos[][] ){

        // Il pedone puo solo muovere in avanti
        // Devo distinguere le mosse a seconda del colore
        i = 0;
        
        if( colore == 1 ){
            
            s = 1;
            t = 1;
            u = 4;
        
        } else {
            
            s = -1;
            t = 6;
            u = 3;
        
        } // Fine If Else
        
        if( isPermit( p.cx, p.cy + s, pos ) && pos[ p.cx ][ p.cy + s ].occupata == -1 ){
            
            permpos[ i ] = pos[ p.cx ][ p.cy + s ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx + s, p.cy + s, pos ) && pos[ p.cx + s ][ p.cy + s ].occupata != -1 ){
            
            permpos[ i ] = pos[ p.cx + s ][ p.cy + s ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx - s, p.cy + s, pos ) && pos[ p.cx - s ][ p.cy + s ].occupata != -1 ){
            
            permpos[ i ] = pos[ p.cx - s ][ p.cy + s ];
            i++;
        
        } // Fine If
        
        if( p.cy == t && isPermit( p.cx, p.cy + s, pos ) && pos[ p.cx ][ p.cy + s ].occupata == -1){
            
            if( isPermit( p.cx, p.cy + 2 * s, pos ) && pos[ p.cx ][ p.cy + 2 * s ].occupata == -1 ){
                
                permpos[ i ] = pos[ p.cx ][ p.cy + 2 * s ];
                i++;
            
            } // Fine If
        
        } // Fine If
        
        // Controllo per l'en passant
        if( p.cy == u ){

            // En passant destro
            isEnPassant( s, pos );
            // En passant sinistro
            isEnPassant( -s, pos );
        
        }

        // L'en passant(controllo mossa precedente del pedone avversario) e l'arrivo in fondo sono gestiti da Mossa
        permpos[ i ] = null;
        return permpos;
    
    } // Fine MossePermese
    
    private void isEnPassant( int z, Posizione pos[][] ){
        
        if( isPermit( p.cx + z, p.cy + s, pos ) ){
            
            for( int k = 0; k < 8; k++ ){
                
                if( !pA[ k ].mangiato && !pA[ k ].promosso && pA[ k ].p == pos[ p.cx + z ][ p.cy ] ){
                    
                    permpos[ i ] = pos[ p.cx + z ][ p.cy + s ];
                    i++;
                    break;
                
                } // Fine If
            
            } // Fine For
        
        } // Fine If
    
    } // Fine isEnPassant
    
    // Produce il vettore delle mosse permesse limitato alle sole posizioni in cui puo mangiare.
    // Serve per considerare le situazioni di scacco, infatti il pedone e l'unico pezzo 
    // che si muove e mangia in due maniere diverse: se quindi il re richiede le mosse
    // permesse del pedone interessano solo quelle in cui il pedone puo mangiare e non le altre
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){
        
        i = 0;
        
        if( colore == 1 ) s=1;
        else s = -1;
        
        if( isPermit( p.cx + s, p.cy + s, pos ) ){
            
            permpos[ i ] = pos[ p.cx + s ][ p.cy + s ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx - s, p.cy + s, pos ) ){
            
            permpos[ i ] = pos[ p.cx - s ][ p.cy + s ];
            i++;
        
        } // Fine If
        
        permpos[ i ] = null;
        return permpos;
    
    } // Fine mossePermesseRe

} // Fine Classe Pedone	