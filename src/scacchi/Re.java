package scacchi;

import java.awt.*;

// La classe re eredita dalla classe pezzo
// Descrive gli attribuiti e i metodi di un re sulla scacchiera
public class Re extends Pezzo{
    
    private Pezzo pA[], pP[];
    
    public Re( int cp, Image imm, Posizione p, Pezzo pAvv[], Pezzo pProp[] ){
        
        super( "Re", cp, 100, imm, p );
        pA = pAvv;
        pP = pProp;
    
    } // Fine Costruttore Re
    
    @Override
    public Posizione[] mossePermesse( Posizione pos[][] ){
        
        int i = 0;
        
        if( mossaPermessa(p.coordX + 1, p.coordY, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX + 1 ][ p.coordY ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa( p.coordX + 1, p.coordY + 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX + 1 ][ p.coordY + 1 ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa(p.coordX, p.coordY + 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX ][ p.coordY + 1 ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa( p.coordX - 1, p.coordY + 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX - 1 ][ p.coordY + 1 ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa(p.coordX - 1, p.coordY, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX - 1 ][ p.coordY ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa( p.coordX - 1, p.coordY - 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX - 1 ][ p.coordY - 1 ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa(p.coordX, p.coordY - 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX ][ p.coordY - 1 ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa( p.coordX + 1, p.coordY - 1, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX + 1 ][ p.coordY - 1 ];
            i++;
        
        } // Fine If
        
        int k;
        
        if( colore == 1 ){
            
            k = 0;
        
        } else {
            
            k = 7;
        
        } // Fine If Else
        
        if ( p == pos[ 4 ][ k ] && !spostato ){
            
            if( !pP[ 9 ].spostato && mossaPermessa( 5, k, pos ) && mossaPermessa( 6, k, pos ) ){
                
                impostaMossa[ i ] = pos[ 6 ][ k ];
                i++;
            
            } // Fine If
            
            if( !pP[ 8 ].spostato && mossaPermessa( 3, k, pos ) && mossaPermessa( 2, k, pos ) ){
                
                impostaMossa[ i ] = pos[ 2 ][ k ];
                i++;
            
            } // Fine If
        
        } // Fine If

        // Per l'arrocco lo spostamento della torre � gestito da Mossa
        impostaMossa[ i ] = null;
        return impostaMossa;
    
    } // Fine mossePermesse
    
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){
        
        return null;
    
    } // Fine mossePermesseRe

} // Fine Classe Re	