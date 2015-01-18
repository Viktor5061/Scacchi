package scacchi;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

// Classe che crea il frame della chiusura dell'applicazione
public class TerminaProgramma implements WindowListener, ActionListener{
    
    private JFrame f;
    private final int screenWidth;
    private final int screenHeigh;
    
    TerminaProgramma( Dimension size ) {
        
        screenWidth = ( int ) size.getWidth() / 2;
        screenHeigh = ( int ) size.getHeight() / 2;
    
    } // Fine Costruttore TerminaProgramma

    // Alla chiusura apre un frame che chiede se si vuole veramente uscire
    @Override
    public void windowClosing( WindowEvent e ){
        
        f = new JFrame( "Uscita" );
        
        Container c = f.getContentPane();
        
        JLabel la = new JLabel( "Uscire Dal Progetto Scacchi ?" );
        c.add( la );
        
        JButton b1 = new JButton( "Si" );
        b1.addActionListener( this );
	
        JButton b2 = new JButton( "No" );
        b2.addActionListener( this );
        
        c.add( b1 );
        c.add( b2 );
        
        c.setLayout( new FlowLayout() );
        

        f.setBounds( screenWidth, screenHeigh, 300, 90 );
        f.setVisible( true );
    
    } // Fine windowClosing
    
    @Override
    public void actionPerformed( ActionEvent e ){
        
        String scelta = e.getActionCommand();
        
        if( scelta.equals( "Si" ) ){
            
            System.exit( 0 ); // In questo modo chiudendo la finestra si esce dalla applicazione
        
        } // Fine If
        
        if( scelta.equals( "No" ) ){
            
            f.dispose();
        
        } // Fine If
    
    } // Fine acttonPerformed

    @Override
    public void windowOpened(WindowEvent e){}
    @Override
    public void windowIconified(WindowEvent e){}
    @Override
    public void windowClosed(WindowEvent e){}
    @Override
    public void windowDeiconified(WindowEvent e){}
    @Override
    public void windowActivated(WindowEvent e){}
    @Override
    public void windowDeactivated(WindowEvent e){}
    
} // Fine Classe TerminaProgramma