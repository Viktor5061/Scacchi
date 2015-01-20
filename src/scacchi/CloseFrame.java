package scacchi;

import java.awt.event.*;
import javax.swing.*;

// Permette di chiudere un frame ripassando il focus al frame della scacchiera
public class CloseFrame implements WindowListener {
    
    private JFrame f;
    
    // Al costruttore vengono passati i riferimenti della finestra da chiudere e della scacchiera
    public CloseFrame( JFrame f ){
        
        this.f = f;
    
    } // Fine Costruttore CloseFrame

    // Alla chiusura della finestra ripassa il focus al frame principale
    @Override
    public void windowClosing( WindowEvent e ){
        
        f.dispose();
       // fr.toFront();
    
    } // Fine windowClosing
    
    @Override
    public void windowOpened( WindowEvent e ){}
    @Override
    public void windowIconified( WindowEvent e ){}
    @Override
    public void windowDeiconified( WindowEvent e ){}
    @Override
    public void windowActivated( WindowEvent e ){}
    @Override
    public void windowDeactivated( WindowEvent e ){}
    @Override
    public void windowClosed( WindowEvent e ){}

} // Fine Classe CloseFrame