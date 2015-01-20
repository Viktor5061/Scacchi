package scacchi;

import java.awt.*;

// La classe cavallo eredita dalla classe pezzo.
// Descrive gli attribuiti e i metodi di un cavallo sulla scacchiera
public class Cavallo extends Pezzo{
    
    public Cavallo( int casellaOccupata, Image imm, Posizione pos ){
        
        super( "Cavallo", casellaOccupata, 3, imm, pos );
    
    } // Fine Costruttore Cavallo
    
    @Override
    public Posizione[] mossePermesse( Posizione pos[][] ){
        
        int i = 0;
        
        // Due In Alto E Una A Destra
        if( mossaPermessa( p.coordX + 2, p.coordY + 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX + 2][ p.coordY + 1 ];
            i++;
        
        } // Fine If
	
        // Due In Alto E Una A Sinistra
        if( mossaPermessa( p.coordX + 1, p.coordY + 2, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX + 1 ][ p.coordY + 2 ];
            i++;
        
        } // Fine If
        
        // Una In Alto E Due A Destra
        if( mossaPermessa( p.coordX - 1, p.coordY + 2, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX - 1 ][ p.coordY + 2 ];
            i++;
        
        } // Fine If
        
        // Una In Alto E Due A Sinistra
        if( mossaPermessa( p.coordX - 2, p.coordY + 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX - 2][ p.coordY + 1 ]; 
            i++;
        
        } // Fine If
        
        // Due In Basso E Una A Destra
        if( mossaPermessa( p.coordX - 2, p.coordY - 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX - 2][ p.coordY - 1 ]; 
            i++;
        
        } // Fine If
        
        // Due In Basso E Una A Sinistra
        if( mossaPermessa( p.coordX - 1, p.coordY - 2, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX - 1 ][ p.coordY - 2 ];
            i++;
        
        } // Fine If
        
        // Una In Basso E Due A Destra
        if( mossaPermessa( p.coordX + 1, p.coordY - 2, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX + 1 ][ p.coordY - 2 ]; 
            i++;
        
        } // Fine If
        
        // Una In Basso E Due A Sinistra
        if( mossaPermessa( p.coordX + 2, p.coordY - 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX + 2 ][ p.coordY - 1 ]; 
            i++;
        
        } // Fine If
        
        impostaMossa[ i ] = null;
        return impostaMossa;
    
    } // Fine mossePermesse
    
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){
        
        return null;
    
    } // Fine mossePermesseRe

} // Fine Classe Cavallo