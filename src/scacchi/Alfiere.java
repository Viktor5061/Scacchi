package scacchi;

import java.awt.*;

// La classe alfiere eredita dalla classe pezzo
// Descrive gli attribuiti e i metodi di un alfiere sulla scacchiera
public class Alfiere extends Pezzo{
    
    public Alfiere( int casellaOccupata, Image imm, Posizione pos ){
        
        super( "Alfiere", casellaOccupata, 3, imm, pos );
    
    } // Fine Costruttore Alfiere
    
    @Override
    public Posizione[] mossePermesse( Posizione pos[][] ){
        
        int i = 0;
        int j = 0;
        
        // Prima Diagonale
        do{
            
            j++;
            
            if( mossaPermessa( p.coordX + j, p.coordY + j, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX + j ][ p.coordY + j ];
                i++;
                
            } // Fine If
        
        } while( mossaPermessa( p.coordX + j, p.coordY + j, pos ) && pos[ p.coordX + j ][ p.coordY + j ].casellaOccupata == -1 ); // Fine Do While
        
        j = 0;
	
        // Seconda Diagonale
        do{
            
            j++;
            
            if( mossaPermessa( p.coordX - j, p.coordY - j, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX - j ][ p.coordY - j ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa( p.coordX - j, p.coordY - j, pos ) && pos[ p.coordX - j ][ p.coordY - j ].casellaOccupata == -1 ); // Fine Do While
        
        j = 0;
        
        // Terza Diagonale
        do{
            
            j++;
            
            if( mossaPermessa( p.coordX + j, p.coordY - j, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX + j ][ p.coordY - j ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa( p.coordX + j, p.coordY - j, pos ) && pos[ p.coordX + j ][ p.coordY - j ].casellaOccupata == -1 ); // Fine Do While
        
        j = 0;
        
        // Quarta Diagonale
        do{
            
            j++;
            
            if( mossaPermessa( p.coordX - j, p.coordY + j, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX - j ][ p.coordY + j ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa( p.coordX - j, p.coordY + j, pos ) && pos[ p.coordX - j ][ p.coordY + j ].casellaOccupata == -1 );
        
        impostaMossa[ i ] = null;
        return impostaMossa;
    
    } // Fine mossePermesse
    
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){
        
        return null;
    
    } // Fine mossePermesseRe

} // Fine Classe Alfiere