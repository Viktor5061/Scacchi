package scacchi;

import java.io.*;

public class ListaNodi implements Lista, Serializable, Cloneable{
    
    private Nodo nodo;

    // Costruttore di una lista vuota
    public ListaNodi(){
    
        nodo = null;
    
    } // Fine Costruttore ListaNodi
    
    @Override
    public boolean isEmpty(){
        
        return ( nodo == null );
    
    } // Fine isEmpty
    
    @Override
    public Object testa(){
        
        if( isEmpty() ){
            
            return null;
        
        } else {
            
            return nodo.info;

        }  //Fine If Else
    
    } // Fine testa
    
    @Override
    public Nodo getNode(){
        
        return nodo;
    
    } // Fine getNode
    
    @Override
    public Nodo ultimoNodo(){
        
        if( isEmpty() ){
            
            return null;
        
        } // Fine If
        
        Nodo tmp = nodo;
        
        while ( tmp.next != null ){
         
            tmp = tmp.next;
        
        } // Fine While
        
        return tmp;
    
    } // Fine ultimoNodo
    
    @Override
    public Object tell(){
        
        if( isEmpty() ){
            
            return null;
        
        } // Fine If
        
        return ultimoNodo().info;
    
    } // Fine tell
    
    @Override
    public void inserisci( Object o ){

        // L'ordine deve rimanere quello di inserimento e non l'inverso
        if( isEmpty() ){
            
            nodo = new Nodo( o );
        
        } else {
            
            Nodo tmp = ultimoNodo();
            tmp.next = new Nodo( o );
        
        } // Fine If Else
    
    } // Fine inserisci
    
    @Override
    public void taglia(){
        
        Nodo tmp = nodo;
        
        if( nodo.next == null ){
            
            nodo = null;
        
        } else {
            
            while ( tmp.next.next != null ){
                
                tmp = tmp.next;
            
            }
            
            tmp.next = null;
        
        } // Fine If Else
    
    } // Fine taglia
    
    // ToString per  generare la stringa per il pannello delle mosse
    @Override
    public String toString(){
        
        String tmp = " " + 1 + " . ";
        Nodo i = nodo;
        int j = 2;
        
        while( i != null ){
            
            tmp = tmp + i.info.toString();
            
            if( j % 2 != 0 ){
                
                tmp = tmp + " " + ( j / 2 + 1 ) + " . ";
            
            } // Fine If
            
            i = i.next;
            j++;
        
        } // Fine While
        
        return tmp;
    
    } // Fine toString

    // Genera la stringa per la pagina di stampa della lista mosse
    @Override
    public String toStringComp(){
        
        String tmp = " " + '\t' + 1 + " . " + '\t';
        Nodo i = nodo;
        int j = 2;
        
        while( i != null ){
            
            tmp = tmp + ( ( Situazione )( i.info ) ).peNome + " " + i.info.toString();
            
            if( j % 2 != 0 ){
                
                tmp = tmp + '\t' + ( j / 2 + 1 ) + " . " + '\t';
            
            } else {
                
                tmp=tmp+'\t';
            
            } // Fine If Else
            
            i = i.next;
            j++;
        
        } // Fine While
        
        tmp = tmp + '\n' + '\n' + "\t" + "\t" + "Numero di mosse: " + ( getTnumb() - 1 );
        return tmp;
    
    } // Fine toStringComp
    
    @Override
    public int getTnumb(){
        
        Nodo i = nodo;
        int j = 0;
        
        while( i != null) {
            
            i = i.next;
            j++;
        
        } // Fine While
        
        return j + 1;
    
    } // Fine getTnumb
    
    @Override
    public void removeAll(){
        
        nodo = null;
    
    } // Fine removeAll
    
    @Override
    public Lista clona(){
        
        try{ // Richiamo il metodo clone() di Object
            
            return ( Lista ) clone();
        
        } catch( CloneNotSupportedException e ){}
        
        return null;
    
    } // Fine clona

} // Fine Classe ListaNodi