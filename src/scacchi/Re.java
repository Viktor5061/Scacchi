package scacchi;

import java.awt.*;

// La classe re eredita dalla classe pezzo
// Descrive gli attribuiti e i metodi di un re sulla scacchiera
public class Re extends Pezzo{
    
    private Pezzo pA[], pP[];
    
    public Re( int cp, Image imm, Posizione p, Pezzo pAvv[], Pezzo pProp[] ){
        
        super( "re", cp, 100, imm, p );
        pA = pAvv;
        pP = pProp;
    
    } // Fine Costruttore Re
    
    @Override
    public Posizione[] mossePermesse( Posizione pos[][] ){
        
        int i = 0;
        
        if( isPermit( p.cx + 1, p.cy, pos ) ){
            
            permpos[ i ] = pos[ p.cx + 1 ][ p.cy ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx + 1, p.cy + 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx + 1 ][ p.cy + 1 ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx, p.cy + 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx ][ p.cy + 1 ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx - 1, p.cy + 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx - 1 ][ p.cy + 1 ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx - 1, p.cy, pos ) ){
            
            permpos[ i ] = pos[ p.cx - 1 ][ p.cy ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx - 1, p.cy - 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx - 1 ][ p.cy - 1 ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx, p.cy - 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx ][ p.cy - 1 ];
            i++;
        
        } // Fine If
        
        if( isPermit( p.cx + 1, p.cy - 1, pos ) ){
            
            permpos[ i ] = pos[ p.cx + 1 ][ p.cy - 1 ];
            i++;
        
        } // Fine If
        
        int k;
        
        if( colore == 1 ){
            
            k = 0;
        
        } else {
            
            k = 7;
        
        } // Fine If Else
        
        if ( p == pos[ 4 ][ k ] && !spostato ){
            
            if( !pP[ 9 ].spostato && isPermit( 5, k, pos ) && isPermit( 6, k, pos ) ){
                
                permpos[ i ] = pos[ 6 ][ k ];
                i++;
            
            } // Fine If
            
            if( !pP[ 8 ].spostato && isPermit( 3, k, pos ) && isPermit( 2, k, pos ) ){
                
                permpos[ i ] = pos[ 2 ][ k ];
                i++;
            
            } // Fine If
        
        } // Fine If

        // Per l'arrocco lo spostamento della torre � gestito da Mossa
        permpos[ i ] = null;
        return permpos;
    
    } // Fine mossePermesse
    
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){
        
        return null;
    
    } // Fine mossePermesseRe

} // Fine Classe Re	