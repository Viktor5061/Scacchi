package scacchi;

import java.awt.*;

// La classe regina eredita dalla classe pezzo
// Descrive gli attribuiti e i metodi di una regina sulla scacchiera
public class Regina extends Pezzo{
    
    public Regina( int cp, Image imm, Posizione p ){
        
        super( "Regina", cp, 10, imm, p );
    
    } // Fine Costruttore Regina
    
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

        // 5a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( mossaPermessa( p.coordX + j, p.coordY + j, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX + j ][ p.coordY + j ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa( p.coordX + j, p.coordY + j, pos ) && pos[ p.coordX + j ][ p.coordY + j ].casellaOccupata == -1 );

        //6a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( mossaPermessa( p.coordX - j, p.coordY - j, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX - j ][ p.coordY - j ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa( p.coordX - j, p.coordY - j, pos ) && pos[ p.coordX - j ][ p.coordY - j ].casellaOccupata == -1 );

        // 7a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( mossaPermessa( p.coordX + j, p.coordY - j, pos ) ){
                
                impostaMossa[ i ] = pos[ p.coordX + j ][ p.coordY - j ];
                i++;
            
            } // Fine If
        
        } while( mossaPermessa( p.coordX + j, p.coordY - j, pos ) && pos[ p.coordX + j ][ p.coordY - j ].casellaOccupata == -1 );

        // 8a direzione
        j = 0;
        
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
    public Posizione[] mossePermesseRe(Posizione pos[][]){
        
        return null;
    
    } // Fine mossePermesseRe

} // Fine Classe Regina	