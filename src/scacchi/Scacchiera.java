package scacchi;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

// Contiene la grafica della scacchiera
public class Scacchiera extends JPanel implements  MouseListener, MouseMotionListener, ImageObserver{
    
    private ImageObserver obs;
    private Image board, tmpimg;
    protected int turno;
    private int contr = -1;
    private float k;
    
    private Posizione p[][];
    private Posizione temp = new Posizione( 0, 0, k );
    private Posizione posin;
    
    private Pezzo[] pB;
    private Pezzo[] pN;
    private Mossa mos;
    private Pezzo prox;
    
    private int x, y;
    private int wi, he;
    private int w[] = new int[16];
    private int h[] = new int[16];
    
    private long tm;
    private double a = 400;
    protected VerificaScacco ai;

    // Il costruttore recupera i riferimenti necessari e le immagini che devono essere dipinte
    public Scacchiera( Posizione[][] pos, float coeff, Pezzo[] pezB, Pezzo[] pezN, Mossa m, int t ){
        
        super();
        k = coeff;
        pB = pezB;
        pN = pezN;
        p = pos;
        mos = m;
        turno = t;

        // Recupero l'immagine della scacchiera
        //Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            board = ImageIO.read(getClass().getResource("img/board.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Scacchiera.class.getName()).log(Level.SEVERE, null, ex);
        }
        //board = tk.getImage( "scacchi.img/board.jpg" );        
        MediaTracker mt = new MediaTracker( this );
        mt.addImage( board, 0 );

        // Recupero le immagini dei pezzi
        for( int i = 0; i < 16; i++ ){
            
            mt.addImage( pB[ i ].imm, i + 1 );
            mt.addImage( pN[ i ].imm, i + 18 );
        
        } // Fine For
        
        try{
            
            mt.waitForAll();
        
        } catch( InterruptedException e ){} 
        
        addMouseListener( this );
    
    } 

    // Disegna la scacchiera, i pezzi e le i simboli alfanumerici per identificare delle caselle
    @Override
    public void paintComponent( Graphics g ){
        
        super.paintComponent(g);

        // Disegno la scacchiera
        g.drawImage( board, 0, 0, ( int )( 484 * k ), ( int )( 481.8 * k ), null );
        g.setColor( new Color( 236, 220, 166 ) );
        g.setFont( new Font( "Times New Roman", 0, ( int )( 14 * k ) ) );
        
        // Disegno le lettere delle caselle
        int p = 17;
        
        for( int i = 0; i < 2; i++ ){
            
            g.drawString( "A", ( int )( 50 * k ), ( int )( p * k ) );
            g.drawString( "B", ( int )( 104 * k ), ( int )( p * k ) );
            g.drawString( "C", ( int )( 158 * k ), ( int )( p * k ) );
            g.drawString( "D", ( int )( 212 * k ), ( int )( p * k ) );
            g.drawString( "E", ( int )( 266 * k ), ( int )( p * k ) );
            g.drawString( "F", ( int )( 320 * k ), ( int )( p * k ) );
            g.drawString( "G", ( int )( 374 * k ), ( int )( p * k ) );
            g.drawString( "H", ( int )( 428 * k ), ( int )( p * k ) );
            p = 473;
        
        } // Fine For

        // Disegno i numeri delle caselle
        p = 10;
        
        for( int i = 0; i < 2; i++ ){
            
            g.drawString( "8", ( int )( p * k ), ( int )( 54 * k ) );
            g.drawString( "7", ( int )( p * k ), ( int )( 108 * k ) );
            g.drawString( "6", ( int )( p * k ), ( int )( 162 * k ) );
            g.drawString( "5", ( int )( p * k ), ( int )( 216 * k ) );
            g.drawString( "4", ( int )( p * k ), ( int )( 270 * k ) );
            g.drawString( "3", ( int )( p * k ), ( int )( 324 * k ) );
            g.drawString( "2", ( int )( p * k ), ( int )( 378 * k ) );
            g.drawString( "1", ( int )( p * k ), ( int )( 432 * k ) );
            p = 468;
        
        } // Fine For

        // Disegno i pezzi
        for( int i = 0; i < 16; i++ ){
            
            if( !pB[ i ].mangiato ){
                
                g.drawImage( pB[ i ].imm, pB[ i ].getPos().px, pB[ i ].getPos().py, ( int )( pB[ i ].imm.getWidth( obs ) * k ), ( int )( pB[ i ].imm.getHeight( obs ) * k ), null );
            
            } // Fine If
            
            if( !pN[ i ].mangiato ){
                
                g.drawImage( pN[ i ].imm, pN[ i ].getPos().px, pN[ i ].getPos().py, ( int )( pN[ i ].imm.getWidth( obs ) * k ), ( int )( pN[ i ].imm.getHeight( obs ) * k ), null );
            
            } // Fine If

            // Aggiungo un'immagine temporanea che viene disegnata per ultima
            // Quando si muove il pezzo, che altrimenti scorrerebbe sotto a quelli disegnati successivamente
            if( contr == 0 ){
                
                g.drawImage( tmpimg, temp.px, temp.py, ( int )( tmpimg.getWidth( obs ) * k ), ( int )( tmpimg.getHeight( obs ) * k ), null);
            
            } // Fine If
        
        } // Fine For
    
    } // Fine paintComponent

    // Trova il pezzo piu vicino al punto in cui si e premuto il mouse
    @Override
    public void mousePressed( MouseEvent e ){
        
        addMouseMotionListener(this);
        double b;

        // Sposto il punto in modo che il mouse punti sul pezzo e non sull'angolo in alto a sinistra
        x = e.getX() - ( int )( 17 * k );
        y = e.getY() - ( int )( 25 * k );

        // Se tocca al bianco si puo prendere solo un pezzo bianco
        if( turno == 1 ){
            
            prox = pB[ 15 ]; // Sono sicuro che il re non viene mai mangiato
            
            for( int i = 14; i >= 0; i--){
                
                if( !pB[ i ].mangiato ){ // Calcolo le distanze
                    
                    a = Math.pow( prox.getPos().px - x, 2 ) + Math.pow( prox.getPos().py - y, 2 );
                    b = Math.pow( pB[ i ].getPos().px - x, 2 ) + Math.pow( pB[ i ].getPos().py - y, 2 );
                    
                    if( a > b ){
                        
                        a = b;
                        prox = pB[ i ];
                    
                    } // Se a != b il re non si puo prendere
                
                } // Fine If
            
            } // Fine For
        
        } // Se tocca al nero si puo prendere solo un pezzo nero
        
        if( turno == 0 ){
            
            prox = pN[ 15 ];
            
            for( int i = 14; i >= 0; i-- ){
                
                if( !pN[ i ].mangiato ){
                    
                    a = Math.pow( prox.getPos().px - x, 2 ) + Math.pow( prox.getPos().py - y, 2 );
                    b = Math.pow( pN[ i ].getPos().px - x, 2 ) + Math.pow( pN[ i ].getPos().py - y, 2 );
                    
                    if( a > b ){
                        
                        a = b;
                        prox = pN[ i ];
                    
                    } // Fine If
                
                } // Fine If
            
            } // Fine For
        
        } // Fine If
        
        if( a < 400 ){
            
            posin = prox.getPos();
            temp.px = x;
            temp.py = y;
            prox.setPos( temp );
            tmpimg = prox.imm;
            contr++;
            repaint( tm, x - 30, y - 30, 100, 100 );
        
        } // Fine If
    
    } // Fine mousePressed

    // Trova la casella piu vicina al punto in cui si e lascito il pezzo e controlla la mossa
    @Override
    public void mouseReleased( MouseEvent e ){
        
        if( a < 400 ){
            
            x = e.getX() - ( int )( 17 * k );
            y = e.getY() - ( int )( 25 * k );
            
            double b;
            Posizione best = p[ 0 ][ 0 ];
            
            for( int i = 0; i < 8; i++ ){
                
                for( int j = 0; j < 8; j++ ){
                    
                    a = Math.pow( best.px - x, 2 ) + Math.pow( best.py - y, 2 );
                    b = Math.pow( p[ i ][ j ].px - x, 2 ) + Math.pow( p[ i ][ j ].py - y, 2 );
                    
                    if( a > b ){
                        
                        best = p[ i ][ j ];
                    
                    } // Fine If
                
                } // Fine For
            
            } // Fine For
            
            turno = mos.avanti( prox, posin, best, turno, false );
            repaint();
            // Finito lo spostamento la copia di prox.imm non serve piu, quindi impedisco che venga ridisegnata
            contr--;
            removeMouseMotionListener( this );
            repaint();
            e.consume(); // Dico che l'evento e consumato

            // if( turno == 0 ){
            // turno = -1;
            // turno = ai2.generaMossa( turno );
            // }
        
        } // Fine If
    
    } // Fine mouseReleased

    // Aggiorno l'immagine del pezzo in movimento e dell'area di scacchiera che viene coperta
    @Override
    public void mouseDragged( MouseEvent e ){
        
        if( a < 400 ){ // Controlla che il pezzo sia stato preso
            
            temp.px = e.getX() - ( int )( 17 * k );
            temp.py = e.getY() -( int )( 25 * k );
            repaint( tm, temp.px - 70, temp.py - 70, 180, 190 );
            e.consume(); // Dico che l'evento e consumato
        
        } // Fine If
    
    } // Fine mouseDragged

    // Forse Mi Serviranno
    @Override
    public void mouseClicked( MouseEvent e ){}
    @Override
    public void mouseEntered( MouseEvent e ){}
    @Override
    public void mouseExited( MouseEvent e ){}
    @Override
    public void mouseMoved( MouseEvent e ){}

    // Aggiorna i pezzi sulla scacchiera
    // Prelevando le mosse dentro la lista passata vengono eseguite tutte le mosse dentro quella lista
    public void aggiorna( Lista r ){
        
        Lista rst = r.clona();
        r.removeAll();
        Toolkit tk = Toolkit.getDefaultToolkit();
        turno = 1;
        
        // Creo Le Posizioni
        for( int j = 0; j < 8; j++ ){
            
            for( int i = 0; i < 8; i++ ){
                
                p[ i ][ j ] = new Posizione( i, j, k );
            
            } // Fine For
        
        } // Fine For
        
        Image imm[] = new Image[ 12 ];

        // Recupero le immagini dei pezzi bianchi
        /*
        imm[ 0 ] = tk.getImage( "scacchi.img/BPedone.gif" );
        imm[ 1 ] = tk.getImage( "scacchi.img/BTorre.gif" );
        imm[ 2 ] = tk.getImage( "scacchi.img/BCavallo.gif" );
        imm[ 3 ] = tk.getImage( "scacchi.img/BAlfiere.gif" );
        imm[ 4 ] = tk.getImage( "scacchi.img/BRegina.gif" );
        imm[ 5 ] = tk.getImage( "scacchi.img/BRe.gif" );

        // Recupero le immagini dei pezzi neri
        imm[ 6 ] = tk.getImage( "scacchi.img/NPedone.gif" );
        imm[ 7 ] = tk.getImage( "scacchi.img/NTorre.gif" );
        imm[ 8 ] = tk.getImage( "scacchi.img/NCavallo.gif" );
        imm[ 9 ] = tk.getImage( "scacchi.img/NAlfiere.gif" );
        imm[ 10 ] = tk.getImage( "scacchi.img/NRegina.gif" );
        imm[ 11 ] = tk.getImage( "scacchi.img/NRe.gif" );
        */
        // Creo i pedoni bianchi
        for( int i = 0; i < 8; i++ ){
            
            pB[ i ] = new Pedone( 1, imm[ 0 ], p[ i ][ 1 ], pN );
        
        } // Fine For

        // Creazione dei pezzi bianchi
        pB[ 8 ] = new Torre( 1, imm[ 1 ], p[ 0 ][ 0 ] );
        pB[ 9 ] = new Torre( 1, imm[ 1 ], p[ 7 ][ 0 ] );
        pB[ 10 ] = new Cavallo( 1, imm[ 2 ], p[ 1 ][ 0 ] );
        pB[ 11 ] = new Cavallo( 1, imm[ 2 ], p[ 6 ][ 0 ] );
        pB[ 12 ] = new Alfiere( 1, imm[ 3 ], p[ 2 ][ 0 ] );
        pB[ 13 ] = new Alfiere( 1, imm[ 3 ], p[ 5 ][ 0 ] );
        pB[ 14 ] = new Regina( 1, imm[ 4 ], p[ 3 ][ 0 ] );
        pB[ 15 ] = new Re( 1, imm[ 5 ], p[ 4 ][ 0 ], pN, pB );

        // Creo i pedoni neri
        for( int i = 0; i < 8; i++ ){
            
            pN[ i ] = new Pedone( 0, imm[ 6 ], p[ i ][ 6 ], pB );
        
        } // Fine For

        // Creazione dei pezzi neri
        pN[ 8 ] = new Torre( 0, imm[ 7 ], p[ 0 ][ 7 ] );
        pN[ 9 ] = new Torre( 0, imm[ 7 ], p[ 7 ][ 7 ] );
        pN[ 10 ] = new Cavallo( 0, imm[ 8 ], p[ 1 ][ 7 ] );
        pN[ 11 ] = new Cavallo( 0, imm[ 8 ], p[ 6 ][ 7 ] );
        pN[ 12 ] = new Alfiere( 0, imm[ 9 ], p[ 2 ][ 7 ] );
        pN[ 13 ] = new Alfiere( 0, imm[ 9 ], p[ 5 ][ 7 ] );
        pN[ 14 ] = new Regina( 0, imm[ 10 ], p[ 3 ][ 7 ] );
        pN[ 15 ] = new Re( 0, imm[ 11 ], p[ 4 ][ 7 ], pB, pN );
        
        mos.ncm = false;
        Nodo i = rst.getNode();
        Pezzo pT[];
        
        // Rieseguo tutte le mosse
        while( i != null ){
            
            Situazione si = ( Situazione ) i.info;
            
            if( si.peColore == 1 ){
                
                pT = pB;
            
            } else {
                
                pT = pN;
            
            } // Fine If Else
            
            turno = mos.avanti( pT[ si.or ], p[ si.posIcx ][ si.posIcy ], p[ si.posFcx ][ si.posFcy ], si.turnoIniz, false );
            i = i.next;
        
        } // Fine While
        
        mos.aggiorna( rst, false, turno );
        mos.ncm = true;
        repaint();
    
    } // Fine aggiorna

} // Fine Classe Scacchiera