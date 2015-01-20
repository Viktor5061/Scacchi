package scacchi;

import java.io.*;

// Classe nodo che riproduce la struttura a catena della lista
public class Nodo implements Serializable{
    
    protected Object info;
    protected Nodo next;
    
    // Costruisce un nodo con riferimento nullo al successivo
    public Nodo( Object o ){
    
        this( o, null );
    
    } // Fine costruttore Nodo generico

    // Costruisce un nodo con riferimento al successivo
    public Nodo( Object o, Nodo n ){
        
        info = o;
        next = n;
        System.out.println(""+o+":"+n);
    
    } // Fine costruttore Nodo con parametri

} // Fine Classe Nodo