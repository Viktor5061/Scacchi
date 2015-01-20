package scacchi;

import java.awt.*;
import javax.swing.*;	
import java.awt.event.*;

// Classe che crea il il frame per iniziare una nuova partita
public class NuovaPartita extends JFrame implements ActionListener{
    
    private float k;
    private JFrame mainFrame;
    private Lista rst;
    private Scacchiera scacchiera;
    
    // Apre un frame in cui si chiede se si vuole realmente iniziare una partita nuova
    public NuovaPartita( JFrame frame, float k, Lista rst, Scacchiera scacchiera ){
        
        super( "Inizia Una Nuova Partita" );
        mainFrame = frame;
        this.k = k;
        this.rst = rst;
        this.scacchiera = scacchiera;
        
        Container c = getContentPane();
        
        JLabel la = new JLabel( "Iniziare Una Nuova Partita ?" );
        c.add( la );
        
        JButton b1 = new JButton( "Si" );
        b1.addActionListener( this );
        
        JButton b2 = new JButton( "No" );
        b2.addActionListener( this );
        c.add( b1 );
        c.add( b2 );
        
        c.setLayout( new FlowLayout() );
        setBounds( 100, 150, 300, 90 );
        show();
    
    } // Fine Costruttore NuovaPartita

    // Riaggiorna la scacchiera a mosse zero, oppure esce senza far nulla
    @Override
    public void actionPerformed(ActionEvent e){
        
        String scelta = e.getActionCommand();
        
        if( scelta.equals( "Si" ) ){
            
            dispose();
            mainFrame.dispose();
            rst.removeAll();
            new InterfacciaGrafica( rst );
        
        } // Fine If
        
        if( scelta.equals( "No" ) ){
            
            dispose();
        
        } // Fine If
    
    } // Fine actionPerformed

} // Fine Classe NuovaPartita