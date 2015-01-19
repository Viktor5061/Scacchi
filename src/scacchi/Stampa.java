package scacchi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Classe per la stampa della mossa effetuata
public class Stampa extends Frame implements ActionListener{
    
    private JTextArea b;
    
    public Stampa( JTextArea t ){
        
        b = t;
    
    }; // Area Di Stampa

    // Stampa la JTextArea
    @Override
    public void actionPerformed( ActionEvent e ){
        
        PrintJob pjob = getToolkit().getPrintJob( this, "Mosse della partita", null, null );
        
        if( pjob != null ){
            
            Graphics pg = pjob.getGraphics();
            
            if( pg != null ){
                
                b.printAll( pg );
                pg.dispose();
            
            } 
            
            pjob.end();
        
        } 
    
    } 

} 