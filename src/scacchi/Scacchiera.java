package scacchi;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
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
    
    private Pezzo[] pezziBianchi;
    private Pezzo[] pezziNeri;
    private Mossa mossa;
    private Pezzo prox;
    
    private int x, y;
    
    private long tm;
    private double a = 400;
    protected VerificaScacco ai;

    // Il costruttore recupera i riferimenti necessari e le immagini che devono essere dipinte
    public Scacchiera( Posizione[][] pos, float dimensioneSchermo, Pezzo[] pezB, Pezzo[] pezN, Mossa m, int t ){
        
        super();
        k = dimensioneSchermo;
        pezziBianchi = pezB;
        pezziNeri = pezN;
        p = pos;
        mossa = m;
        turno = t;

        // Recupero l'immagine della scacchiera
        try {
            board = ImageIO.read( getClass().getResource( "img/Scacchiera.jpg" ) );
        } catch ( IOException ex ) {}

        addMouseListener( this );
    
    } 

    // Disegna la scacchiera, i pezzi e le i simboli alfanumerici per identificare delle caselle
    @Override
    public void paintComponent( Graphics g ){
        
        super.paintComponent(g);

        // Disegno la scacchiera
        g.drawImage( board, 0, 0, ( int )( 485 * k ), ( int )( 485 * k ), null );
        
        // Disegno i pezzi
        for( int i = 0; i < 16; i++ ){
            
            if( !pezziBianchi[ i ].mangiato ){
                
                g.drawImage(pezziBianchi[ i ].imm, pezziBianchi[ i ].getPos().pixelX, pezziBianchi[ i ].getPos().pixelY, ( int )( pezziBianchi[ i ].imm.getWidth( obs ) * k ), ( int )( pezziBianchi[ i ].imm.getHeight( obs ) * k ), null );
            
            } // Fine If
            
            if( !pezziNeri[ i ].mangiato ){
                
                g.drawImage(pezziNeri[ i ].imm, pezziNeri[ i ].getPos().pixelX, pezziNeri[ i ].getPos().pixelY, ( int )( pezziNeri[ i ].imm.getWidth( obs ) * k ), ( int )( pezziNeri[ i ].imm.getHeight( obs ) * k ), null );
            
            } // Fine If

            // Aggiungo un'immagine temporanea che viene disegnata per ultima
            // Quando si muove il pezzo, che altrimenti scorrerebbe sotto a quelli disegnati successivamente
            if( contr == 0 ){

                g.drawImage(tmpimg, temp.pixelX, temp.pixelY, ( int )( tmpimg.getWidth( obs ) * k ), ( int )( tmpimg.getHeight( obs ) * k ), null);
            
            } // Fine If
        
        } // Fine For
    
    } // Fine paintComponent

    // Trova il pezzo piu vicino al punto in cui si e premuto il mouse
    @Override
    public void mousePressed( MouseEvent e ){
        
        addMouseMotionListener(this);
        double b;

        // Sposto il punto in modo che il mouse punti sul pezzo e non sull'angolo in alto a sinistra
        x = e.getX() - ( int )( 20 * k );
        y = e.getY() - ( int )( 25 * k );

        // Se tocca al bianco si puo prendere solo un pezzo bianco
        if( turno == 1 ){
            
            prox = pezziBianchi[ 15 ]; // Sono sicuro che il re non viene mai mangiato
            
            for( int i = 14; i >= 0; i--){
                
                if( !pezziBianchi[ i ].mangiato ){ // Calcolo le distanze
                    
                    a = Math.pow( prox.getPos().pixelX - x, 2 ) + Math.pow( prox.getPos().pixelY - y, 2 );
                    b = Math.pow( pezziBianchi[ i ].getPos().pixelX - x, 2 ) + Math.pow( pezziBianchi[ i ].getPos().pixelY - y, 2 );
                    
                    if( a > b ){
                        
                        a = b;
                        prox = pezziBianchi[ i ];
                    
                    } // Se a != b il re non si puo prendere
                
                } // Fine If
            
            } // Fine For
        
        } // Se tocca al nero si puo prendere solo un pezzo nero
        
        if( turno == 0 ){
            
            prox = pezziNeri[ 15 ];
            
            for( int i = 14; i >= 0; i-- ){
                
                if( !pezziNeri[ i ].mangiato ){
                    
                    a = Math.pow( prox.getPos().pixelX - x, 2 ) + Math.pow( prox.getPos().pixelY - y, 2 );
                    b = Math.pow( pezziNeri[ i ].getPos().pixelX - x, 2 ) + Math.pow( pezziNeri[ i ].getPos().pixelY - y, 2 );
                    
                    if( a > b ){
                        
                        a = b;
                        prox = pezziNeri[ i ];
                    
                    } // Fine If
                
                } // Fine If
            
            } // Fine For
        
        } // Fine If
        
        if( a < 400 ){
            
            posin = prox.getPos();
            temp.pixelX = x;
            temp.pixelY = y;
            prox.setPos( temp );
            tmpimg = prox.imm;
            contr++;
            repaint( tm, x - 30, y - 30, 100, 100 );
        
        } // Fine If
    
    } // Fine mousePressed

    // Trova la casella piu vicina al punto in cui si è lascito il pezzo e controlla la mossa
    @Override
    public void mouseReleased( MouseEvent e ){
        
        if( a < 400 ){
            
            x = e.getX() - ( int )( 17 * k );
            y = e.getY() - ( int )( 25 * k );
            
            double b;
            Posizione best = p[ 0 ][ 0 ];
            
            for( int i = 0; i < 8; i++ ){
                
                for( int j = 0; j < 8; j++ ){
                    
                    a = Math.pow( best.pixelX - x, 2 ) + Math.pow( best.pixelY - y, 2 );
                    b = Math.pow( p[ i ][ j ].pixelX - x, 2 ) + Math.pow( p[ i ][ j ].pixelY - y, 2 );
                    
                    if( a > b ){
                        
                        best = p[ i ][ j ];
                    
                    } // Fine If
                
                } // Fine For
            
            } // Fine For
            
            turno = mossa.avanti( prox, posin, best, turno, false );
            repaint();
            // Finito lo spostamento la copia di prox.imm non serve piu, quindi impedisco che venga ridisegnata
            contr--;
            removeMouseMotionListener( this );
            repaint();
            e.consume(); // Dico che l'evento e consumato
        
        } // Fine If
    
    } // Fine mouseReleased

    // Aggiorno l'immagine del pezzo in movimento e dell'area di scacchiera che viene coperta
    @Override
    public void mouseDragged( MouseEvent e ){
        
        if( a < 400 ){ // Controlla che il pezzo sia stato preso
            
            temp.pixelX = e.getX() - ( int )( 17 * k );
            temp.pixelY = e.getY() -( int )( 25 * k );
            repaint( tm, temp.pixelX - 70, temp.pixelY - 70, 180, 190 );
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
        turno = 1;
        
        // Creo Le Posizioni
        for( int j = 0; j < 8; j++ ){
            
            for( int i = 0; i < 8; i++ ){
                
                p[ i ][ j ] = new Posizione( i, j, k );
            
            } // Fine For
        
        } // Fine For
        
        Image imm[] = new Image[ 12 ];

        // Creo i pedoni bianchi
        for( int i = 0; i < 8; i++ ){
            
            pezziBianchi[ i ] = new Pedone( 1, imm[ 0 ], p[ i ][ 1 ], pezziNeri );
        
        } // Fine For

        // Creazione dei pezzi bianchi
        pezziBianchi[ 8 ] = new Torre( 1, imm[ 1 ], p[ 0 ][ 0 ] );
        pezziBianchi[ 9 ] = new Torre( 1, imm[ 1 ], p[ 7 ][ 0 ] );
        pezziBianchi[ 10 ] = new Cavallo( 1, imm[ 2 ], p[ 1 ][ 0 ] );
        pezziBianchi[ 11 ] = new Cavallo( 1, imm[ 2 ], p[ 6 ][ 0 ] );
        pezziBianchi[ 12 ] = new Alfiere( 1, imm[ 3 ], p[ 2 ][ 0 ] );
        pezziBianchi[ 13 ] = new Alfiere( 1, imm[ 3 ], p[ 5 ][ 0 ] );
        pezziBianchi[ 14 ] = new Regina( 1, imm[ 4 ], p[ 3 ][ 0 ] );
        pezziBianchi[ 15 ] = new Re( 1, imm[ 5 ], p[ 4 ][ 0 ], pezziNeri, pezziBianchi );

        // Creo i pedoni neri
        for( int i = 0; i < 8; i++ ){
            
            pezziNeri[ i ] = new Pedone( 0, imm[ 6 ], p[ i ][ 6 ], pezziBianchi );
        
        } // Fine For

        // Creazione dei pezzi neri
        pezziNeri[ 8 ] = new Torre( 0, imm[ 7 ], p[ 0 ][ 7 ] );
        pezziNeri[ 9 ] = new Torre( 0, imm[ 7 ], p[ 7 ][ 7 ] );
        pezziNeri[ 10 ] = new Cavallo( 0, imm[ 8 ], p[ 1 ][ 7 ] );
        pezziNeri[ 11 ] = new Cavallo( 0, imm[ 8 ], p[ 6 ][ 7 ] );
        pezziNeri[ 12 ] = new Alfiere( 0, imm[ 9 ], p[ 2 ][ 7 ] );
        pezziNeri[ 13 ] = new Alfiere( 0, imm[ 9 ], p[ 5 ][ 7 ] );
        pezziNeri[ 14 ] = new Regina( 0, imm[ 10 ], p[ 3 ][ 7 ] );
        pezziNeri[ 15 ] = new Re( 0, imm[ 11 ], p[ 4 ][ 7 ], pezziBianchi, pezziNeri );
        
        mossa.ncm = false;
        Nodo i = rst.getNode();
        Pezzo pT[];
        
        // Rieseguo tutte le mosse
        while( i != null ){
            
            Situazione si = ( Situazione ) i.info;
            
            if( si.peColore == 1 ){
                
                pT = pezziBianchi;
            
            } else {
                
                pT = pezziNeri;
            
            } // Fine If Else
            
            turno = mossa.avanti(pT[ si.or ], p[ si.posIcx ][ si.posIcy ], p[ si.posFcx ][ si.posFcy ], si.turnoIniziale, false );
            i = i.next;
        
        } // Fine While
        
        mossa.aggiorna( rst, false, turno );
        mossa.ncm = true;
        repaint();
    
    } // Fine aggiorna

} // Fine Classe Scacchiera