package scacchi;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

// Genera un frame contenente l'impaginazione della lista delle mosse effettuate
public class ListaMosse implements ActionListener{
    
    private JFrame f, fr;
    // Genera il frame, recupera la lista delle mosse e aggiunge i bottoni di opzione
    // Ce la possibilita di stampare la pagina visualizzata
    
    public ListaMosse( Lista rst, JFrame fr ){
        
        this.fr = fr;
        f = new JFrame( "Lista delle mosse" );
        Container c = f.getContentPane();
        JTextArea testo = new JTextArea( 25, 64 );
        testo.setEditable( false );
        testo.setFont( new Font( "Times New Roman", 0, 12 ) );
        
        GregorianCalendar cal = new GregorianCalendar();
        String data = "" + cal.get( cal.DATE ) + "/" + cal.get( cal.MONTH ) + "/" + cal.get( cal.YEAR );
        testo.append( '\n' + "" + '\n' + '\n' + '\t' + data + "Progetto Scacchi" );
        testo.append( "" + '\n' + '\n' + '\n' + '\t' + '\t' + "\tLista delle mosse" + '\n' + '\n' );
        testo.append( rst.toStringComp() );
        
        JScrollPane text=new JScrollPane( testo );
        text.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        c.add( text );
        
        JButton bu=new JButton( "Stampa" );
        bu.addActionListener( new Stampa( testo ) );
        c.add( bu );
        
        JButton bu2 = new JButton( "Annulla" );
        bu2.addActionListener( this );
        c.add( bu2 );
        c.setLayout( new FlowLayout() );
        f.setBounds( 70, 70, 600, 455 );
        f.addWindowListener( new CloseFrame( f, fr ) );
        f.show();
    
    } // Fine Costruttore ListaMosse

    // Chiude la finestra e ritorna al frame principale
    @Override
    public void actionPerformed( ActionEvent e ){
        if( e.getActionCommand().equals( "Annulla" ) ){
            
            CloseFrame cw = new CloseFrame( f,fr );
            cw.windowClosing( new WindowEvent( fr, 201 ) );
        
        } // Fine If
    
    } // Fine actionPerformed

} // Fine Classe ListaMosse