package scacchi;

// Classe che contiene i metodi per verificare le condizioni di scacco e scacco matto
public class VerificaScacco{
    
    private Scacchiera scacchiera;
    private Mossa mossa;

    // Costruttore che riceve i riferimenti necessari per le invocazioni di metodi di altre classi
    public VerificaScacco( Scacchiera scacchiera, Mossa mossa ){
        
        this.scacchiera = scacchiera;
        this.mossa = mossa;
    
    } // Fine Costruttore VerificaScacco

    // Metodo che controlla se ce scacco matto
    // Prova tutte le mosse dell'avversario per vedere se il Re riesce a non rimanere sotto scacco
    public boolean verificaScaccoMatto( Pezzo pA[], Pezzo[] pT, Posizione pos[][], Lista rst ){
        
        boolean controllo = true;
        
        for( int i = 15; i >= 0 && controllo; i-- ){
            
            scacchiera.aggiorna( rst );
            Posizione[] mop = pA[ i ].mossePermesse( pos );
            Posizione tmp = pA[ i ].getPos();
            int j = 0;
            
            while( mop[ j ] != null ){
                
                int t = mossa.valuta( pA[ i ], pA[ i ].getPos(), mop[ j ], pA[ i ].colore, true );
                
                if( t != pA[ i ].colore ){
                    
                    controllo = false;
                
                } // Fine If
                
                j++;
            
            } // Fine While
        
        } // Fine For
        
        scacchiera.aggiorna( rst );
        return controllo;
    
    } // Fine verificaScaccoMatto

    // Controlla se il re e sotto scacco
    // Osserva se il re si trova in una delle mosse permesse dell'avversario
    public boolean verificaScacco( int x, int y, Posizione pos[][], Pezzo pA[] ){

        // Pedoni normali
        for( int i = 0; i < 8; i++ ){
            
            if( !pA[ i ].mangiato && !pA[ i ].promosso ){
                
                int j = 0;
                Posizione[] mop = pA[ i ].mossePermesseRe( pos );
                
                while( mop[ j ] != null ){
                    
                    if( mop[ j ] == pos[ x ][ y ] ){
                        
                        return false;
                    
                    } // Fine If
                    
                    j++;
                
                } // Fine While
            
            } // Fine If

            // Per i pedoni promossi
            if( !pA[ i ].mangiato && pA[ i ].promosso ){
                
                int j = 0;
                Posizione[] mop = pA[ i ].mossePermesse( pos );
                
                while( mop[ j ] != null ){
                    
                    if( mop[ j ] == pos[ x ][ y ] ){
                        
                        return false;
                    
                    } // Fine If
                    
                    j++;
                
                } // Fine While
            
            } // Fine If
        
        } // Fine For

        // Per il resto dei pezzi
        for( int i = 8; i < 16; i++ ){
            
            if( !pA[ i ].mangiato ){
                
                int j=0;
                Posizione[] mop = pA[ i ].mossePermesse( pos );
                
                while( mop[ j ] != null ){
                    
                    if( mop[ j ] == pos[ x ][ y ] ){
                        
                        return false;
                    
                    } // Fine If
                    
                    j++;
                
                } // Fine While
            
            } // Fine If
        
        } // Fine For
        
        return true;
    
    } // Fine verificaScacco

} // Fine Classe VerificaScacco