package scacchi;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;

// Classe che riproduce al processo di promozione di un pedone
public class Promozione  implements ActionListener{
    
    private int t;
    private JButton regina, torre, cavallo, alfiere;
    private Pezzo pe, p[];
    private JFrame s, f;
    private Mossa m;
    protected String opt;
    private int h;
    private Lista rst;
    protected int op2 = 1;

    // Se non si tratta di casi particolari(controllo di mosse) genera un frame che chiede
    // Di scegliere il pezzo con cui promuovere il pedone
    public Promozione( JFrame s, Pezzo pe, Pezzo pB[], Pezzo pN[], Mossa m, int turno, Lista rst, boolean intell ){
        
        this.pe = pe;
	Image torImg=null, cavImg=null, alfImg=null, regImg = null;
        this.s = s;
        this.m = m;
        this.rst = rst;
        t = turno;
        f = new JFrame( "Promozione Pedone" );
        Container c = f.getContentPane();
        //Toolkit tk = Toolkit.getDefaultToolkit();
        
        try {
            if( pe.colore == 1 ){
            
                p = pB;
                torImg = ImageIO.read(getClass().getResource( "img/Torre_Bianca.gif" ) );
                cavImg = ImageIO.read(getClass().getResource( "img/Cavallo_Bianco.gif" ) );
                alfImg = ImageIO.read(getClass().getResource( "img/Alfiere_Bianco.gif" ) );
                regImg = ImageIO.read(getClass().getResource( "img/Regina_Bianca.gif" ) );
        
            } else {
            
                p = pN;
                torImg = ImageIO.read(getClass().getResource( "img/Torre_Nera.gif" ) );
                cavImg = ImageIO.read(getClass().getResource( "img/Cavallo_Nero.gif" ) );
                alfImg = ImageIO.read(getClass().getResource( "img/Alfiere_Nero.gif" ) );
                regImg = ImageIO.read(getClass().getResource( "img/Regina_Nera.gif" ) );
        
            } 
        }
        catch (IOException ex) {}
        
        JLabel la = new JLabel( "In Cosa Vuoi Promuovere Il Pedone ?" );
        c.add( la );
        
        regina = new JButton( new ImageIcon( regImg ) );
        regina.addActionListener( this );
        regina.setActionCommand( "Regina" );
        c.add( regina );
        
        torre = new JButton( new ImageIcon( torImg ) );
        torre.addActionListener( this );
        torre.setActionCommand( "Torre" );
        c.add( torre );
        
        cavallo = new JButton( new ImageIcon( cavImg ) );
        cavallo.addActionListener( this );
        cavallo.setActionCommand( "Cavallo" );
        c.add( cavallo );
        
        alfiere = new JButton( new ImageIcon( alfImg ) );
        alfiere.addActionListener( this );
        alfiere.setActionCommand( "Alfiere" );
        c.add( alfiere );
        
        c.setLayout( new FlowLayout() );
        f.setBounds( 100, 100, 350, 150);

        if( !intell ){
            
            f.setVisible( true );//f.show();
        
        } else {
            
            switch( op2 ){
                
                case 1 : opt = "Regina"; break;
                case 2 : opt = "Torre"; break;
                case 3 : opt = "Cavallo"; break;
                case 4 : opt = "Alfiere";
            
            } // Fine Switch
            
            promote( opt );
        
        } // Fine If Else
        
        for( int i = 0; i < 8; i++ ){
            
            if( pe == p[ i ] ){
                
                h = i;
                break;
            
            } // Fine If
        
        } // Fine For
    
    } // Fine promozione
    
    @Override
    public void actionPerformed( ActionEvent e ){
        
        opt = e.getActionCommand();
        promote( opt );
    
    } // Fine actionPerformed

    // Metodo che puo essere chiamato anche da fuori
    // Consente di eseguire la sostituzione del pedone con il pezzo indicato
    public void promote( String scelta ){
        
        if( scelta.equals( "Regina" ) ){
            
            p[ h ] = new Regina( p[ h ].colore, p[ 14 ].imm, p[ h ].getPos() );
            p[ h ].promosso = true;
        
        } // Fine If
        
        if( scelta.equals( "Torre" ) ){
            
            p[ h ] = new Torre( p[ h ].colore, p[ 8 ].imm, p[ h ].getPos() );
            p[ h ].promosso = true;
        
        } // Fine If
        
        if( scelta.equals( "Cavallo" ) ){
            
            p[ h ] = new Cavallo( p[ h ].colore, p[ 10 ].imm, p[ h ].getPos() );
            p[ h ].promosso = true;
        
        } // Fine If
        
        if( scelta.equals( "Alfiere" ) ){
            
            p[ h ] = new Alfiere( p[ h ].colore, p[ 12 ].imm, p[ h ].getPos() );
            p[ h ].promosso = true;
        
        } // Fine If
        
        m.avanti( p[ h ], p[ h ].getPos(), p[ h ].getPos(), t, true );
        s.repaint();
        
        CloseFrame cl = new CloseFrame( f );
        cl.windowClosing( new WindowEvent( f, 0 ) );
    
    } // Fine promote

} // Fine Classe Promozione