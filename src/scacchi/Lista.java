package scacchi;

// Interfaccia che descrive la struttura dati degli scacchi
public interface Lista {
	
    // Controlla se la lista e vuota
    public boolean isEmpty();

    // Restituisce le informazioni del nodo iniziale
    public Object testa();
	
    // Restituisce il riferimento al nodo finale
    public Nodo ultimoNodo();

    // Inserisce un oggetto aggiungendolo in fondo(non in cima)
    public void inserisci( Object o );

    // Elimina l'ultimo nodo della lista
    public void taglia();

    // Restituisce la stringa delle mosse effettuate
    @Override
    public String toString();
    
    // Restituisce la stringa delle mosse effettuate con i nomi dei pezzi mossi
    public String toStringComp();

    // Restituisce la lunghezza della lista
    public int getTnumb();

    // Elimina tutti gli elementi della lista
    public void removeAll();
    
    // Restituisce le informazioni dell'ultimo nodo
    public Object tell();
    
    // Restituisce il riferimento al primo nodo della lista
    public Nodo getNode();
	
    // Restituisce una copia esatta della lista
    public Lista clona();

} // Fine Classe Lista