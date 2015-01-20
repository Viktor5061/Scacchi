package scacchi;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

// Genera un frame contenente l'impaginazione della lista delle mosse effettuate
public class ListaMosse implements ActionListener{
    
    private JFrame f;
    // Genera il frame, recupera la lista delle mosse e aggiunge i bottoni di opzione
    // Ce la possibilita di stampare la pagina visualizzata
    
    public ListaMosse( Lista rst, JFrame fr ){
        
       // this.fr = fr;
        f = new JFrame( "Lista Delle Mosse !!!" );
        Container c = f.getContentPane();
        JTextArea testo = new JTextArea( 35, 60 );
        testo.setEditable( false );
        testo.setFont( new Font( "Times New Roman", 0, 12 ) );
        
        GregorianCalendar cal = new GregorianCalendar();
        String data = cal.get( cal.DATE ) + "/" + cal.get( cal.MONTH ) + "/" + cal.get( cal.YEAR );
        testo.append( "Data: " + data );
        
        testo.append( "\n\n\t\t\t" + "Progetto Scacchi !!!" );
        

        
        testo.append( "\n\n\t\t\t" + "Lista Delle Mosse:" + "\n\n" );
        testo.append( rst.toStringComp() );
        
        JScrollPane text=new JScrollPane( testo );
        text.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        c.add( text );
        
        JButton bu2 = new JButton( "Ok" );
        bu2.addActionListener( this );
        c.add( bu2 );
        c.setLayout( new FlowLayout() );
        
        f.setBounds( 100, 100, 600, 600 );
        f.setVisible( true );
    
    } // Fine Costruttore ListaMosse

    // Chiude la finestra e ritorna al frame principale
    @Override
    public void actionPerformed( ActionEvent e ){
        
        if( e.getActionCommand().equals( "Ok" ) ){
            
            f.dispose();
        
        } // Fine If
    
    } // Fine actionPerformed

} // Fine Classe ListaMosse