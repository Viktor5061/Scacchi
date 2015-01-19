package scacchi;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

// Estende la classe JMenuBar per creare una barra dei menu personalizzata
public class MyBar extends JMenuBar implements ActionListener{
    
    private float k;
    private Mossa b;
    private JFrame fr;
    private Lista rst;
    private Scacchiera brd;

    // Inserisce tutti i sottomenu nella barra e la barra degli strumenti
    public MyBar( Mossa b, JFrame f, float k, Lista rst, Scacchiera brd ){
        
        this.b = b;
        fr = f;
        this.k = k;
        this.rst = rst;
        this.brd = brd;
        
        JMenu m1 = new JMenu( "File" );
        JMenuItem it1 = new JMenuItem( "Nuova Partita" );
        JMenuItem it2 = new JMenuItem( "Lista Mosse" );
        JMenuItem it3 = new JMenuItem( "Esci" );
        it1.addActionListener( this );
        it2.addActionListener( this );
        it3.addActionListener( this );
        m1.add( it1 );
        m1.add( it2 );
        m1.add( it3 );
        add( m1 );
        
        JMenu m2 = new JMenu( "Modifica" );
        JMenuItem it8 = new JMenuItem( "Annulla L'Ultima Mossa" );
        it8.addActionListener( this );
        m2.add( it8 );
        add( m2 );
        
        JToolBar br = new JToolBar();
        Toolkit tk = Toolkit.getDefaultToolkit();
        
        Image freccia = null;
        try {
            freccia = ImageIO.read(getClass().getResource("img/last.gif"));
        } catch (IOException ex) {
            Logger.getLogger(MyBar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JButton b1 = new JButton( new ImageIcon( freccia ) );
        b1.setActionCommand( "Annulla L'Ultima Mossa" );
        b1.addActionListener( this );
        b1.setToolTipText( "Annulla L'Ultima Mossa" );

        Image nuovo = null;
        try {
            nuovo = ImageIO.read(getClass().getResource("img/new.gif"));
        } catch (IOException ex) {
            Logger.getLogger(MyBar.class.getName()).log(Level.SEVERE, null, ex);
        }
        JButton b2 = new JButton( new ImageIcon( nuovo ) );
        b2.setActionCommand( "Nuova Partita" );
        b2.addActionListener( this );
        b2.setToolTipText( "Nuova Partita" );
        
        Image lista = null;
        try {
            lista = ImageIO.read(getClass().getResource("img/list.gif"));
        } catch (IOException ex) {
            Logger.getLogger(MyBar.class.getName()).log(Level.SEVERE, null, ex);
        }

        JButton b5 = new JButton( new ImageIcon ( lista ) );
        b5.setActionCommand( "Lista Mosse" );
        b5.addActionListener( this );
        b5.setToolTipText( "Lista Mosse" );
        
        br.addSeparator();
        br.add( b2 );
        br.addSeparator();
        br.add( b5 );
        br.addSeparator();
        br.add( b1 );
        add( br );

    } // Fine MyBar
    
    @Override
    public void actionPerformed( ActionEvent e ){
        
        String scelta = e.getActionCommand();
        
        if( scelta.equals( "Lista Mosse" ) ){
            
            new ListaMosse( rst, fr );
        
        } // Fine If
        
        if( scelta.equals( "Nuova Partita" ) ){
            
            new NuovaPartita( fr, 0, rst, brd );
        
        } // Fine If
        
        if( scelta.equals( "Esci" ) ){
            
            TerminaProgramma ter = new TerminaProgramma( fr.getSize() );
            ter.windowClosing( new WindowEvent( fr, 0 ) );
        
        } // Fine If
        
        if( scelta.equals( "Annulla L'Ultima Mossa" ) ){
            
            if( !rst.isEmpty() ){
                
                rst.taglia();
                brd.aggiorna(rst);
            
            } // Fine If
        
        } // Fine If
    
    } // Fine actionPerformed

} // Fine Classe MyBar