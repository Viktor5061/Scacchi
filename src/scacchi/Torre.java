package scacchi;

import java.awt.*;

// La classe torre eredita dalla classe pezzo.
// Descrive gli attribuiti e i metodi di una torre sulla scacchiera.
public class Torre extends Pezzo{
    
    public Torre( int cp, Image imm, Posizione p ){
        
        super( "torre", cp, 5, imm, p );
    
    } // Fine Costruttore Torre
    
    @Override
    public Posizione[] mossePermesse( Posizione pos[][] ){
        
        int i = 0;

        // 1a direzione
        int j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx + j, p.cy, pos ) ){
                
                permpos[ i ] = pos[ p.cx + j ][ p.cy ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx + j, p.cy, pos ) && pos[ p.cx + j ][ p.cy ].occupata == -1 );

        // 2a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx - j, p.cy, pos ) ){
                
                permpos[ i ] = pos[ p.cx - j ][ p.cy ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx - j, p.cy, pos ) && pos[ p.cx - j ][ p.cy ].occupata == -1 );

        // 3a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx, p.cy + j, pos ) ){
                
                permpos[ i ] = pos[ p.cx ][ p.cy + j ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx, p.cy + j, pos ) && pos[ p.cx ][ p.cy + j ].occupata == -1 );

        // 4a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx, p.cy - j, pos ) ){
                
                permpos[ i ] = pos[ p.cx ][ p.cy - j ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx, p.cy - j, pos ) && pos[ p.cx ][ p.cy - j ].occupata == -1 );
        
        permpos[ i ] = null;
        return permpos;
    
    } // Fine mossePermesse
    
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){
        
        return null;
    
    } // Fine mossePermesseRe

} // Fine Classe Torre	