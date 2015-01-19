package scacchi;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

// La classe rappresenta la scacchiera concettuale
public class InterfacciaGrafica {
    
    private Pezzo [] pB = new Pezzo[16];
    private Pezzo [] pN = new Pezzo[16];
    private Posizione[][] p = new Posizione[8][8];
    private TerminaProgramma ter;
    private JFrame f;
    protected float dimensioneSchermo;

    // Il costruttore genera i pannelli della scacchiera
    // (board,mosse,pezzi mangiati), i pezzi e le posizioni
    public InterfacciaGrafica( Lista rst ){
        
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        dimensioneSchermo = (float)tk.getScreenSize().getWidth() / 1024;
            
        f = new JFrame( "Progetto Scacchi" );
        Container c = f.getContentPane();
        f.setDefaultCloseOperation( f.DO_NOTHING_ON_CLOSE );
            
        int turno = 1; // Creazione delle posizioni
            
        for( int j = 0; j < 8; j++ ){
                
            for( int i = 0; i < 8; i++ ){
                    
                p[ i ][ j ] = new Posizione( i, j, dimensioneSchermo );
                    
            }
                
        }
            
        // Recupero le immagini dei pezzi bianchi
        Image imm[] = new Image[ 12 ];
        try {
            
            imm[ 0 ] = ImageIO.read(getClass().getResource("img/BPedone.gif"));
            imm[ 1 ] = ImageIO.read(getClass().getResource("img/BTorre.gif"));
            imm[ 2 ] = ImageIO.read(getClass().getResource("img/BCavallo.gif"));
            imm[ 3 ] = ImageIO.read(getClass().getResource("img/BAlfiere.gif"));
            imm[ 4 ] = ImageIO.read(getClass().getResource("img/BRegina.gif"));
            imm[ 5 ] = ImageIO.read(getClass().getResource("img/BRe.gif"));
            
            // Recupero le immagini pezzi neri
            imm[ 6 ] = ImageIO.read(getClass().getResource("img/NPedone.gif"));
            imm[ 7 ] = ImageIO.read(getClass().getResource("img/NTorre.gif"));
            imm[ 8 ] = ImageIO.read(getClass().getResource("img/NCavallo.gif"));
            imm[ 9 ] = ImageIO.read(getClass().getResource("img/NAlfiere.gif"));
            imm[ 10 ] = ImageIO.read(getClass().getResource("img/NRegina.gif"));
            imm[ 11 ] = ImageIO.read(getClass().getResource("img/NRe.gif"));
        
        } catch (IOException ex) {
            Logger.getLogger(InterfacciaGrafica.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        // Creazione dei pedoni bianchi
        for( int i = 0; i < 8; i++ ){
                
            pB[ i ] = new Pedone( 1, imm[0], p[i][1], pN );
                
        }
            
        // Creazione dei pezzi bianchi, prima fila
        pB[ 8 ] = new Torre ( 1, imm[ 1 ], p[ 0 ][ 0 ] );
        pB[ 9 ] = new Torre ( 1, imm[ 1 ], p[ 7 ][ 0 ] );
        pB[ 10 ] = new Cavallo ( 1, imm[ 2 ], p[ 1 ][ 0 ] );
        pB[ 11 ] = new Cavallo ( 1, imm[ 2 ], p[ 6 ][ 0 ] );
        pB[ 12 ] = new Alfiere ( 1, imm[ 3 ], p[ 2 ][ 0 ] );
        pB[ 13 ] = new Alfiere ( 1, imm[ 3 ], p[ 5 ][ 0 ] );
        pB[ 14 ] = new Regina ( 1, imm[ 4 ], p[ 3 ][ 0 ] );
        pB[ 15 ] = new Re ( 1, imm[ 5 ], p[ 4 ][ 0 ], pN, pB );
            
            
        // Creazione dei pedoni neri
        for( int i = 0; i < 8; i++ ){
                
            pN[ i ]= new Pedone( 0, imm[ 6 ], p[ i ][ 6 ], pB );
                
        }
            
        // Creazione dei pezzi neri prima fila
        pN[ 8 ] = new Torre ( 0, imm[ 7 ], p[ 0 ][ 7 ] );
        pN[ 9 ] = new Torre ( 0, imm[ 7 ], p[ 7 ][ 7 ] );
        pN[ 10 ] = new Cavallo ( 0, imm[ 8 ], p[ 1 ][ 7 ] );
        pN[ 11 ] = new Cavallo ( 0, imm[ 8 ], p[ 6 ][ 7 ] );
        pN[ 12 ] = new Alfiere ( 0, imm[ 9 ], p[ 2 ][ 7 ] );
        pN[ 13 ] = new Alfiere ( 0, imm[ 9 ], p[ 5 ][ 7 ] );
        pN[ 14 ] = new Regina ( 0, imm[ 10 ], p[ 3 ][ 7 ] );
        pN[ 15 ] = new Re ( 0, imm[ 11 ], p[ 4 ][ 7 ], pB, pN );
            
        // Creo il pannello dei pezzi mangiati
        Mangiati mang = new Mangiati( pB, pN, dimensioneSchermo );
        mang.setBounds( ( int )( 495 * dimensioneSchermo ) + 8, 8, ( int )( 250 * dimensioneSchermo ), ( int )( 352 * dimensioneSchermo ) );
        c.add( mang );
            
        // Pannello con le mosse effettuate dall'utente
        Mossa mos = new Mossa( dimensioneSchermo, p, pB, pN, mang, f, rst );
        mos.setPointer( mos );
        mos.setBounds( ( int )( 495 * dimensioneSchermo ) + 8, 8 + ( int )( 360 * dimensioneSchermo ), ( int )( 250 * dimensioneSchermo ), ( int )( 120 * dimensioneSchermo ) );
        c.add( mos );
            
        // Creazione della scacchiera
        Scacchiera b = new Scacchiera( p, dimensioneSchermo, pB, pN, mos, turno );
        b.setBounds( 8, 8, ( int )( 486 * dimensioneSchermo ), ( int )( 484 * dimensioneSchermo ) );
        c.add( b );
        mos.b = b;
            
        // Inserisco dei spazi vuoti
        JPanel space = new JPanel();
        c.add( space );
            
        // Creo il mio menu personalizzato
        MyBar bar = new MyBar( mos, f, dimensioneSchermo, rst, b );
        f.setJMenuBar( bar );
            
        VerificaScacco ai = new VerificaScacco( b, mos );
        mos.ai = ai;
        b.ai = ai;
            
        f.setBounds( 5, 5, ( int )( 8 + 770 * dimensioneSchermo ), ( int )( 80 + 485 * dimensioneSchermo ) );
            
        // Aggiungo un ascoltatore per poter chiudere la finestra dell'applicazione
        f.addWindowListener( new TerminaProgramma( f.getSize() ) );
            
        f.setVisible( true );
        
    
    } // Fine interfacciaGrafica

} // Fine Classe interfacciaGrafica
		