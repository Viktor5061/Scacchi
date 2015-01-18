package scacchi;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

// Classe dei pezzi mangiati non piu presenti sulla scacchiera
public class Mangiati extends JPanel implements ImageObserver{
    
    private ImageObserver obs;
    private float k;
    private Pezzo pB[], pN[];
    private Posizione[] posB = new Posizione[ 15 ];
    private Posizione[] posN = new Posizione[ 15 ];
    
    // Genera il pannello e le poszioni che i pezzi possono assumervi
    public Mangiati( Pezzo pezB[], Pezzo pezN[], float coeff ){
        
        super();
        pB = pezB;
        pN = pezN;
        k = coeff;
        setBackground( Color.darkGray );
        
        for( int i = 0; i < 8; i++ ){
            
            posB[ i ] = new Posizione( 0, 7 - i, ( float )( k / 1.3 ) );
            posN[ i ] = new Posizione( 3, 7 - i, ( float )( k / 1.3 ) );
        
        } // Fine For
        
        for( int i = 8; i < 15; i++ ){
            
            posB[ i ] = new Posizione( 1, 15 - i, ( float )( k / 1.3 ) );
            posN[ i ] = new Posizione( 4, 15 - i, ( float )( k / 1.3 ) );
        
        } // Fine For
    
    } // Fine Mangiati
    
    // Disegna i pezzi che sono stati mangiati
    @Override
    public void paintComponent( Graphics g ){
        
        super.paintComponent( g );
        g.setColor( new Color( 236, 220, 166 ) );
        g.setFont( new Font( "Times New Roman", 2, ( int )( 14 * k ) ) );
        g.drawString( "Pezzi Mangiati", ( int )( 80 * k ), ( int )( 15 * k ) );
        
        int j = 0, m = 0;
        
        for( int i = 14; i >= 0; i-- ){
            
            if( pB[ i ].mangiato ){
            
                g.drawImage( pB[ i ].imm,posB[ j ].px,posB[ j ].py, ( int )( pB[ i ].imm.getWidth( obs ) * k / 1.1), ( int )(pB[ i ].imm.getHeight( obs ) * k / 1.1 ), null);
                j++;
            
            } // Fine If
            
            if( pN[ i ].mangiato ){
            
                g.drawImage( pN[ i ].imm,posN[ m ].px,posN[ m ].py, ( int )( pN[ i ].imm.getWidth( obs ) * k / 1.1 ), ( int )( pN[ i ].imm.getHeight( obs ) * k / 1.1), null);
                m++;
            
            } // Fine If
        
        } // Fine For
    
    } // Fine paintComponents

} // Fine Classe Mangiati