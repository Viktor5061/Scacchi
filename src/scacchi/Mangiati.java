package scacchi;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

// Classe dei pezzi mangiati non piu presenti sulla scacchiera
public class Mangiati extends JPanel implements ImageObserver{
    
    private ImageObserver obs;
    private float k;
    private Pezzo pezziBianchi[], pezziNeri[];
    private Posizione[] posizioneBianchi = new Posizione[ 15 ];
    private Posizione[] posizioneNeri = new Posizione[ 15 ];
    
    // Genera il pannello e le poszioni che i pezzi possono assumervi
    public Mangiati( Pezzo pezB[], Pezzo pezN[], float coeff ){
        
        super();
        pezziBianchi = pezB;
        pezziNeri = pezN;
        k = coeff;
        setBackground( Color.GRAY );
        
        for( int i = 0; i < 8; i++ ){
            
            posizioneBianchi[ i ] = new Posizione( 0, 7 - i, ( float )( k / 1.3 ) );
            posizioneNeri[ i ] = new Posizione( 3, 7 - i, ( float )( k / 1.3 ) );
        
        } // Fine For
        
        for( int i = 8; i < 15; i++ ){
            
            posizioneBianchi[ i ] = new Posizione( 1, 15 - i, ( float )( k / 1.3 ) );
            posizioneNeri[ i ] = new Posizione( 4, 15 - i, ( float )( k / 1.3 ) );
        
        } // Fine For
    
    } // Fine Mangiati
    
    // Disegna i pezzi che sono stati mangiati
    @Override
    public void paintComponent( Graphics g ){
        
        super.paintComponent( g );
        g.setColor( Color.RED );
        g.setFont( new Font( "Times New Roman", 2, ( int )( 14 * k ) ) );
        g.drawString( "Pezzi Mangiati", ( int )( 80 * k ), ( int )( 15 * k ) );
        
        int j = 0, m = 0;
        
        for( int i = 14; i >= 0; i-- ){
            
            if( pezziBianchi[ i ].mangiato ){
            
                g.drawImage(pezziBianchi[ i ].imm,posizioneBianchi[ j ].pixelX,posizioneBianchi[ j ].pixelY, ( int )( pezziBianchi[ i ].imm.getWidth( obs ) * k / 1.1), ( int )(pezziBianchi[ i ].imm.getHeight( obs ) * k / 1.1 ), null);
                j++;
            
            } // Fine If
            
            if( pezziNeri[ i ].mangiato ){
            
                g.drawImage(pezziNeri[ i ].imm,posizioneNeri[ m ].pixelX,posizioneNeri[ m ].pixelY, ( int )( pezziNeri[ i ].imm.getWidth( obs ) * k / 1.1 ), ( int )( pezziNeri[ i ].imm.getHeight( obs ) * k / 1.1), null);
                m++;
            
            } // Fine If
        
        } // Fine For
    
    } // Fine paintComponents

} // Fine Classe Mangiati