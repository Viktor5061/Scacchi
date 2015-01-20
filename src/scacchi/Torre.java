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
            
            if( mossaPermessa(p.coordX + j, p.coordY, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX + j ][ p.coordY ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa(p.coordX + j, p.coordY, pos ) && pos[ p.coordX + j ][ p.coordY ].casellaOccupata == -1 );

        // 2a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( mossaPermessa(p.coordX - j, p.coordY, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX - j ][ p.coordY ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa(p.coordX - j, p.coordY, pos ) && pos[ p.coordX - j ][ p.coordY ].casellaOccupata == -1 );

        // 3a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( mossaPermessa(p.coordX, p.coordY + j, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX ][ p.coordY + j ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa(p.coordX, p.coordY + j, pos ) && pos[ p.coordX ][ p.coordY + j ].casellaOccupata == -1 );

        // 4a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( mossaPermessa(p.coordX, p.coordY - j, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX ][ p.coordY - j ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa(p.coordX, p.coordY - j, pos ) && pos[ p.coordX ][ p.coordY - j ].casellaOccupata == -1 );
        
        impostaMossa[ i ] = null;
        return impostaMossa;
    
    } // Fine mossePermesse
    
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){
        
        return null;
    
    } // Fine mossePermesseRe

} // Fine Classe Torre	