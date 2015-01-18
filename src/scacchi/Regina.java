package scacchi;

import java.awt.*;

// La classe regina eredita dalla classe pezzo
// Descrive gli attribuiti e i metodi di una regina sulla scacchiera
public class Regina extends Pezzo{
    
    public Regina( int cp, Image imm, Posizione p ){
        
        super( "regina", cp, 10, imm, p );
    
    } // Fine Costruttore Regina
    
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

        // 5a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx + j, p.cy + j, pos ) ){
                
                permpos[ i ] = pos[ p.cx + j ][ p.cy + j ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx + j, p.cy + j, pos ) && pos[ p.cx + j ][ p.cy + j ].occupata == -1 );

        //6a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx - j, p.cy - j, pos ) ){
                
                permpos[ i ] = pos[ p.cx - j ][ p.cy - j ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx - j, p.cy - j, pos ) && pos[ p.cx - j ][ p.cy - j ].occupata == -1 );

        // 7a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx + j, p.cy - j, pos ) ){
                
                permpos[ i ] = pos[ p.cx + j ][ p.cy - j ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx + j, p.cy - j, pos ) && pos[ p.cx + j ][ p.cy - j ].occupata == -1 );

        // 8a direzione
        j = 0;
        
        do{
            
            j++;
            
            if( isPermit( p.cx - j, p.cy + j, pos ) ){
                
                permpos[ i ] = pos[ p.cx - j ][ p.cy + j ];
                i++;
            
            } // Fine If
        
        } while( isPermit( p.cx - j, p.cy + j, pos ) && pos[ p.cx - j ][ p.cy + j ].occupata == -1 );
        
        permpos[ i ] = null;
        return permpos;
    
    } // Fine mossePermesse
    
    @Override
    public Posizione[] mossePermesseRe(Posizione pos[][]){
        
        return null;
    
    } // Fine mossePermesseRe

} // Fine Classe Regina	