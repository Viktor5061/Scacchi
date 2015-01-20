package scacchi;

import java.awt.*;
// La classe pedone eredita dalla classe pezzo.
// Descrive gli attribuiti e i metodi di un pedone sulla scacchiera
public class Pedone extends Pezzo{
    
    private Pezzo pA[];
    private int i, s, t, u;
    
    public Pedone( int cp, Image imm, Posizione p, Pezzo[] pAvv ){
        
        super( "Pedone", cp, 1, imm, p );
        pA = pAvv;
    
    } // Fine Costruttore Pedone
    
    @Override
    public Posizione[] mossePermesse( Posizione pos[][] ){

        // Il pedone puo solo muovere in avanti
        // Devo distinguere le mosse a seconda del colore
        i = 0;
        
        if( colore == 1 ){
            
            s = 1;
            t = 1;
            u = 4;
        
        } else {
            
            s = -1;
            t = 6;
            u = 3;
        
        } // Fine If Else
        
        if( mossaPermessa(p.coordX, p.coordY + s, pos ) && pos[ p.coordX ][ p.coordY + s ].casellaOccupata == -1 ){
            
            impostaMossa[ i ] = pos[ p.coordX ][ p.coordY + s ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa( p.coordX + s, p.coordY + s, pos ) && pos[ p.coordX + s ][ p.coordY + s ].casellaOccupata != -1 ){
            
            impostaMossa[ i ] = pos[ p.coordX + s ][ p.coordY + s ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa( p.coordX - s, p.coordY + s, pos ) && pos[ p.coordX - s ][ p.coordY + s ].casellaOccupata != -1 ){
            
            impostaMossa[ i ] = pos[ p.coordX - s ][ p.coordY + s ];
            i++;
        
        } // Fine If
        
        if( p.coordY == t && mossaPermessa(p.coordX, p.coordY + s, pos ) && pos[ p.coordX ][ p.coordY + s ].casellaOccupata == -1){
            
            if( mossaPermessa(p.coordX, p.coordY + 2 * s, pos ) && pos[ p.coordX ][ p.coordY + 2 * s ].casellaOccupata == -1 ){
                
                impostaMossa[ i ] = pos[ p.coordX ][ p.coordY + 2 * s ];
                i++;
            
            } // Fine If
        
        } // Fine If
        
        // Controllo per l'en passant
        if( p.coordY == u ){

            // En passant destro
            isEnPassant( s, pos );
            // En passant sinistro
            isEnPassant( -s, pos );
        
        }

        // L'en passant(controllo mossa precedente del pedone avversario) e l'arrivo in fondo sono gestiti da Mossa
        impostaMossa[ i ] = null;
        return impostaMossa;
    
    } // Fine MossePermese
    
    private void isEnPassant( int z, Posizione pos[][] ){
        
        if( mossaPermessa( p.coordX + z, p.coordY + s, pos ) ){
            
            for( int k = 0; k < 8; k++ ){
                
                if( !pA[ k ].mangiato && !pA[ k ].promosso && pA[ k ].p == pos[ p.coordX + z ][ p.coordY ] ){
                    
                    impostaMossa[ i ] = pos[ p.coordX + z ][ p.coordY + s ];
                    i++;
                    break;
                
                } // Fine If
            
            } // Fine For
        
        } // Fine If
    
    } // Fine isEnPassant
    
    // Produce il vettore delle mosse permesse limitato alle sole posizioni in cui puo mangiare.
    // Serve per considerare le situazioni di scacco, infatti il pedone e l'unico pezzo 
    // che si muove e mangia in due maniere diverse: se quindi il re richiede le mosse
    // permesse del pedone interessano solo quelle in cui il pedone puo mangiare e non le altre
    @Override
    public Posizione[] mossePermesseRe( Posizione pos[][] ){
        
        i = 0;
        
        if( colore == 1 ) s=1;
        else s = -1;
        
        if( mossaPermessa( p.coordX + s, p.coordY + s, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX + s ][ p.coordY + s ];
            i++;
        
        } // Fine If
        
        if( mossaPermessa( p.coordX - s, p.coordY + s, pos ) ){
            
            impostaMossa[ i ] = pos[ p.coordX - s ][ p.coordY + s ];
            i++;
        
        } // Fine If
        
        impostaMossa[ i ] = null;
        return impostaMossa;
    
    } // Fine mossePermesseRe

} // Fine Classe Pedone	