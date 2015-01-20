package scacchi;

import java.awt.*;
import javax.swing.*;

// Classe che gestisce il controllo sulle mosse e il pannello delle mosse
public class Mossa extends JPanel{
    
    protected boolean ncm = true; // Variabile di controllo per gli aggiornamenti
    private float k;
    private Posizione pos[][], mp[];
    private Pezzo pB[], pN[];
    private Mangiati mang;
    protected JTextArea mosse;
    private JTextField messaggi, notifiche;
    private JScrollPane scr;
    private JFrame s;
    private Mossa m;
    protected Lista rst;
    protected Scacchiera b;
    private Promozione p;
    protected VerificaScacco ai;
    protected boolean intell = false;//variabile di controllo per la gestione della promozione

    // Il costruttore genera il pannello delle mosse e i campi di testo per le comunicazioni all'utente
    public Mossa( float coef, Posizione p[][], Pezzo pezB[], Pezzo pezN[], Mangiati ma, JFrame sc, Lista rst ){
        
        setBackground( Color.darkGray );
        k = coef;
        pos = p;
        pB = pezB;
        pN = pezN;
        mang = ma;
        s = sc;
        this.rst = rst;
        messaggi = new JTextField( 40 );
        messaggi.setEditable( false );
        messaggi.setText( "Mossa 1: Tocca Al Bianco" );
        notifiche = new JTextField( 40 );
        notifiche.setEditable( false );
        add( messaggi );
        add( notifiche );
        notifiche.setBackground( Color.ORANGE );
        mosse = new JTextArea( 10, 40 );
        mosse.setEditable( false );
        messaggi.setBackground( Color.YELLOW );
        scr=new JScrollPane( mosse );
        scr.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        add( scr );
    
    } // Fine Costruttore Mossa
    
    // Gestisce l'avanzamento di un pezzo sulla scacchiera
    public int avanti( Pezzo pe, Posizione posI, Posizione posF, int turnoIniz, boolean prom ){

        // Genero gli ordinamenti dei pezzi (servono per la lista)
        for( int i = 0; i < 16; i++ ){
        
            pN[ i ].or = i;
            pB[ i ].or = i;
        
        } //Fine For
        
        Pezzo pT[];
        int turno; // Valuto se la mossa e possibile
        
        if( !prom ){
        
            turno = valuta( pe, posI, posF, turnoIniz, false );
        
        } else {
            
            if (turnoIniz==1){
                
                turno=0;
            
            } else {
                
                turno =1;
            
            } // Fine If Else
        
        } // Fine If Else

        // Quello che segue va fatto se la mossa è stata effettuata
        if( turnoIniz != turno ){
            
            if( !prom ){
                
                String pr = "";
                
                if ( p != null ){
                    
                    pr = p.opt;
                
                } // Fine If
                
                if( pe.colore == 1 ){
                    
                    pT = pB;
                
                } else {
                    
                    pT=pN;
                
                } // Inserisco la mossa nella lista
                
                Situazione sit = new Situazione( pe, pe.or, posI, posF, turnoIniz,pr );
                rst.inserisci( sit );
            
            } // Fine If
            
            aggiorna( rst, prom, turno );
        
        } // Fine If
        
        return turno;
    
    } // Fine avanti

    // Valta se la mossa con le caratteristiche passate puo essere effettuata
    // Controlla scacco, arrocco, promozione pedone, en passant
    public int valuta( Pezzo pe, Posizione posI, Posizione posF, int turno, boolean virtual ){
        
        Pezzo pT[];
        
        if( !virtual ){
            
            notifiche.setBackground( Color.ORANGE );
            notifiche.setText( "Mossa Non Consentita, Riprova!!!" );
        
        } // Fine If
        
        pe.setPos( posI ); // Necessario perche il pezzo ha ancora la pos.temp per il mouse
        int j = 0;

        // Recupero il vettore dei pezzi di chi gioca
        Pezzo pA[];
        
        if( pe.colore == 1 ){
            
            pT = pB; 
            pA = pN;
        
        } else {
            
            pT = pN;
            pA = pB;
        
        } // Controllo che la mossa sia lecita
        
        mp = pe.mossePermesse( pos );
        
        while( mp[ j ] != null ){

            // Controllo inoltre che posF non sia quella di un re: non si mangia
            if( mp[ j ] == posF && mp[ j ] != pN[ 15 ].getPos() && mp[ j ] != pB[ 15 ].getPos() ){
                
                int tmpo = posF.casellaOccupata;
                pe.setPos( posF );
                
                if( !virtual ){
                    
                    notifiche.setText( "" );
                
                } // Fine If

                // Se alla fine due pezzi di colore != hanno posizioni = la mossa e
                // Comprende anche l'eliminazione dalla scacchiera del pezzo avversario
                boolean hamang = false; // Per en passant
                int i = 15;
                
                for( int h = 0; h < 15; h++ ){
                    
                    if ( !pA[ h ].mangiato && pe.getPos() == pA[ h ].getPos() ){
                        
                        pA[ h ].mangiato = true;
                        hamang = true;
                        i = h;
                    
                    } // Fine If
                
                } // Fine For

                // En Passant
                int s;
                
                if( pe.colore == 1 ){
                    
                    s = 1;
                
                } else {
                    
                    s = -1;
                
                } // Fine If Else
                
                boolean isep = false;
                
                for( int k = 0; k < 8; k++ ){
                    
                    if( pT[ k ] == pe ){
                        
                        isep = true;
                    
                    } // Fine If
                
                } // Fine For
                
                if( isep && !hamang && ( posF.coordX == posI.coordX + s && posF.coordY == posI.coordY + s || posF.coordX == posI.coordX - s && posF.coordY == posI.coordY + s ) ){
                    
                    if( !rst.isEmpty() ){
                        
                        Situazione sit = ( Situazione )rst.tell();
                        // Controllo se la mossa precedente e quella necessaria per permettere
                        // L'en passant
                        if( pos[ sit.posIcx ][ sit.posIcy - s ] != pe.getPos() ){
                            
                            pe.setPos( posI );
                            posF.casellaOccupata = tmpo;
                            
                            if( !virtual ){
                                
                                notifiche.setText( "Mossa Non Consentita, Riprova!!!" );
                            
                            } // Fine If
                        
                        } else {
                            
                            pA[ sit.or ].mangiato = true;
                        
                        } // Fine If Else
                    
                    } // Fine If
                
                } // Fine If
                
                if( !ai.verificaScacco(pT[ 15 ].getPos().coordX, pT[ 15 ].getPos().coordY,pos, pA ) ){
                    
                    pe.setPos( posI );
                    posF.casellaOccupata = tmpo;
                    pA[ i ].mangiato = false;
                    
                    if( !virtual ){
                        
                        notifiche.setText( "La Mossa Effettuata Lascia Il Re Sotto Scacco !!!" );
                    
                    } // Fine If
                
                } // Fine If
            
            } // Fine If
            
            j++;
        
        } // Fine While
        
        if( pe.getPos() != posI ){
            
            pe.spostato = true;
            
            // Considero i casi particolari:
            // ARROCCO
            if( pe == pT[ 15 ] && Math.abs( posI.coordX - posF.coordX ) == 2 ){
                
                int r;
                
                if ( pe.colore == 1 ){
                    
                    r = 0;
                
                } else {
                    
                    r = 7;
                
                } // Fine If Else

                if(  posI.coordX - posF.coordY < 0 ){
                    
                    pT[ 9 ].setPos( pos[ 5 ][ r ]);
                    pT[ 9 ].spostato = true;
                
                } else {
                    
                    pT[ 8 ].setPos( pos[ 3 ][ r ]);
                    pT[ 8 ].spostato = true;
                
                } // Fine If Else
            
            } // Fine If

            // Promozione Pedone
            if( pe.getPos().coordY == 7 || pe.getPos().coordY == 0 ){
                
                for( int i = 0; i < 8; i++ ){
                    
                    if( pT[ i ] == pe && !pe.promosso ){
                        
                        if( ncm ){
                            
                            if( virtual ){
                                
                                intell = true;
                                
                                for( int q = 1; q < 5; q++ ){
                                    
                                    p = new Promozione( s, pe, pB, pN, m, turno, rst, intell );
                                    p.op2 = q;
                                
                                } // Fine For
                                
                                intell = false;
                            
                            } else {
                                
                                p = new Promozione( s, pe, pB, pN, m, turno, rst, intell );
                            
                            } // Fine If Else
                        
                        } else {
                            
                            p.promote( ( ( Situazione ) ( rst.tell() ) ).prom );
                        
                        } // Fine If Else
                    
                    } // Fine If
                
                } // Fine For
            
            } // Fine If

            // Se il pezzo non e stato spostato di fatto non cambia turno
            if( turno == 1 ){
                
                turno=0;
            
            } else {
                
                turno = 1;
            
            } // Fine If Else
        
        } // Fine If
        
        return turno;
    
    } // Fine valuta

    // Aggiorna il pannello delle mosse e delle comunicazioni
    public void aggiorna( Lista rst, boolean prom, int turno ){
        
        Pezzo pA[], pT[];
        
        if( turno == 0 ){
            
            pT = pB;
            pA = pN;
        
        } else {
            
            pT = pN;
            pA = pB;
        
        } // Fine If Else
        
        String achi;
        
        if( turno == 0 ){
            
            achi = "Nero";
        
        } else {
            
            achi = "Bianco";
        
        } // Fine If Else
        
        if( !prom ){

            // Scrivo sull'area di testo la mossa effettuata
            mosse.setText( rst.toString() );
            // Scrivo nel messaggio a chi tocca
            messaggi.setText( "Mossa " + rst.getTnumb() + " : Tocca Al " + achi );
            mang.repaint(); // Aggiorno il pannello mangiati
            
        } // Fine If

        // Comunico lo SCACCO quando c'e
        if( !ai.verificaScacco(pA[ 15 ].getPos().coordX, pA[ 15 ].getPos().coordY,pos, pT ) ){
            
            messaggi.setText( "Mossa " + rst.getTnumb() + ": Scacco Al Re " + achi);
            
            if( ncm && ai.verificaScaccoMatto( pA, pT, pos, rst ) ){
                
                notifiche.setBackground( Color.red );
                notifiche.setText( "Scacco Matto !!!" );
            
            } // Fine If
        
        } // Fine If
    
    } // Fine aggiorna

    // Metodo cha consente alla classe di avere il riferimento da cui dipende
    // E necessario per la promozione
    public void setPointer( Mossa mos ){
        
        m = mos;
    
    } // Fine setPointer

} // Fine Classe Mossa