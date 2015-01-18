package scacchi;

import java.awt.*;

// La classe cavallo eredita dalla classe pezzo.
// Descrive gli attribuiti e i metodi di un cavallo sulla scacchiera
public class Cavallo extends Pezzo{
    
    public Cavallo( int cp, Image imm, Posizione p ){
        
        super( "cavallo", cp, 3, imm, p );
    
    } // Fine Costruttore Cavallo
    
    @Override
    public Posizione[] mossePermesse( Posizione pos[][] ){
        
        int i = 0;
        
        if( isPermit( p.cx + 2, p.cy + 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx + 2][ p.cy + 1 ];
            i++;
        
        } // Fine If
	
        if( isPermit( p.cx + 1, p.cy + 2, pos ) ){
            
            permpos[ i ] = pos[ p.cx + 1 ][ p.cy + 2 ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx - 1, p.cy + 2, pos ) ){
            
            permpos[ i ] = pos[ p.cx - 1 ][ p.cy + 2 ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx - 2, p.cy + 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx - 2][ p.cy + 1 ]; 
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx - 2, p.cy - 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx - 2][ p.cy - 1 ]; 
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx - 1, p.cy - 2, pos ) ){
            
            permpos[ i ] = pos[ p.cx - 1 ][ p.cy - 2 ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx + 1, p.cy - 2, pos ) ){
            
            permpos[ i ] = pos[ p.cx + 1 ][ p.cy - 2 ]; 
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx + 2, p.cy - 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx + 2 ][ p.cy - 1 ]; 
            i++;
        
        } // Fine If
        
        permpos[ i ] = null;
        return permpos;
    
    } // Fine mossePermesse
    
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){
        
        return null;
    
    } // Fine mossePermesseRe

} // Fine Classe Cavallo