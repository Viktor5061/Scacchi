package scacchi;

import java.awt.*;

// La classe alfiere eredita dalla classe pezzo
// Descrive gli attribuiti e i metodi di un alfiere sulla scacchiera
public class Alfiere extends Pezzo{
    
    public Alfiere( int cp, Image imm, Posizione p ){
        
        super( "alfiere", cp, 3, imm, p );
    
    } // Fine Costruttore Alfiere
    
    @Override
    public Posizione[] mossePermesse( Posizione pos[][] ){
        
        int i = 0;
        int j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx + j, p.cy + j, pos ) ){
                
                permpos[ i ] = pos[ p.cx + j ][ p.cy + j ];
                i++;
                
            } // Fine If
        
        } while( isPermit( p.cx + j, p.cy + j, pos ) && pos[ p.cx + j ][ p.cy + j ].occupata == -1 ); // Fine Do While
        
        j = 0;
	
        do{
            
            j++;
            
            if( isPermit( p.cx - j, p.cy - j, pos ) ){
                
                permpos[ i ] = pos[ p.cx - j ][ p.cy - j ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx - j, p.cy - j, pos ) && pos[ p.cx - j ][ p.cy - j ].occupata == -1 ); // Fine Do While
        
        j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx + j, p.cy - j, pos ) ){
                
                permpos[ i ] = pos[ p.cx + j ][ p.cy - j ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx + j, p.cy - j, pos ) && pos[ p.cx + j ][ p.cy - j ].occupata == -1 ); // Fine Do While
        
        j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx - j, p.cy + j, pos ) ){
                
                permpos[ i ] = pos[ p.cx - j ][ p.cy + j ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx - j, p.cy + j, pos ) && pos[ p.cx - j ][ p.cy + j ].occupata == -1 ); // Fine Do While
        
        permpos[ i ] = null;
        return permpos;
    
    } // Fine mossePermesse
    
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){ return null; }

} // Fine Classe Alfiere